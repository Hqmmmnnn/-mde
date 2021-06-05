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
    private String series;
    private Integer powerRating;
    private Integer rotationFrequency;
    private String manufacturerName;

    // dimensions
    private Integer length;
    private Integer width;
    private Integer height;

    // cylinder
    private Integer cylinderQuantity;
    private Integer cylinderDiameter;
    private Integer pistonStroke;

    // eco standards
    private String imoEcoStandard;
    private String epaEcoStandard;
    private String euEcoStandard;
    private String uicEcoStandard;
}
