package umc.springWorkbook.web.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.springWorkbook.apiPayload.ApiResponse;
import umc.springWorkbook.domain.Review;
import umc.springWorkbook.service.StoreService.StoreCommandService;
import umc.springWorkbook.web.converter.StoreConverter;
import umc.springWorkbook.web.dto.StoreRequest;
import umc.springWorkbook.web.dto.StoreResponse;

@RestController
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/members/{memberId}/stores/{storeId}")
    public ApiResponse<StoreResponse.CreateReviewResultDTO> createReviewAPI(@PathVariable Long memberId, @PathVariable Long storeId, @RequestBody StoreRequest.ReviewDTO request) {
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }
}
