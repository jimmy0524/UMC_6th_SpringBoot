package umc.springWorkbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.springWorkbook.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    final private MissionRepository missionRepository;

    public Boolean existsById(Long id) {
        return missionRepository.existsById(id);
    }
}
