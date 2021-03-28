package com.hqqm.mde.repositories;

import com.hqqm.mde.controllers.engine.EngineDTO;
import  com.hqqm.mde.jooq.tables.*;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EngineRepository {
    private final DSLContext context;

    private final Engines e = Engines.ENGINES_;
    private final Manufacturers m = Manufacturers.MANUFACTURERS;
    private final ClassificationSocieties cs = ClassificationSocieties.CLASSIFICATION_SOCIETIES;
    private final Assignments a = Assignments.ASSIGNMENTS;
    private final Ratings r = Ratings.RATINGS;
    private final Flanges f = Flanges.FLANGES;

    public EngineRepository(DSLContext context) {
        this.context = context;
    }

    public List<EngineDTO> getEngines(Condition condition, Integer lastFetchedEngineId) {
        return context.select(
                //general
                e.ENGINE_ID.as("id"),
                e.MODEL,
                e.POWER_RATING,
                e.ROTATION_SPEED,
                m.ABBREVIATION.as("manufacturerAbbreviation"),
                e.TORQUE_MAX,
                a.ASSIGNMENT,
                r.LOAD_MODE,
                f.TYPE.as("flangeType"),
                // recommended operating time
                e.OPERATING_TIME_YEAR,
                e.OPERATING_TIME_FIRST_TS,
                e.OPERATING_TIME_TO_REPAIR,
                // fuel consumption
                e.FUEL_RATE,
                e.FUEL_RATE_NOMINAL_POWER,
                // cylinder
                e.CYLINDER_WORKING_VOLUME,
                e.CYLINDER_QUANTITY,
                e.CYLINDER_DIAMETER,
                e.PISTON_STROKE,
                e.COMPRESSION_RATIO,
                e.CYLINDER_MAX_PRESSURE,
                e.CYLINDER_ARRANGEMENT,
                e.CYLINDER_DEGREES,
                // injection
                e.INJECTION_TYPE,
                e.INJECTION_PRESSURE,
                // dimensions
                e.LENGTH,
                e.WIDTH,
                e.HEIGHT,
                // weight
                e.WEIGHT_DRY_NO_IMPLEMENTS,
                e.WEIGHT_WITH_IMPLEMENTS,
                // cooling
                e.COOLING_SYSTEM_TYPE,
                e.COOLING_SYSTEM_VOLUME,
                // oil
                e.OIL_RATE,
                e.OIL_SYSTEM_VOLUME,
                // eco standards
                e.IMO_ECO_STANDARD,
                e.EPA_ECO_STANDARD,
                e.EU_ECO_STANDARD,
                e.UIC_ECO_STANDARD,
                cs.NAME.as("classificationSociety"),
                e.VESSEL_TYPE
        )
                .from(e)
                .leftJoin(m).using(e.MANUFACTURER_ID)
                .leftJoin(a).using(e.ASSIGNMENT_ID)
                .leftJoin(r).using(e.RATING_ID)
                .leftJoin(cs).using(e.CLASSIFICATION_SOCIETY_ID)
                .leftJoin(f).using(e.FLANGE_ID)
                .where(condition)
                .orderBy(e.ENGINE_ID)
                .seek(lastFetchedEngineId)
                .limit(2)
                .fetch()
                .into(EngineDTO.class);
    }
}

