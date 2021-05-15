package com.hqqm.mde.services.engine.impl;

import com.hqqm.mde.models.*;
import com.hqqm.mde.repositories.EngineRepository;
import com.hqqm.mde.services.engine.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineServiceImpl implements EngineService {
    private final EngineRepository engineRepository;

    public List<EngineDTO> getEngines(RequestParamsForEngineFiltration requestParamsForEngineFiltration) {
        var engineFilter = new EngineFilter(requestParamsForEngineFiltration);
        return engineRepository.getEngines(engineFilter.getCondition(), engineFilter.getLastFetchedEngineId());
    }

    @Override
    public List<EngineInfoTable> getEngine(Long id) {
        return engineRepository.getEngine(id);
    }

    public Long saveEngine(Engine engine) {
        return engineRepository.saveEngine(engine);
    }

    public void updateEngine(UpdateEngineDTO engine) {
        engineRepository.updateEngine(engine);
    }

    @Override
    public UpdateEngineDTO getEngineDataForUpdate(Long id) {
        return engineRepository.getEngineDataForUpdate(id);
    }

    @Transactional
    public int deleteEngine(Long id) {
        return engineRepository.deleteEngine(id);
    }
}
