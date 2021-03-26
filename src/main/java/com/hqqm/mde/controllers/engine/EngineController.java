package com.hqqm.mde.controllers.engine;
import com.hqqm.mde.services.engine.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EngineController {
    private final EngineService engineService;

    @GetMapping("/engines")
    public List<EngineDTO> getEngines(EngineRequestParams engineParams) {
        return engineService.getEngines(engineParams);
    }
}