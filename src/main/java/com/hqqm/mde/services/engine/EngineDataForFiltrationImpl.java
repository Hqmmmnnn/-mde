package com.hqqm.mde.services.engine;

import com.hqqm.mde.models.CheckboxDTO;
import com.hqqm.mde.models.RangeDTO;
import com.hqqm.mde.repositories.EngineDataForFiltrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineDataForFiltrationImpl implements EngineDataForFiltrationService {
    private final EngineDataForFiltrationRepository engineDataForFiltrationRepository;

    @Override
    public List<CheckboxDTO> getFlangeTypes() {
        return engineDataForFiltrationRepository.getFlangeTypes();
    }

    @Override
    public List<CheckboxDTO> getCylinderQuantity() {
        return engineDataForFiltrationRepository.getCylinderQuantity();
    }

    @Override
    public List<CheckboxDTO> getManufacturers() {
        return engineDataForFiltrationRepository.getManufacturers();
    }

    @Override
    public List<CheckboxDTO> getRotationFrequencies() {
        return engineDataForFiltrationRepository.getRotationFrequencies();
    }

    @Override
    public List<CheckboxDTO> getImoEcoStandards() {
        return engineDataForFiltrationRepository.getImoEcoStandards();
    }

    @Override
    public List<CheckboxDTO> getEpaEcoStandards() {
        return engineDataForFiltrationRepository.getEpaEcoStandards();
    }

    @Override
    public List<CheckboxDTO> getEuEcoStandards() {
        return engineDataForFiltrationRepository.getEuEcoStandards();
    }

    @Override
    public List<CheckboxDTO> getUicEcoStandards() {
        return engineDataForFiltrationRepository.getUicEcoStandards();
    }

    @Override
    public RangeDTO getMinAndMaxPowerRating() {
        return engineDataForFiltrationRepository.getMinAndMaxPowerRating();
    }

    @Override
    public RangeDTO getMinAndMaxWeightDryNoImplements() {
        return engineDataForFiltrationRepository.getMinAndMaxWeightDryNoImplements();
    }

    @Override
    public RangeDTO getMinAndMaxLength() {
        return engineDataForFiltrationRepository.getMinAndMaxLength();
    }

    @Override
    public RangeDTO getMinAndMaxWidth() {
        return engineDataForFiltrationRepository.getMinAndMaxWidth();
    }

    @Override
    public RangeDTO getMinAndMaxHeight() {
        return engineDataForFiltrationRepository.getMinAndMaxHeight();
    }
}
