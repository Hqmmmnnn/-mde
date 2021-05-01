package com.hqqm.mde.repositories;

import com.hqqm.mde.models.Engine;
import com.hqqm.mde.models.EngineDTO;
import org.jooq.Condition;

import java.util.List;
import java.util.Optional;

public interface EngineRepository {
    List<EngineDTO> getEngines(Condition condition, Long lastFetchedEngineId);
    Long saveEngine(Engine engine);
    void updateEngine(Engine engine);
    int deleteEngine(Long id);
    Optional<String> findImagePath(Long engineId);
}
