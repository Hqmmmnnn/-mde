package com.hqqm.mde.repositories;

import com.hqqm.mde.models.EngineSelectData;
import java.util.List;

public interface EngineSelectDataRepository {
    List<EngineSelectData> getManufacturersData();
    List<EngineSelectData> getCylindersQuantityData();
    List<EngineSelectData> getCylinderArrangementsData();
    List<EngineSelectData> getInjectionTypesData();
    List<EngineSelectData> getVesselTypesData();
    List<EngineSelectData> getCoolingSystemTypesData();
    List<EngineSelectData> getImoEcoStandardsData();
    List<EngineSelectData> getEpaEcoStandardsData();
    List<EngineSelectData> getEuEcoStandardsData();
    List<EngineSelectData> getUicEcoStandardsData();
    List<EngineSelectData> getAssignmentsData();
    List<EngineSelectData> getEngineRatingData();
    List<EngineSelectData> getClassificationSocietyData();
    List<EngineSelectData> getFlangeTypesData();
}
