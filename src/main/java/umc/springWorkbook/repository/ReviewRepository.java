package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
