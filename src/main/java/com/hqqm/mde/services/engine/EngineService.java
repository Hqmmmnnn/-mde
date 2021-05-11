package com.hqqm.mde.services.engine;

import com.hqqm.mde.models.*;

import java.util.List;

public interface EngineService {
    List<EngineDTO> getEngines(RequestParamsForEngineFiltration requestParamsForEngineFiltration);
    List<EngineInfoTable> getEngine(Long id);
    Long saveEngine(Engine engine);
    void updateEngine(Engine engine);
    int deleteEngine(Long id);
}
