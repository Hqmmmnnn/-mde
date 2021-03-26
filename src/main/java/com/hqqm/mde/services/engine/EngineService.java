package com.hqqm.mde.services.engine;

import com.hqqm.mde.controllers.engine.EngineDTO;
import com.hqqm.mde.controllers.engine.EngineRequestParams;
import com.hqqm.mde.repositories.EngineRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EngineService {
    private final EngineRepository engineRepository;

    public List<EngineDTO> getEngines(EngineRequestParams engineRequestParams) {
        var engineFilter = new EngineFilter(engineRequestParams);
        return engineRepository.getEngines(engineFilter.getCondition());
    }
}
