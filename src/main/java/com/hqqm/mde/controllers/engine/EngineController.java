package com.hqqm.mde.controllers.engine;
import com.hqqm.mde.models.SaveEngineRequestData;
import com.hqqm.mde.services.engine.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/saveEngine")
    public Long saveEngine(SaveEngineRequestData saveEngineRequestData) {
        return engineFacade.saveEngine(saveEngineRequestData);
    }
}