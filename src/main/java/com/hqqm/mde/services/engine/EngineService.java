package com.hqqm.mde.services.engine;

import com.hqqm.mde.controllers.engine.EngineDTO;
import com.hqqm.mde.controllers.engine.RequestParamsForEngineFiltration;
import com.hqqm.mde.models.Engine;
import com.hqqm.mde.repositories.EngineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
