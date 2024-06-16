package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
