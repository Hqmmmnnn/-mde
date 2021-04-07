package com.hqqm.mde.services.engine;

import com.hqqm.mde.models.*;
import com.hqqm.mde.repositories.EngineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineService {
    private final EngineRepository engineRepository;

    public List<EngineDTO> getEngines(RequestParamsForEngineFiltration requestParamsForEngineFiltration) {
        var engineFilter = new EngineFilter(requestParamsForEngineFiltration);
        return engineRepository.getEngines(engineFilter.getCondition(), engineFilter.getLastFetchedEngineId());
    }

    public Long saveEngine(Engine engine) {
        return engineRepository.saveEngine(engine);
    }

    public void updateEngine(Engine engine) {
        engineRepository.updateEngine(engine);
    }

    @Transactional
    public int deleteEngine(Long id) {
        return engineRepository.deleteEngine(id);
    }
}
