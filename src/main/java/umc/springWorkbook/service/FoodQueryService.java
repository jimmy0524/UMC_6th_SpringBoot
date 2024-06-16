package umc.springWorkbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.springWorkbook.repository.FoodRepository;

@Service
@RequiredArgsConstructor
public class FoodQueryService {

    private final FoodRepository foodRepository;

    public Boolean existsById(Long id) {
        return foodRepository.existsById(id);
    }

}