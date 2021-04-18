package com.hqqm.mde.repositories;

import com.hqqm.mde.models.Engine;
import com.hqqm.mde.models.EngineDTO;
import org.jooq.Condition;

import java.util.List;

public interface EngineRepository {
    List<EngineDTO> getEngines(Condition condition, Long lastFetchedEngineId);
    Long saveEngine(Engine engine);
    void updateEngine(Engine engine);
    int deleteEngine(Long id);
    String findImagePath(Long engineId);
}
