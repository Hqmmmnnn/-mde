package com.hqqm.mde.repositories;

import com.hqqm.mde.models.*;
import org.jooq.Condition;

import java.util.List;
import java.util.Optional;

public interface EngineRepository {
    List<EngineDTO> getEngines(Condition condition, Long lastFetchedEngineId);
    List<EngineInfoTable> getEngine(Long id);
    Long saveEngine(Engine engine);
    void updateEngine(UpdateEngineDTO engine);
    UpdateEngineDTO getEngineDataForUpdate(Long id);
    Optional<String> deleteEngine(Long id);
    Optional<String> findImagePath(Long engineId);
    String deleteEngineImage(Long engineId);
    void updateEngineImage(String pathToImg, Long engineId);

    ExportEngineData exportEngineInCSV(Long id);
}
