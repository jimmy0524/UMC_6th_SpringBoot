package umc.springWorkbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springWorkbook.apiPayload.code.status.ErrorStatus;
import umc.springWorkbook.apiPayload.exception.handler.FoodHandler;
import umc.springWorkbook.apiPayload.exception.handler.MemberHandler;
import umc.springWorkbook.apiPayload.exception.handler.MissionHandler;
import umc.springWorkbook.domain.Food;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.Mission;
import umc.springWorkbook.domain.mapping.FoodLike;
import umc.springWorkbook.domain.mapping.MemberMission;
import umc.springWorkbook.repository.FoodRepository;
import umc.springWorkbook.repository.MemberMissionRepository;
import umc.springWorkbook.repository.MemberRepository;
import umc.springWorkbook.repository.MissionRepository;
import umc.springWorkbook.web.converter.FoodLikeConverter;
import umc.springWorkbook.web.converter.MemberConverter;
import umc.springWorkbook.web.converter.MemberMissionConverter;
import umc.springWorkbook.web.dto.MemberRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandService {

    private final FoodRepository foodRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public Member joinMember(MemberRequestDto.JoinDto request) {
        Member member = MemberConverter.toMember(request);

        List<Food> foodList = request.getFoodLikeList().stream()
                .map(food -> {
                    return foodRepository.findById(food).orElseThrow(() -> new FoodHandler(ErrorStatus.FOOD_NOT_FOUND));
                }).toList();
        //컨버터로 Long으로 받았던 foodLikeList -> foodLike객체로 만들고,
        //이 객체 리스트를 또 각 setMember 메서드 사용. foodLike 엔티티 저장.
        List<FoodLike> foodLikeList = FoodLikeConverter.toFoodLikeList(foodList);
        foodLikeList.forEach(foodLike -> {
            foodLike.setMember(member);});

        return memberRepository.save(member);
    }

    @Transactional
    public MemberMission challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(member, mission);

        return memberMissionRepository.save(memberMission);
    }
}
