package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
