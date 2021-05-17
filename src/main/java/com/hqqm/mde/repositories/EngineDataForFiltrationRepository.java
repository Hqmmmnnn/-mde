package com.hqqm.mde.repositories;

import com.hqqm.mde.models.CheckboxDTO;
import com.hqqm.mde.models.RangeDTO;

import java.util.List;

public interface EngineDataForFiltrationRepository {
    List<CheckboxDTO> getFlangeTypes();
    List<CheckboxDTO> getCylinderQuantity();
    List<CheckboxDTO> getManufacturers();
    List<CheckboxDTO> getImoEcoStandards();
    List<CheckboxDTO> getEpaEcoStandards();
    List<CheckboxDTO> getEuEcoStandards();
    List<CheckboxDTO> getUicEcoStandards();
    RangeDTO getRotationFrequencies();
    RangeDTO getMinAndMaxPowerRating();
    RangeDTO getMinAndMaxWeightDryNoImplements();
    RangeDTO getMinAndMaxLength();
    RangeDTO getMinAndMaxWidth();
    RangeDTO getMinAndMaxHeight();
}
