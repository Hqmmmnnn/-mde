package com.hqqm.mde.controllers.engine;

import lombok.Data;

@Data
public class EngineRequestParams {
    private String model;
    private String manufacturerName;
    private String powerRating;
    private String rotationSpeed;
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
}