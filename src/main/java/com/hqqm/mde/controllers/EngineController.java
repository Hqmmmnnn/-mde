package com.hqqm.mde.controllers;

import com.hqqm.mde.models.EngineDTO;
import com.hqqm.mde.models.RequestParamsForEngineFiltration;
import com.hqqm.mde.models.SaveEngineRequestData;
import com.hqqm.mde.services.engine.EngineFacade;
import com.hqqm.mde.services.engine.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class EngineController {
    private final EngineService engineService;
    private final EngineFacade engineFacade;

    @GetMapping("/engines")
    public List<EngineDTO> getEngines(RequestParamsForEngineFiltration engineParams) {
        return engineService.getEngines(engineParams);
    }

    @PostMapping("/engines")
    public Long saveEngine(SaveEngineRequestData saveEngineRequestData) {
        return engineFacade.saveEngine(saveEngineRequestData);
    }

    @PutMapping("/engines")
    public void updateEngine(SaveEngineRequestData saveEngineRequestData) {
        engineFacade.updateEngine(saveEngineRequestData);
    }

    @DeleteMapping("/engines/{id}")
    public int deleteEngine(@PathVariable Long id) {
        return engineService.deleteEngine(id);
    }
}