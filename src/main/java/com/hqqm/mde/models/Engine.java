package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private Integer rotationFrequency;
    private Integer torqueMax;
    private Integer fuelRate;
    private Float fuelRateNominalPower;
    private Float cylinderWorkingVolume;
    private Integer cylinderQuantityId;
    private Integer cylinderDiameter;
    private Integer pistonStroke;
    private Float compressionRatio;
    private Integer injectionTypeId;
    private Integer injectionPressure;
    private Integer cylinderMaxPressure;
    private Integer cylinderArrangementId;
    private Integer cylinderDegrees;
    private Integer weightDryNoImplements;
    private Integer weightWithImplements;
    private Integer coolingSystemTypeId;
    private Integer length;
    private Integer width;
    private Integer height;
    private Float oilRate;
    private Integer oilSystemVolume;
    private Integer coolingSystemVolume;
    private Integer imoEcoStandardId;
    private Integer epaEcoStandardId;
    private Integer euEcoStandardId;
    private Integer uicEcoStandardId;
    private Integer vesselTypeId;
    private Integer classificationSocietyId;
    private Integer flangeId;
    private List<FileEntity> files;
    private String pathToImage;
    private String note;
    private Date createdAt;
    private Date lastUpdate;
}
