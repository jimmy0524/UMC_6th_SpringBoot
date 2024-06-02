package umc.springWorkbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springWorkbook.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
