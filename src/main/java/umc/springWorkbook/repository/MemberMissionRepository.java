package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.mapping.FoodLike;
import umc.springWorkbook.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
