package com.hqqm.mde.models;

import com.hqqm.mde.jooq.enums.*;
import lombok.Data;

import java.util.List;

@Data
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
    private InjectionTypes injectionType;
    private Integer injectionPressure;
    private Integer cylinderMaxPressure;
    private ArrangementCylinders cylinderArrangement;
    private Integer cylinderDegrees;
    private Integer weightDryNoImplements;
    private Integer weightWithImplements;
    private CoolingSystemTypes coolingSystemType;
    private Integer length;
    private Integer width;
    private Integer height;
    private Float oilRate;
    private Integer oilSystemVolume;
    private Integer coolingSystemVolume;
    private ImoEcoStandards imoEcoStandard;
    private EpaEcoStandards epaEcoStandard;
    private EuEcoStandards euEcoStandard;
    private UicEcoStandards uicEcoStandard;
    private VesselTypes vesselType;
    private Integer classificationSocietyId;
    private Integer flangeId;
    private List<FileEntity> files;
    private String pathToImage;
}
