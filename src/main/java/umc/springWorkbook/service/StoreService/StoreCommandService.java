package umc.springWorkbook.service.StoreService;

import umc.springWorkbook.domain.Review;
import umc.springWorkbook.web.dto.StoreRequest;

public interface StoreCommandService {
    Review createReview(Long memberId, Long StoreId, StoreRequest.ReviewDTO request);
}
