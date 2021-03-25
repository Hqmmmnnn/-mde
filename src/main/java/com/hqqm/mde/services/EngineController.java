package com.hqqm.mde.services;

import com.hqqm.mde.jooq.enums.*;
import com.hqqm.mde.jooq.tables.*;
import lombok.Data;
import org.jooq.DSLContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EngineController {
    private final DSLContext context;

    public EngineController(DSLContext context) {
        this.context = context;
    }

    @GetMapping("/engines")
    public List<EngineDTO> getEngines() {
        final Engines engines = Engines.ENGINES_;
        var res = context.select(
                //general
                engines.MODEL,
                engines.POWER_RATING,
                engines.ROTATION_SPEED,
                Manufacturers.MANUFACTURERS.ABBREVIATION.as("manufacturerAbbreviation"),
                engines.TORQUE_MAX,
                Assignments.ASSIGNMENTS.ASSIGNMENT,
                Ratings.RATINGS.LOAD_MODE,
                Flanges.FLANGES.TYPE.as("flangeType"),
                // recommended operating time
                engines.OPERATING_TIME_YEAR,
                engines.OPERATING_TIME_FRST_TS,
                engines.OPERATING_TIME_TO_REPAIR,
                // fuel consumption
                engines.FUEL_RATE,
                engines.FUEL_RATE_NOMINAL_POWER,
                // cylinder
                engines.CYLINDER_WORKING_VOLUME,
                engines.CYLINDER_QUANTITY,
                engines.CYLINDER_DIAMETER,
                engines.PISTON_STROKE,
                engines.COMPRESSION_RATIO,
                engines.CYLINDER_MAX_PRESSURE,
                engines.CYLINDER_ARRANGEMENT,
                engines.CYLINDER_DEGREES,
                // injection
                engines.INJECTION_TYPE,
                engines.INJECTION_PRESSURE,
                // dimensions
                engines.LENGTH,
                engines.WIDTH,
                engines.HEIGHT,
                // weight
                engines.WEIGHT_DRY_NO_IMPLEMENTS,
                engines.WEIGHT_WITH_IMPLEMENTS,
                // cooling
                engines.COOLING_SYSTEM_TYPE,
                engines.COOLING_SYSTEM_VOLUME,
                // oil
                engines.OIL_RATE,
                engines.OIL_SYSTEM_VOLUME,
                // eco standards
                engines.IMO_ECO_STANDARD,
                engines.EPA_ECO_STANDARD,
                engines.EU_ECO_STANDARD,
                engines.UIC_ECO_STANDARD,
                ClassificationSocieties.CLASSIFICATION_SOCIETIES.NAME.as("classificationSociety"),
                engines.VESSEL_TYPE

                )
                .from(engines)
                .leftJoin(Manufacturers.MANUFACTURERS).using(engines.MANUFACTURER_ID)
                .leftJoin(Assignments.ASSIGNMENTS).using(engines.ASSIGNMENT_ID)
                .leftJoin(Ratings.RATINGS).using(engines.RATING_ID)
                .leftJoin(ClassificationSocieties.CLASSIFICATION_SOCIETIES).using(engines.CLASSIFICATION_SOCIETY_ID)
                .leftJoin(Flanges.FLANGES).using(engines.FLANGE_ID)
                .fetch()
                .into(EngineDTO.class);

        return res;
    }

}

@Data
class EngineDTO {
    // general
    private String model;
    private Integer powerRating;
    private Integer rotationSpeed;
    private String manufacturerAbbreviation;
    private Integer torqueMax;
    private String assignment;
    private String load_mode;
    private String flangeType;

    // recommended operating time
    private Integer operatingTimeYear;
    private Integer operatingTimeFrstTs;
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