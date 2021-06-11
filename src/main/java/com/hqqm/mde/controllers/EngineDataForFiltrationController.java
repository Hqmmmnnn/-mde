package com.hqqm.mde.controllers;

import com.hqqm.mde.models.CheckboxDTO;
import com.hqqm.mde.models.RangeDTO;
import com.hqqm.mde.services.engine.EngineDataForFiltrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/api/filtrationData")
@AllArgsConstructor
public class EngineDataForFiltrationController {
    private final EngineDataForFiltrationService engineDataForFiltrationService;

    @GetMapping("/flangeTypes")
    public List<CheckboxDTO> getFlangeTypes() {
        return engineDataForFiltrationService.getFlangeTypes();
    }

    @GetMapping("/cylindersQuantity")
    public List<CheckboxDTO> getCylinderQuantity() {
        return  engineDataForFiltrationService.getCylinderQuantity();
    }

    @GetMapping("/manufacturers")
    public List<CheckboxDTO> getManufacturers() {
        return engineDataForFiltrationService.getManufacturers();
    }

    @GetMapping("/imoEcoStandards")
    public List<CheckboxDTO> getImoEcoStandards() {
        return engineDataForFiltrationService.getImoEcoStandards();
    }

    @GetMapping("/epaEcoStandards")
    public List<CheckboxDTO> getEpaEcoStandards() {
        return engineDataForFiltrationService.getEpaEcoStandards();
    }

    @GetMapping("/euEcoStandards")
    public List<CheckboxDTO> getEuEcoStandards() {
        return engineDataForFiltrationService.getEuEcoStandards();
    }

    @GetMapping("/uicEcoStandards")
    public List<CheckboxDTO> getUicEcoStandards() {
        return engineDataForFiltrationService.getUicEcoStandards();
    }

    @GetMapping("/rotationFrequencies")
    public RangeDTO getRotationFrequency() {
        return engineDataForFiltrationService.getRotationFrequencies();
    }

    @GetMapping("/powerRatingMinAndMax")
    public RangeDTO getMinAndMaxPowerRating() {
        return engineDataForFiltrationService.getMinAndMaxPowerRating();
    }

    @GetMapping("/weightDryNoImplementsMinAndMax")
    public RangeDTO getMinAndMaxWeightDryNoImplements() {
        return engineDataForFiltrationService.getMinAndMaxWeightDryNoImplements();
    }

    @GetMapping("/lengthMinAndMax")
    public RangeDTO getMinAndMaxLength() {
        return engineDataForFiltrationService.getMinAndMaxLength();
    }

    @GetMapping("/widthMinAndMax")
    public RangeDTO getMinAndMaxWidth() {
        return engineDataForFiltrationService.getMinAndMaxWidth();
    }

    @GetMapping("/heightMinAndMax")
    public RangeDTO getMinAndMaxHeight() {
        return engineDataForFiltrationService.getMinAndMaxHeight();
    }
}
