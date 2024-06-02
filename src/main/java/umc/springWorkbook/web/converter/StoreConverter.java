package umc.springWorkbook.web.converter;

import umc.springWorkbook.domain.Review;
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

    public static StoreResponse.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return StoreResponse.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
    }
}
