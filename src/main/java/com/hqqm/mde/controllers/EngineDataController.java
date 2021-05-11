package com.hqqm.mde.controllers;

import com.hqqm.mde.models.EngineSelectData;
import com.hqqm.mde.services.engine.EngineSelectDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EngineDataController {
    private final EngineSelectDataService engineSelectDataService;

    @GetMapping("/manufacturersData")
    public List<EngineSelectData> getManufacturersData() {
        return engineSelectDataService.getManufacturersData();
    }

    @GetMapping("/rotationFrequenciesData")
    public List<EngineSelectData> getRotationFrequenciesData() {
        return engineSelectDataService.getRotationFrequenciesData();
    }

    @GetMapping("/cylindersQuantityData")
    public List<EngineSelectData> getCylindersQuantityData() {
        return engineSelectDataService.getCylindersQuantityData();
    }

    @GetMapping("/imoEcoStandardsData")
    public List<EngineSelectData> getImoEcoStandardsData() {
        return engineSelectDataService.getImoEcoStandardsData();
    }

    @GetMapping("/epaEcoStandardsData")
    public List<EngineSelectData> getEpaEcoStandardsData() {
        return engineSelectDataService.getEpaEcoStandardsData();
    }

    @GetMapping("/euEcoStandardsData")
    public List<EngineSelectData> getEuEcoStandardsData() {
        return engineSelectDataService.getEuEcoStandardsData();
    }

    @GetMapping("/uicEcoStandardsData")
    public List<EngineSelectData> getUicEcoStandardsData() {
        return engineSelectDataService.getUicEcoStandardsData();
    }

    @GetMapping("/assignmentsData")
    public List<EngineSelectData> getAssignmentsData() {
        return engineSelectDataService.getAssignmentsData();
    }

    @GetMapping("/engineRatingData")
    public List<EngineSelectData> getEngineRatingData() {
        return engineSelectDataService.getEngineRatingData();
    }

    @GetMapping("/classificationSocietyData")
    public List<EngineSelectData> getClassificationSocietyData() {
        return engineSelectDataService.getClassificationSocietyData();
    }

    @GetMapping("/flangeTypesData")
    public List<EngineSelectData> getFlangeTypesData() {
        return engineSelectDataService.getFlangeTypesData();
    }
}

