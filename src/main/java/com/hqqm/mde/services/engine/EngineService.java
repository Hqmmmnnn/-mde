package com.hqqm.mde.services.engine;

import com.hqqm.mde.controllers.engine.EngineDTO;
import com.hqqm.mde.controllers.engine.EngineRequestParamsForFiltration;
import com.hqqm.mde.repositories.EngineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineService {
    private final EngineRepository engineRepository;

    public List<EngineDTO> getEngines(EngineRequestParamsForFiltration engineRequestParamsForFiltration) {
        var engineFilter = new EngineFilter(engineRequestParamsForFiltration);
        return engineRepository.getEngines(engineFilter.getCondition(), engineFilter.getLastFetchedEngineId());
    }
}
