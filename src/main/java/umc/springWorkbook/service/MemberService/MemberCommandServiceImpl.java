package umc.springWorkbook.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springWorkbook.apiPayload.code.status.ErrorStatus;
import umc.springWorkbook.apiPayload.exception.handler.FoodHandler;
import umc.springWorkbook.domain.Food;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.mapping.FoodLike;
import umc.springWorkbook.repository.FoodLikeRepository;
import umc.springWorkbook.repository.FoodRepository;
import umc.springWorkbook.repository.MemberRepository;
import umc.springWorkbook.web.converter.FoodLikeConverter;
import umc.springWorkbook.web.converter.MemberConverter;
import umc.springWorkbook.web.dto.MemberRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final FoodRepository foodRepository;
    private final MemberRepository memberRepository;
    private final FoodLikeRepository foodLikeRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequest.JoinDto request) {
        Member member = MemberConverter.toMember(request);

        Member savedMember = memberRepository.save(member);

        List<Food> foodList = request.getFoodLikeList().stream()
                .map(food -> {
                    return foodRepository.findById(food).orElseThrow(() -> new FoodHandler(ErrorStatus.FOOD_NOT_FOUND));
                }).toList();
        //컨버터로 String으로 받았던 foodLikeList -> foodLike객체로 만들고,
        //이 객체 리스트를 또 각 setMember 메서드 사용. foodLike 엔티티 저장.
        List<FoodLike> foodLikeList = FoodLikeConverter.toFoodLikeList(foodList);
        foodLikeList.forEach(foodLike -> {
            foodLike.setMember(member);
            foodLikeRepository.save(foodLike);});

        return savedMember;
    }

}
