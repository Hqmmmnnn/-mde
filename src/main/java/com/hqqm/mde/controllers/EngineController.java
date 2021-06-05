package com.hqqm.mde.controllers;

import com.hqqm.mde.models.*;
import com.hqqm.mde.services.engine.impl.EngineFacade;
import com.hqqm.mde.services.engine.EngineService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api")
@AllArgsConstructor
public class EngineController {
    private final EngineService engineService;
    private final EngineFacade engineFacade;

    @GetMapping("/engines")
    public EnginesDemo getEngines(RequestParamsForEngineFiltration engineParams) {
        return engineService.getEngines(engineParams);
    }

    @GetMapping("/engines/{id}")
    public List<EngineInfoTable> getEngine(@PathVariable Long id) {
        return engineService.getEngine(id);
    }

    @PostMapping("/engines")
    @PreAuthorize("hasAuthority('engines:write')")
    public Long saveEngine(SaveEngineRequestData saveEngineRequestData) {
        return engineFacade.saveEngine(saveEngineRequestData);
    }

    @PutMapping("/engines")
    @PreAuthorize("hasAuthority('engines:update')")
    public void updateEngine(UpdateEngineDTO updateEngineDTO) {
        engineService.updateEngine(updateEngineDTO);
    }

    @DeleteMapping("/engines/{id}")
    @PreAuthorize("hasAuthority('engines:delete')")
    public void deleteEngine(@PathVariable Long id) {
        engineFacade.deleteEngine(id);
    }

    @GetMapping("/editEngine/{id}")
    @PreAuthorize("hasAuthority('engines:update')")
    public UpdateEngineDTO getEngineDataForUpdate(@PathVariable Long id) {
        return engineService.getEngineDataForUpdate(id);
    }
}