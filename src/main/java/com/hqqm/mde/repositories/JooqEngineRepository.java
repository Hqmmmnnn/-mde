package com.hqqm.mde.repositories;

import com.hqqm.mde.models.EngineDTO;
import com.hqqm.mde.jooq.tables.*;
import com.hqqm.mde.models.Engine;
import lombok.AllArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JooqEngineRepository implements EngineRepository {
    private final DSLContext context;

    private final Engines e = Engines.ENGINES_;
    private final Manufacturers m = Manufacturers.MANUFACTURERS;
    private final ClassificationSocieties cs = ClassificationSocieties.CLASSIFICATION_SOCIETIES;
    private final Assignments a = Assignments.ASSIGNMENTS;
    private final Ratings r = Ratings.RATINGS;
    private final Flanges f = Flanges.FLANGES;

    public List<EngineDTO> getEngines(Condition condition, Long lastFetchedEngineId) {
        return context.select(
                //general
                e.ENGINE_ID.as("id"),
                e.MODEL,
                e.POWER_RATING,
                e.ROTATION_SPEED,
                m.NAME.as("manufacturerName"),
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
                e.VESSEL_TYPE,
                cs.NAME.as("classificationSociety")
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
                .limit(9)
                .fetch()
                .into(EngineDTO.class);
    }

    public Long saveEngine(Engine engine) {
        var engineIdRecord = context
                .insertInto(e,
                        e.MANUFACTURER_ID, e.SERIES, e.MODEL, e.ASSIGNMENT_ID, e.RATING_ID,
                        e.OPERATING_TIME_YEAR, e.OPERATING_TIME_FIRST_TS, e.OPERATING_TIME_TO_REPAIR,
                        e.POWER_RATING, e.ROTATION_SPEED, e.TORQUE_MAX, e.FUEL_RATE,
                        e.FUEL_RATE_NOMINAL_POWER, e.CYLINDER_WORKING_VOLUME, e.CYLINDER_QUANTITY,
                        e.CYLINDER_DIAMETER, e.PISTON_STROKE, e.COMPRESSION_RATIO, e.INJECTION_TYPE,
                        e.INJECTION_PRESSURE, e.CYLINDER_MAX_PRESSURE, e.CYLINDER_ARRANGEMENT,
                        e.CYLINDER_DEGREES, e.WEIGHT_DRY_NO_IMPLEMENTS, e.WEIGHT_WITH_IMPLEMENTS,
                        e.COOLING_SYSTEM_TYPE, e.LENGTH, e.WIDTH, e.HEIGHT,
                        e.OIL_RATE, e.OIL_SYSTEM_VOLUME, e.COOLING_SYSTEM_VOLUME, e.IMO_ECO_STANDARD,
                        e.EPA_ECO_STANDARD, e.EU_ECO_STANDARD, e.UIC_ECO_STANDARD, e.VESSEL_TYPE,
                        e.CLASSIFICATION_SOCIETY_ID, e.FLANGE_ID, e.PATH_TO_IMAGE)
                .values(engine.getManufacturerId(), engine.getSeries(), engine.getModel(), engine.getAssignmentId(), engine.getEngineRatingId(),
                        engine.getOperatingTimeYear(), engine.getOperatingTimeFirstTs(), engine.getOperatingTimeToRepair(),
                        engine.getPowerRating(), engine.getRotationSpeed(), engine.getTorqueMax(), engine.getFuelRate(),
                        engine.getFuelRateNominalPower(), engine.getCylinderWorkingVolume(), engine.getCylinderQuantity(),
                        engine.getCylinderDiameter(), engine.getPistonStroke(), engine.getCompressionRatio(), engine.getInjectionType(),
                        engine.getInjectionPressure(), engine.getCylinderMaxPressure(), engine.getCylinderArrangement(),
                        engine.getCylinderDegrees(), engine.getWeightDryNoImplements(), engine.getWeightWithImplements(),
                        engine.getCoolingSystemType(), engine.getLength(), engine.getWidth(), engine.getHeight(),
                        engine.getOilRate(), engine.getOilSystemVolume(), engine.getCoolingSystemVolume(), engine.getImoEcoStandard(),
                        engine.getEpaEcoStandard(), engine.getEuEcoStandard(), engine.getUicEcoStandard(), engine.getVesselType(),
                        engine.getClassificationSocietyId(), engine.getFlangeId(), engine.getPathToImage())
                .returningResult(e.ENGINE_ID)
                .fetchOne();

        return engineIdRecord.getValue(e.ENGINE_ID);
    }

    public void updateEngine(Engine engine) {
         context.update(e)
                .set(e.MANUFACTURER_ID, engine.getManufacturerId())
                .set(e.SERIES, engine.getSeries())
                .set(e.MODEL, engine.getModel())
                .set(e.ASSIGNMENT_ID, engine.getAssignmentId())
                .set(e.RATING_ID, engine.getEngineRatingId())
                .set(e.OPERATING_TIME_YEAR, engine.getOperatingTimeYear())
                .set(e.OPERATING_TIME_FIRST_TS, engine.getOperatingTimeFirstTs())
                .set(e.OPERATING_TIME_TO_REPAIR, engine.getOperatingTimeToRepair())
                .set(e.POWER_RATING, engine.getPowerRating())
                .set(e.ROTATION_SPEED, engine.getRotationSpeed())
                .set(e.TORQUE_MAX, engine.getTorqueMax())
                .set(e.FUEL_RATE, engine.getFuelRate())
                .set(e.FUEL_RATE_NOMINAL_POWER, engine.getFuelRateNominalPower())
                .set(e.CYLINDER_WORKING_VOLUME, engine.getCylinderWorkingVolume())
                .set(e.CYLINDER_QUANTITY, engine.getCylinderQuantity())
                .set(e.CYLINDER_DIAMETER, engine.getCylinderDiameter())
                .set(e.PISTON_STROKE, engine.getPistonStroke())
                .set(e.COMPRESSION_RATIO, engine.getCompressionRatio())
                .set(e.INJECTION_TYPE, engine.getInjectionType())
                .set(e.INJECTION_PRESSURE, engine.getInjectionPressure())
                .set(e.CYLINDER_MAX_PRESSURE, engine.getCylinderMaxPressure())
                .set(e.CYLINDER_ARRANGEMENT, engine.getCylinderArrangement())
                .set(e.CYLINDER_DEGREES, engine.getCylinderDegrees())
                .set(e.WEIGHT_DRY_NO_IMPLEMENTS, engine.getWeightDryNoImplements())
                .set(e.WEIGHT_WITH_IMPLEMENTS, engine.getWeightWithImplements())
                .set(e.COOLING_SYSTEM_TYPE, engine.getCoolingSystemType())
                .set(e.LENGTH, engine.getLength())
                .set(e.WIDTH, engine.getWidth())
                .set(e.HEIGHT, engine.getHeight())
                .set(e.OIL_RATE, engine.getOilRate())
                .set(e.OIL_SYSTEM_VOLUME, engine.getOilSystemVolume())
                .set(e.COOLING_SYSTEM_VOLUME, engine.getCoolingSystemVolume())
                .set(e.IMO_ECO_STANDARD, engine.getImoEcoStandard())
                .set(e.EPA_ECO_STANDARD, engine.getEpaEcoStandard())
                .set(e.EU_ECO_STANDARD, engine.getEuEcoStandard())
                .set(e.UIC_ECO_STANDARD, engine.getUicEcoStandard())
                .set(e.VESSEL_TYPE, engine.getVesselType())
                .set(e.CLASSIFICATION_SOCIETY_ID, engine.getClassificationSocietyId())
                .set(e.FLANGE_ID, engine.getFlangeId())
                .set(e.PATH_TO_IMAGE, engine.getPathToImage())
                .where(e.ENGINE_ID.eq(engine.getEngineId()))
                .returning()
                .fetchOne();
    }

    public int deleteEngine(Long id) {
        return context.delete(e)
                      .where(e.ENGINE_ID.eq(id))
                      .execute();
    }

    public Optional<String> findImagePath(Long engineId) {
        return context
                .select(e.PATH_TO_IMAGE)
                .from(e)
                .where(e.ENGINE_ID.eq(engineId))
                .fetchOptionalInto(String.class);
    }
}