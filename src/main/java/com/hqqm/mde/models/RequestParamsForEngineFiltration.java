package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class RequestParamsForEngineFiltration {
    private String model;
    private String manufacturerName;
    private String powerRating;
    private Integer rotationSpeed;
    private Integer cylinderQuantity;
    private String weightDryNoImplements;
    private String length;
    private String width;
    private String height;
    private String flangeType;
    private String imoEcoStandard;
    private String epaEcoStandard;
    private String euEcoStandard;
    private String uicEcoStandard;
    private Long lastFetchedEngineId;
}