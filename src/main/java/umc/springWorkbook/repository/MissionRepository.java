package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
