package com.hqqm.mde.controllers;
import com.hqqm.mde.models.*;
import com.hqqm.mde.services.engine.EngineFacade;
import com.hqqm.mde.services.engine.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping("/engines/saveEngine")
    public Long saveEngine(SaveEngineRequestData saveEngineRequestData) {
        return engineFacade.saveEngine(saveEngineRequestData);
    }

    @PutMapping("/engines/updateEngine")
    public void updateEngine(SaveEngineRequestData saveEngineRequestData) {
        engineFacade.updateEngine(saveEngineRequestData);
    }

    @DeleteMapping("/engines/{id}")
    public int deleteEngine(@PathVariable Long id) {
        return engineService.deleteEngine(id);
    }

    @GetMapping(value = "/images/{engineId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] getEngineImage(@PathVariable Long engineId) throws IOException {
        return engineService.getImage(engineId);
    }
}