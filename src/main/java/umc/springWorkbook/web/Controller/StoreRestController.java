package umc.springWorkbook.web.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.springWorkbook.apiPayload.ApiResponse;
import umc.springWorkbook.domain.Review;
import umc.springWorkbook.domain.Store;
import umc.springWorkbook.service.StoreCommandService;
import umc.springWorkbook.validation.annotation.ExistStore;
import umc.springWorkbook.web.converter.StoreConverter;
import umc.springWorkbook.web.dto.StoreRequest;
import umc.springWorkbook.web.dto.StoreResponse;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/members/{memberId}/stores/{storeId}")
    public ApiResponse<StoreResponse.CreateReviewResultDTO> createReviewAPI(@PathVariable Long memberId,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @RequestBody @Valid StoreRequest.ReviewDTO request) {
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    //특정지역에 가게 추가
    @PostMapping("/stores")
    public ApiResponse<StoreResponse.CreateStoreResultDTO> createStoreAPI(@RequestBody @Valid StoreRequest.CreateDTO request) {
        Store store = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(StoreConverter.toCreateStoreResultDTO(store));
    }


}
