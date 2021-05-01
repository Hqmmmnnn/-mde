package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Engine {
    private Long engineId;
    private Integer manufacturerId;
    private String series;
    private String model;
    private Integer assignmentId;
    private Integer engineRatingId;
    private Integer operatingTimeYear;
    private Integer operatingTimeFirstTs;
    private Integer operatingTimeToRepair;
    private Integer powerRating;
    private Integer rotationSpeed;
    private Integer torqueMax;
    private Integer fuelRate;
    private Float fuelRateNominalPower;
    private Float cylinderWorkingVolume;
    private Integer cylinderQuantity;
    private Integer cylinderDiameter;
    private Integer pistonStroke;
    private Float compressionRatio;
    private String injectionType;
    private Integer injectionPressure;
    private Integer cylinderMaxPressure;
    private String cylinderArrangement;
    private Integer cylinderDegrees;
    private Integer weightDryNoImplements;
    private Integer weightWithImplements;
    private String coolingSystemType;
    private Integer length;
    private Integer width;
    private Integer height;
    private Float oilRate;
    private Integer oilSystemVolume;
    private Integer coolingSystemVolume;
    private String imoEcoStandard;
    private String epaEcoStandard;
    private String euEcoStandard;
    private String uicEcoStandard;
    private String vesselType;
    private Integer classificationSocietyId;
    private Integer flangeId;
    private List<FileEntity> files;
    private String pathToImage;
}
