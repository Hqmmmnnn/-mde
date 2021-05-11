package com.hqqm.mde.services.engine;

import com.hqqm.mde.models.EngineSelectData;
import com.hqqm.mde.repositories.EngineSelectDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EngineSelectDataServiceImpl implements EngineSelectDataService {
    private final EngineSelectDataRepository engineSelectDataRepository;

    @Override
    public List<EngineSelectData> getManufacturersData() {
        return engineSelectDataRepository.getManufacturersData();
    }

    @Override
    public List<EngineSelectData> getRotationFrequenciesData() {
        return engineSelectDataRepository.getRotationFrequenciesData();
    }

    @Override
    public List<EngineSelectData> getCylindersQuantityData() {
        return engineSelectDataRepository.getCylindersQuantityData();
    }

    @Override
    public List<EngineSelectData> getImoEcoStandardsData() {
        return engineSelectDataRepository.getImoEcoStandardsData();
    }

    @Override
    public List<EngineSelectData> getEpaEcoStandardsData() {
        return engineSelectDataRepository.getEpaEcoStandardsData();
    }

    @Override
    public List<EngineSelectData> getEuEcoStandardsData() {
        return engineSelectDataRepository.getEuEcoStandardsData();
    }

    @Override
    public List<EngineSelectData> getUicEcoStandardsData() {
        return engineSelectDataRepository.getUicEcoStandardsData();
    }

    @Override
    public List<EngineSelectData> getAssignmentsData() {
        return engineSelectDataRepository.getAssignmentsData();
    }

    @Override
    public List<EngineSelectData> getEngineRatingData() {
        return engineSelectDataRepository.getEngineRatingData();
    }

    @Override
    public List<EngineSelectData> getClassificationSocietyData() {
        return engineSelectDataRepository.getClassificationSocietyData();
    }

    @Override
    public List<EngineSelectData> getFlangeTypesData() {
        return engineSelectDataRepository.getFlangeTypesData();
    }
}
