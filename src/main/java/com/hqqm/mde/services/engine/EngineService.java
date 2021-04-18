package com.hqqm.mde.services.engine;

import com.hqqm.mde.models.Engine;
import com.hqqm.mde.models.EngineDTO;
import com.hqqm.mde.models.RequestParamsForEngineFiltration;

import java.util.List;

public interface EngineService {
    List<EngineDTO> getEngines(RequestParamsForEngineFiltration requestParamsForEngineFiltration);
    Long saveEngine(Engine engine);
    void updateEngine(Engine engine);
    int deleteEngine(Long id);
}
