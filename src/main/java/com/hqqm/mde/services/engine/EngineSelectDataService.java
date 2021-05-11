package com.hqqm.mde.services.engine;

import com.hqqm.mde.models.EngineSelectData;

import java.util.List;

public interface EngineSelectDataService {
    List<EngineSelectData> getManufacturersData();
    List<EngineSelectData> getRotationFrequenciesData();
    List<EngineSelectData> getCylindersQuantityData();
    List<EngineSelectData> getImoEcoStandardsData();
    List<EngineSelectData> getEpaEcoStandardsData();
    List<EngineSelectData> getEuEcoStandardsData();
    List<EngineSelectData> getUicEcoStandardsData();
    List<EngineSelectData> getAssignmentsData();
    List<EngineSelectData> getEngineRatingData();
    List<EngineSelectData> getClassificationSocietyData();
    List<EngineSelectData> getFlangeTypesData();
}
