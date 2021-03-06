package com.hqqm.mde.lib.FromRequestParamsMappers;

import com.hqqm.mde.models.Engine;
import com.hqqm.mde.models.SaveEngineRequestData;

public class FromSaveEngineReqToEngineMapper {
    public static Engine map(SaveEngineRequestData data) {
        Engine e = new Engine();
        e.setEngineId(data.getEngineId());
        e.setManufacturerId(data.getManufacturerId());
        e.setSeries(data.getSeries());
        e.setModel(data.getModel());
        e.setAssignmentId(data.getAssignmentId());
        e.setEngineRatingId(data.getEngineRatingId());
        e.setOperatingTimeYear(data.getOperatingTimeYear());
        e.setOperatingTimeFirstTs(data.getOperatingTimeFirstTs());
        e.setOperatingTimeToRepair(data.getOperatingTimeToRepair());
        e.setPowerRating(data.getPowerRating());
        e.setRotationFrequency(data.getRotationFrequency());
        e.setTorqueMax(data.getTorqueMax());
        e.setFuelRate(data.getFuelRate());
        e.setFuelRateNominalPower(data.getFuelRateNominalPower());
        e.setCylinderWorkingVolume(data.getCylinderWorkingVolume());
        e.setCylinderQuantityId(data.getCylinderQuantityId());
        e.setCylinderDiameter(data.getCylinderDiameter());
        e.setPistonStroke(data.getPistonStroke());
        e.setCompressionRatio(data.getCompressionRatio());
        e.setInjectionTypeId(data.getInjectionTypeId());
        e.setInjectionPressure(data.getInjectionPressure());
        e.setCylinderMaxPressure(data.getCylinderMaxPressure());
        e.setCylinderArrangementId(data.getCylinderArrangementId());
        e.setCylinderDegrees(data.getCylinderDegrees());
        e.setWeightDryNoImplements(data.getWeightDryNoImplements());
        e.setWeightWithImplements(data.getWeightWithImplements());
        e.setCoolingSystemTypeId(data.getCoolingSystemTypeId());
        e.setLength(data.getLength());
        e.setWidth(data.getWidth());
        e.setHeight(data.getHeight());
        e.setOilRate(data.getOilRate());
        e.setOilSystemVolume(data.getOilSystemVolume());
        e.setCoolingSystemVolume(data.getCoolingSystemVolume());
        e.setImoEcoStandardId(data.getImoEcoStandardId());
        e.setEpaEcoStandardId(data.getEpaEcoStandardId());
        e.setEuEcoStandardId(data.getEuEcoStandardId());
        e.setUicEcoStandardId(data.getUicEcoStandardId());
        e.setVesselTypeId(data.getVesselTypeId());
        e.setClassificationSocietyId(data.getClassificationSocietyId());
        e.setFlangeId(data.getFlangeId());
        e.setNote(data.getNote());
        return e;
    }
}

