package umc.springWorkbook.web.converter;

import umc.springWorkbook.domain.Review;
import umc.springWorkbook.domain.Store;
import umc.springWorkbook.web.dto.StoreRequest;
import umc.springWorkbook.web.dto.StoreResponse;

import java.sql.Timestamp;


public class StoreConverter {

    public static Review toReview(StoreRequest.ReviewDTO request) {
        return Review.builder()
                            .content(request.getContent())
                            .star(request.getStar())
                            .build();
    }

    public static Store toStore(StoreRequest.CreateDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .category(request.getCategory()).build();
    }

    public static StoreResponse.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return StoreResponse.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public static StoreResponse.CreateStoreResultDTO toCreateStoreResultDTO(Store store) {
        return StoreResponse.CreateStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
