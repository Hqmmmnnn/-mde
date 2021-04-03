package com.hqqm.mde.lib.FromRequestParamsMappers;

import com.hqqm.mde.jooq.enums.*;
import com.hqqm.mde.models.Engine;
import com.hqqm.mde.models.SaveEngineRequestData;

public class FromSaveEngineReqToEngineMapper {
    public static Engine map(SaveEngineRequestData data) {
        Engine e = new Engine();
        e.setManufacturerId(data.getManufacturerId());
        e.setSeries(data.getSeries());
        e.setModel(data.getModel());
        e.setAssignmentId(data.getAssignmentId());
        e.setEngineRatingId(data.getEngineRatingId());
        e.setOperatingTimeYear(data.getOperatingTimeYear());
        e.setOperatingTimeFirstTs(data.getOperatingTimeFirstTs());
        e.setOperatingTimeToRepair(data.getOperatingTimeToRepair());
        e.setPowerRating(data.getPowerRating());
        e.setRotationSpeed(data.getRotationSpeed());
        e.setTorqueMax(data.getTorqueMax());
        e.setFuelRate(data.getFuelRate());
        e.setFuelRateNominalPower(data.getFuelRateNominalPower());
        e.setCylinderWorkingVolume(data.getCylinderWorkingVolume());
        e.setCylinderQuantity(data.getCylinderQuantity());
        e.setCylinderDiameter(data.getCylinderDiameter());
        e.setPistonStroke(data.getPistonStroke());
        e.setCompressionRatio(data.getCompressionRatio());
        e.setInjectionType(StringToEnumConverter.convert(data.getInjectionType(), InjectionTypes.values()));
        e.setInjectionPressure(data.getInjectionPressure());
        e.setCylinderMaxPressure(data.getCylinderMaxPressure());
        e.setCylinderArrangement(StringToEnumConverter.convert(data.getCylinderArrangement(), ArrangementCylinders.values()));
        e.setCylinderDegrees(data.getCylinderDegrees());
        e.setWeightDryNoImplements(data.getWeightDryNoImplements());
        e.setWeightWithImplements(data.getWeightWithImplements());
        e.setCoolingSystemType(StringToEnumConverter.convert(data.getCoolingSystemType(), CoolingSystemTypes.values()));
        e.setLength(data.getLength());
        e.setWidth(data.getWidth());
        e.setHeight(data.getHeight());
        e.setOilRate(data.getOilRate());
        e.setOilSystemVolume(data.getOilSystemVolume());
        e.setCoolingSystemVolume(data.getCoolingSystemVolume());
        e.setImoEcoStandard(StringToEnumConverter.convert(data.getImoEcoStandard(), ImoEcoStandards.values()));
        e.setEpaEcoStandard(StringToEnumConverter.convert(data.getEpaEcoStandard(), EpaEcoStandards.values()));
        e.setEuEcoStandard(StringToEnumConverter.convert(data.getEuEcoStandard(), EuEcoStandards.values()));
        e.setUicEcoStandard(StringToEnumConverter.convert(data.getUicEcoStandard(), UicEcoStandards.values()));
        e.setVesselType(StringToEnumConverter.convert(data.getVesselType(), VesselTypes.values()));
        e.setClassificationSocietyId(data.getClassificationSocietyId());
        e.setFlangeId(data.getFlangeId());

        return e;
    }
}

