package com.hqqm.mde.controllers;

import com.hqqm.mde.models.EngineSelectData;
import com.hqqm.mde.models.Role;
import com.hqqm.mde.services.engine.EngineSelectDataService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController @RequestMapping("/api/selectData")
@PreAuthorize("hasAnyAuthority('engines:write', 'engines:update')")
@AllArgsConstructor
public class EngineSelectDataController {
    private final EngineSelectDataService engineSelectDataService;

    @GetMapping("/manufacturers")
    public List<EngineSelectData> getManufacturersData() {
        return engineSelectDataService.getManufacturersData();
    }

    @GetMapping("/cylindersQuantity")
    public List<EngineSelectData> getCylindersQuantityData() {
        return engineSelectDataService.getCylindersQuantityData();
    }

    @GetMapping("/cylinderArrangements")
    public List<EngineSelectData> getCylinderArrangementsData() {
        return engineSelectDataService.getCylinderArrangementsData();
    }

    @GetMapping("/injectionTypes")
    public List<EngineSelectData> getInjectionTypesData() {
        return engineSelectDataService.getInjectionTypesData();
    }

    @GetMapping("/vesselTypes")
    public List<EngineSelectData> getVesselTypesData() {
        return engineSelectDataService.getVesselTypesData();
    }

    @GetMapping("/coolingSystemTypes")
    public List<EngineSelectData> getCoolingSystemTypesData() {
        return engineSelectDataService.getCoolingSystemTypesData();
    }

    @GetMapping("/imoEcoStandards")
    public List<EngineSelectData> getImoEcoStandardsData() {
        return engineSelectDataService.getImoEcoStandardsData();
    }

    @GetMapping("/epaEcoStandards")
    public List<EngineSelectData> getEpaEcoStandardsData() {
        return engineSelectDataService.getEpaEcoStandardsData();
    }

    @GetMapping("/euEcoStandards")
    public List<EngineSelectData> getEuEcoStandardsData() {
        return engineSelectDataService.getEuEcoStandardsData();
    }

    @GetMapping("/uicEcoStandards")
    public List<EngineSelectData> getUicEcoStandardsData() {
        return engineSelectDataService.getUicEcoStandardsData();
    }

    @GetMapping("/assignments")
    public List<EngineSelectData> getAssignmentsData() {
        return engineSelectDataService.getAssignmentsData();
    }

    @GetMapping("/engineRating")
    public List<EngineSelectData> getEngineRatingData() {
        return engineSelectDataService.getEngineRatingData();
    }

    @GetMapping("/classificationSociety")
    public List<EngineSelectData> getClassificationSocietyData() {
        return engineSelectDataService.getClassificationSocietyData();
    }

    @GetMapping("/flangeTypes")
    public List<EngineSelectData> getFlangeTypesData() {
        return engineSelectDataService.getFlangeTypesData();
    }

    @GetMapping("/roles")
    public List<EngineSelectData> getAllRoles() {
        return Arrays.stream(Role.values())
                .map(role -> new EngineSelectData(role.toString(), role.toString()))
                .collect(Collectors.toList());
    }
}

