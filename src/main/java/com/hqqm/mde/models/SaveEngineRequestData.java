package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveEngineRequestData {
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
    private Integer rotationFrequencyId;
    private Integer torqueMax;
    private Integer fuelRate;
    private Float fuelRateNominalPower;
    private Float cylinderWorkingVolume;
    private Integer cylinderQuantityId;
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
    private Integer imoEcoStandardId;
    private Integer epaEcoStandardId;
    private Integer euEcoStandardId;
    private Integer uicEcoStandardId;
    private String vesselType;
    private Integer classificationSocietyId;
    private Integer flangeId;
    private List<MultipartFile> files;
    private MultipartFile image;
}