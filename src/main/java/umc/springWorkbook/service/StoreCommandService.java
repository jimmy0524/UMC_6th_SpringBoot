package umc.springWorkbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.springWorkbook.apiPayload.code.status.ErrorStatus;
import umc.springWorkbook.apiPayload.exception.handler.RegionHandler;
import umc.springWorkbook.apiPayload.exception.handler.MemberHandler;
import umc.springWorkbook.apiPayload.exception.handler.StoreHandler;
import umc.springWorkbook.domain.*;
import umc.springWorkbook.repository.*;
import umc.springWorkbook.web.converter.StoreConverter;
import umc.springWorkbook.web.dto.StoreRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final ImageRepository imageRepository;
    private final RegionRepository regionRepository;

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

    @Transactional
    public Store createStore(StoreRequest.CreateDTO request) {
        Store store = StoreConverter.toStore(request);

        store.setAddress(regionRepository.findById(request.getRegion()).orElseThrow(() -> new RegionHandler(ErrorStatus.Region_NOT_FOUND)));

        return storeRepository.save(store);
    }

    public Boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }
}
