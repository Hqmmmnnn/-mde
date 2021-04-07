package com.hqqm.mde.models;

import com.hqqm.mde.jooq.enums.*;
import lombok.Data;

@Data
public class EngineDTO {
    private Long id;
    // general
    private String model;
    private Integer powerRating;
    private Integer rotationSpeed;
    private String manufacturerName;
    private Integer torqueMax;
    private String assignment;
    private String loadMode;
    private String flangeType;

    // recommended operating time
    private Integer operatingTimeYear;
    private Integer operatingTimeFirstTs;
    private Integer operatingTimeToRepair;

    // fuel consumption
    private Integer fuelRate;
    private Float fuelRateNominalPower;

    // cylinder
    private Float cylinderWorkingVolume;
    private Integer cylinderQuantity;
    private Integer cylinderDiameter;
    private Integer pistonStroke;
    private Float compressionRatio;
    private Integer cylinderMaxPressure;
    private ArrangementCylinders cylinderArrangement;
    private Integer cylinderDegrees;

    // injection
    private InjectionTypes injectionType;
    private Integer injectionPressure;

    // dimensions
    private Integer length;
    private Integer width;
    private Integer height;

    // weight
    private Integer weightDryNoImplements;
    private Integer weightWithImplements;

    // cooling
    private CoolingSystemTypes coolingSystemType;
    private Integer coolingSystemVolume;

    // oil
    private Float oilRate;
    private Integer oilSystemVolume;

    // eco standards
    private ImoEcoStandards imoEcoStandard;
    private EpaEcoStandards epaEcoStandard;
    private EuEcoStandards euEcoStandard;
    private UicEcoStandards uicEcoStandard;

    private VesselTypes vesselType;
    private String classificationSociety;
}
