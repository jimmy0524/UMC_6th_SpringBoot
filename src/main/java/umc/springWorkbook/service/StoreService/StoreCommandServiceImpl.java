package umc.springWorkbook.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.springWorkbook.apiPayload.code.status.ErrorStatus;
import umc.springWorkbook.apiPayload.exception.handler.MemberHandler;
import umc.springWorkbook.apiPayload.exception.handler.StoreHandler;
import umc.springWorkbook.domain.Image;
import umc.springWorkbook.domain.Review;
import umc.springWorkbook.repository.ImageRepository;
import umc.springWorkbook.repository.MemberRepository;
import umc.springWorkbook.repository.ReviewRepository;
import umc.springWorkbook.repository.StoreRepository;
import umc.springWorkbook.web.converter.StoreConverter;
import umc.springWorkbook.web.dto.StoreRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final ImageRepository imageRepository;

    @Override
    @Transactional
    public Review createReview(Long memberId, Long storeId, StoreRequest.ReviewDTO request) {
        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)));
        review.setStore(storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)));

        Review savedReview = reviewRepository.save(review);

        List<Image> imageList = new ArrayList<>();
        for(MultipartFile file : request.getImages()) {
            String imageUrl = saveFileAndGetUrl(file);
            Image image = Image.builder()
                    .imageUrl(imageUrl)
                    .build();
            image.setReview(review);
            imageRepository.save(image);
        }
        return savedReview;
    }

    // 이미지 파일 저장 후 URL 반환
    private String saveFileAndGetUrl(MultipartFile file) {
        // 이미지 파일 저장 로직 구현 예정
        String imageUrl = "";
        return imageUrl;
    }
}
