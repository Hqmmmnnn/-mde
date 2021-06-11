package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RequestParamsForEngineFiltration {
    private String model;
    private List<String> manufacturerNames;
    private String powerRating;
    private String rotationFrequencies;
    private List<String> cylindersQuantity;
    private String weightDryNoImplements;
    private String length;
    private String width;
    private String height;
    private List<String> flangeTypes;
    private List<String> imoEcoStandards;
    private List<String> epaEcoStandards;
    private List<String> euEcoStandards;
    private List<String> uicEcoStandards;
    private Integer currentPage;
}