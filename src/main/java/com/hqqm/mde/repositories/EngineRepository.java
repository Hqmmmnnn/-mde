package com.hqqm.mde.repositories;

import com.hqqm.mde.controllers.engine.EngineDTO;
import com.hqqm.mde.jooq.tables.*;
import lombok.AllArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class EngineRepository {
    private final DSLContext context;

    public List<EngineDTO> getEngines(Condition condition) {
        final Engines engines = Engines.ENGINES_;
        return context.select(
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
                engines.OPERATING_TIME_FIRST_TS,
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
                .where(condition)
                .fetch()
                .into(EngineDTO.class);
    }
}

