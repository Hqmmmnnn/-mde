package com.hqqm.mde.services.engine;

import com.hqqm.mde.models.*;

import java.util.List;
import java.util.Optional;

public interface EngineService {
    EnginesDemo getEngines(RequestParamsForEngineFiltration requestParamsForEngineFiltration);
    List<EngineInfoTable> getEngine(Long id);
    Long saveEngine(Engine engine);
    void updateEngine(UpdateEngineDTO engine);
    UpdateEngineDTO getEngineDataForUpdate(Long id);
    Optional<String> deleteEngine(Long id);
}
