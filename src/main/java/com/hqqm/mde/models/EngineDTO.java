package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngineDTO {
    private Long id;
    // general
    private String model;
    private Integer powerRating;
    private Integer rotationFrequency;
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
    private String cylinderArrangement;
    private Integer cylinderDegrees;

    // injection
    private String injectionType;
    private Integer injectionPressure;

    // dimensions
    private Integer length;
    private Integer width;
    private Integer height;

    // weight
    private Integer weightDryNoImplements;
    private Integer weightWithImplements;

    // cooling
    private String coolingSystemType;
    private Integer coolingSystemVolume;

    // oil
    private Float oilRate;
    private Integer oilSystemVolume;

    // eco standards
    private String imoEcoStandard;
    private String epaEcoStandard;
    private String euEcoStandard;
    private String uicEcoStandard;

    private String vesselType;
    private String classificationSociety;
}
