package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.Image;
import umc.springWorkbook.domain.Member;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
