package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
