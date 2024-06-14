package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.Member;
import umc.springWorkbook.domain.mapping.FoodLike;

public interface FoodLikeRepository extends JpaRepository<FoodLike, Long> {
}
