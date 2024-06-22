package umc.springWorkbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.springWorkbook.domain.Mission;
import umc.springWorkbook.domain.Review;
import umc.springWorkbook.domain.Store;
import umc.springWorkbook.repository.MissionRepository;
import umc.springWorkbook.repository.ReviewRepository;
import umc.springWorkbook.repository.StoreRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryService {

    private final StoreRepository storeRepository;

    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    public Page<Review> getReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    public Page<Mission> getMissionList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();
        Page<Mission> MissionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return MissionPage;
    }

    public boolean checkPage(Integer value) {
        if(value < 0){
            return false;
        }
        return true;
    }
}
