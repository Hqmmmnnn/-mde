package com.hqqm.mde.repositories.impl;

import com.hqqm.mde.models.*;
import com.hqqm.mde.jooq.tables.*;
import com.hqqm.mde.repositories.EngineRepository;
import lombok.AllArgsConstructor;
import static org.jooq.impl.DSL.*;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JooqEngineRepository implements EngineRepository {
    private final DSLContext context;

    private final Engines e = Engines.ENGINES_;
    private final CylinderQuantity cq = CylinderQuantity.CYLINDER_QUANTITY;
    private final CylinderArrangements cylinderArrangements = CylinderArrangements.CYLINDER_ARRANGEMENTS;
    private final InjectionTypes injectionTypes = InjectionTypes.INJECTION_TYPES;
    private final VesselTypes vesselTypes = VesselTypes.VESSEL_TYPES;
    private final CoolingSystemTypes coolingSystemTypes = CoolingSystemTypes.COOLING_SYSTEM_TYPES;
    private final ImoEcoStandard imo = ImoEcoStandard.IMO_ECO_STANDARD;
    private final EpaEcoStandard epa = EpaEcoStandard.EPA_ECO_STANDARD;
    private final EuEcoStandard eu = EuEcoStandard.EU_ECO_STANDARD;
    private final UicEcoStandard uic = UicEcoStandard.UIC_ECO_STANDARD;
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
                e.ROTATION_FREQUENCY,
                m.NAME.as("manufacturerName"),
                e.TORQUE_MAX,
                a.ASSIGNMENT,
                r.LOAD_MODE,
                f.TYPE.as("flangeType"),
                // fuel consumption
                e.FUEL_RATE,
                e.FUEL_RATE_NOMINAL_POWER,
                // cylinder
                e.CYLINDER_WORKING_VOLUME,
                cq.QUANTITY.as("cylinderQuantity"),
                e.CYLINDER_DIAMETER,
                e.PISTON_STROKE,
                e.COMPRESSION_RATIO,
                e.CYLINDER_MAX_PRESSURE,
                cylinderArrangements.ARRANGEMENT.as("cylinderArrangement"),
                e.CYLINDER_DEGREES,
                // injection
                injectionTypes.TYPE.as("injectionType"),
                e.INJECTION_PRESSURE,
                // dimensions
                e.LENGTH,
                e.WIDTH,
                e.HEIGHT,
                // weight
                e.WEIGHT_DRY_NO_IMPLEMENTS,
                // cooling
                coolingSystemTypes.TYPE.as("coolingSystemType"),
                e.COOLING_SYSTEM_VOLUME,
                // oil
                e.OIL_RATE,
                e.OIL_SYSTEM_VOLUME,
                // eco standards
                imo.QUOTE_NAME.as("imoEcoStandard"),
                epa.QUOTE_NAME.as("epaEcoStandard"),
                eu.QUOTE_NAME.as("euEcoStandard"),
                uic.QUOTE_NAME.as("uicEcoStandard"),
                vesselTypes.TYPE.as("vesselType"),
                cs.NAME.as("classificationSociety")
        )
                .from(e)
                .leftJoin(cq).using(e.CYLINDER_QUANTITY_ID)
                .leftJoin(cylinderArrangements).using(e.CYLINDER_ARRANGEMENT_ID)
                .leftJoin(injectionTypes).using(e.INJECTION_TYPE_ID)
                .leftJoin(vesselTypes).using(e.VESSEL_TYPE_ID)
                .leftJoin(coolingSystemTypes).using(e.COOLING_SYSTEM_TYPE_ID)
                .leftJoin(imo).using(e.IMO_ECO_STANDARD_ID)
                .leftJoin(epa).using(e.EPA_ECO_STANDARD_ID)
                .leftJoin(eu).using(e.EU_ECO_STANDARD_ID)
                .leftJoin(uic).using(e.UIC_ECO_STANDARD_ID)
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

    @Override
    public List<EngineInfoTable> getEngine(Long id) {
        var res = context.select(
                //general
                e.ENGINE_ID,
                e.SERIES,
                e.MODEL,
                e.POWER_RATING,
                e.ROTATION_FREQUENCY,
                m.NAME,
                e.TORQUE_MAX,
                a.ASSIGNMENT,
                r.LOAD_MODE,
                f.TYPE,
                // recommended operating time
                e.OPERATING_TIME_YEAR,
                e.OPERATING_TIME_FIRST_TS,
                e.OPERATING_TIME_TO_REPAIR,
                // fuel consumption
                e.FUEL_RATE,
                e.FUEL_RATE_NOMINAL_POWER,
                // cylinder
                e.CYLINDER_WORKING_VOLUME,
                cq.QUANTITY,
                e.CYLINDER_DIAMETER,
                e.PISTON_STROKE,
                e.COMPRESSION_RATIO,
                e.CYLINDER_MAX_PRESSURE,
                cylinderArrangements.ARRANGEMENT,
                e.CYLINDER_DEGREES,
                // injection
                injectionTypes.TYPE,
                e.INJECTION_PRESSURE,
                // dimensions
                e.LENGTH,
                e.WIDTH,
                e.HEIGHT,
                // weight
                e.WEIGHT_DRY_NO_IMPLEMENTS,
                e.WEIGHT_WITH_IMPLEMENTS,
                // cooling
                coolingSystemTypes.TYPE,
                e.COOLING_SYSTEM_VOLUME,
                // oil
                e.OIL_RATE,
                e.OIL_SYSTEM_VOLUME,
                // eco standards
                imo.QUOTE_NAME,
                epa.QUOTE_NAME,
                eu.QUOTE_NAME,
                uic.QUOTE_NAME,
                // other
                vesselTypes.TYPE,
                cs.NAME,
                e.NOTE,
                e.CREATED_AT,
                e.LAST_UPDATE)
                .from(e)
                .leftJoin(cq).using(e.CYLINDER_QUANTITY_ID)
                .leftJoin(cylinderArrangements).using(e.CYLINDER_ARRANGEMENT_ID)
                .leftJoin(injectionTypes).using(e.INJECTION_TYPE_ID)
                .leftJoin(vesselTypes).using(e.VESSEL_TYPE_ID)
                .leftJoin(coolingSystemTypes).using(e.COOLING_SYSTEM_TYPE_ID)
                .leftJoin(imo).using(e.IMO_ECO_STANDARD_ID)
                .leftJoin(epa).using(e.EPA_ECO_STANDARD_ID)
                .leftJoin(eu).using(e.EU_ECO_STANDARD_ID)
                .leftJoin(uic).using(e.UIC_ECO_STANDARD_ID)
                .leftJoin(m).using(e.MANUFACTURER_ID)
                .leftJoin(a).using(e.ASSIGNMENT_ID)
                .leftJoin(r).using(e.RATING_ID)
                .leftJoin(cs).using(e.CLASSIFICATION_SOCIETY_ID)
                .leftJoin(f).using(e.FLANGE_ID)
                .where(e.ENGINE_ID.eq(id))
                .fetchOne();

        var model = new EngineInfoRow("Модель", res.getValue(e.MODEL));
        var series = new EngineInfoRow("Серия", res.getValue(e.SERIES));
        var powerRating = new EngineInfoRow("Мощность кВт", res.getValue(e.POWER_RATING));
        var rotationFrequency = new EngineInfoRow("Частота вращения об/мин", res.getValue(e.ROTATION_FREQUENCY));
        var manufacturerName = new EngineInfoRow("Производитель", res.getValue(m.NAME));
        var torqueMax = new EngineInfoRow("Макс. крутящий момент, нм", res.getValue(e.TORQUE_MAX));
        var assignment = new EngineInfoRow("Назначение", res.getValue(a.ASSIGNMENT));
        var loadMode = new EngineInfoRow("Рейтинг", res.getValue(r.LOAD_MODE));
        var flangeType = new EngineInfoRow("Фланец", res.getValue(f.TYPE));

        String generalBlockName = "Основные характеристики";
        var generalBlock = new EngineInfoTable(
                generalBlockName,
                Arrays.asList(model, series, powerRating, rotationFrequency, manufacturerName, torqueMax,
                              assignment, loadMode, flangeType)
        );

        var operatingTimeYear = new EngineInfoRow("В год, ч", res.getValue(e.OPERATING_TIME_YEAR));
        var operatingTimeFirstTs = new EngineInfoRow("До первого ТО, ч", res.getValue(e.OPERATING_TIME_FIRST_TS));
        var operatingTimeToRepair = new EngineInfoRow("До кап. ремонта, ч", res.getValue(e.OPERATING_TIME_TO_REPAIR));

        String recommendedOperatingTimeBlockName = "Рекомендуемая наработка";
        var recommendedOperatingTimeBlock = new EngineInfoTable(
                recommendedOperatingTimeBlockName,
                Arrays.asList(operatingTimeYear, operatingTimeFirstTs, operatingTimeToRepair));


        var fuelRate = new EngineInfoRow("Удельный, г/кВт ч", res.getValue(e.FUEL_RATE));
        var fuelRateNominalPower = new EngineInfoRow("Номинальной мощности, л/ч", res.getValue(e.FUEL_RATE_NOMINAL_POWER));
        String fuelRateBlockName = "Расход топлива";
        var fuelRateBlock = new EngineInfoTable(fuelRateBlockName, Arrays.asList(fuelRate, fuelRateNominalPower));


        var cylinderWorkingVolume = new EngineInfoRow("Рабочий объем, л", res.getValue(e.CYLINDER_WORKING_VOLUME));
        var cylinderQuantity = new EngineInfoRow("Количество", res.getValue(cq.QUANTITY));
        var cylinderDiameter = new EngineInfoRow("Диаметр, мм", res.getValue(e.CYLINDER_DIAMETER));
        var pistonStroke = new EngineInfoRow("Ход поршня, мм", res.getValue(e.PISTON_STROKE));
        var compressionRation = new EngineInfoRow("Степень сжатия", res.getValue(e.COMPRESSION_RATIO));
        var cylinderMaxPressure = new EngineInfoRow("Макс. давление (Pz), bar", res.getValue(e.CYLINDER_MAX_PRESSURE));
        var cylinderArrangement = new EngineInfoRow("Расположение", res.getValue(cylinderArrangements.ARRANGEMENT));
        var cylinderDegrees = new EngineInfoRow("Развал, град", res.getValue(e.CYLINDER_DEGREES));
        String cylinderBlockName = "Цилиндр";
        var cylinderBlock = new EngineInfoTable(
                cylinderBlockName,
                Arrays.asList(cylinderWorkingVolume, cylinderQuantity, cylinderDiameter, pistonStroke,
                              compressionRation, cylinderMaxPressure, cylinderArrangement, cylinderDegrees)
        );

        var injectionType = new EngineInfoRow("Тип", res.getValue(injectionTypes.TYPE));
        var injectionPressure = new EngineInfoRow("Давление, бар", res.getValue(e.INJECTION_PRESSURE));
        String injectionBlockName = "Впрыск";
        var injectionBlock = new EngineInfoTable(injectionBlockName, Arrays.asList(injectionType, injectionPressure));

        var length = new EngineInfoRow("Длина", res.getValue(e.LENGTH));
        var width = new EngineInfoRow("Ширина", res.getValue(e.WIDTH));
        var height = new EngineInfoRow("Высота", res.getValue(e.HEIGHT));
        String dimensionsBlockName = "Габариты, мм";
        var dimensionsBlock = new EngineInfoTable(dimensionsBlockName, Arrays.asList(length, width, height));

        var weightDryNoImplements = new EngineInfoRow("Без оборудования", res.getValue(e.WEIGHT_DRY_NO_IMPLEMENTS));
        var weightWithImplements = new EngineInfoRow("C оборудованием", res.getValue(e.WEIGHT_WITH_IMPLEMENTS));
        String weightBlockName = "Масса, кг";
        var weightBlock = new EngineInfoTable(weightBlockName, Arrays.asList(weightDryNoImplements, weightWithImplements));

        var coolingSystemType = new EngineInfoRow("Тип", res.getValue(coolingSystemTypes.TYPE));
        var coolingSystemVolume = new EngineInfoRow("Объем, л", res.getValue(e.COOLING_SYSTEM_VOLUME));
        String coolingSystemBlockName = "Охлаждение";
        var coolingSystemBlock = new EngineInfoTable(coolingSystemBlockName, Arrays.asList(coolingSystemType, coolingSystemVolume));

        var oilRate = new EngineInfoRow("Расход на угар, г/кВт ч", res.getValue(e.OIL_RATE));
        var oilSystemVolume = new EngineInfoRow("Объем системы, л", res.getValue(e.OIL_SYSTEM_VOLUME));
        String oilSystemBlockName = "Система смазки";
        var oilSystemBlock = new EngineInfoTable(oilSystemBlockName, Arrays.asList(oilRate, oilSystemVolume));

        var imoEcoStandard = new EngineInfoRow("IMO", res.getValue(imo.QUOTE_NAME));
        var epaEcoStandard = new EngineInfoRow("EPA", res.getValue(epa.QUOTE_NAME));
        var euEcoStandard = new EngineInfoRow("EU", res.getValue(eu.QUOTE_NAME));
        var uicEcoStandard = new EngineInfoRow("UIC", res.getValue(uic.QUOTE_NAME));
        String ecoStandardsBlockName = "Эконормы";
        var ecoStandardsBlock = new EngineInfoTable(
                ecoStandardsBlockName,
                Arrays.asList(imoEcoStandard, epaEcoStandard, euEcoStandard, uicEcoStandard)
        );

        var vesselType = new EngineInfoRow("Тип судна", res.getValue(vesselTypes.TYPE));
        var classificationSociety = new EngineInfoRow("Классифиционное общество", res.getValue(cs.NAME));
        var note = new EngineInfoRow("Примечание", res.getValue(e.NOTE));
        var createdAt = new EngineInfoRow("Дата добавления двигателя", res.getValue(e.CREATED_AT));
        var lastUpdate = new EngineInfoRow("Дата последнего обновления", res.getValue(e.LAST_UPDATE));
        String otherBlockName = "Другое";
        var otherBlock = new EngineInfoTable(
                otherBlockName,
                Arrays.asList(vesselType, classificationSociety, note, createdAt, lastUpdate));

        return Arrays.asList(
                generalBlock, recommendedOperatingTimeBlock, fuelRateBlock, cylinderBlock, injectionBlock,
                dimensionsBlock, weightBlock, coolingSystemBlock, oilSystemBlock, ecoStandardsBlock, otherBlock
        );
    }

    public Long saveEngine(Engine engine) {
        var engineIdRecord = context
                .insertInto(e,
                        e.MANUFACTURER_ID, e.SERIES, e.MODEL, e.ASSIGNMENT_ID, e.RATING_ID,
                        e.OPERATING_TIME_YEAR, e.OPERATING_TIME_FIRST_TS, e.OPERATING_TIME_TO_REPAIR,
                        e.POWER_RATING, e.ROTATION_FREQUENCY, e.TORQUE_MAX, e.FUEL_RATE,
                        e.FUEL_RATE_NOMINAL_POWER, e.CYLINDER_WORKING_VOLUME, e.CYLINDER_QUANTITY_ID,
                        e.CYLINDER_DIAMETER, e.PISTON_STROKE, e.COMPRESSION_RATIO, e.INJECTION_TYPE_ID,
                        e.INJECTION_PRESSURE, e.CYLINDER_MAX_PRESSURE, e.CYLINDER_ARRANGEMENT_ID,
                        e.CYLINDER_DEGREES, e.WEIGHT_DRY_NO_IMPLEMENTS, e.WEIGHT_WITH_IMPLEMENTS,
                        e.COOLING_SYSTEM_TYPE_ID, e.LENGTH, e.WIDTH, e.HEIGHT,
                        e.OIL_RATE, e.OIL_SYSTEM_VOLUME, e.COOLING_SYSTEM_VOLUME, e.IMO_ECO_STANDARD_ID,
                        e.EPA_ECO_STANDARD_ID, e.EU_ECO_STANDARD_ID, e.UIC_ECO_STANDARD_ID, e.VESSEL_TYPE_ID,
                        e.CLASSIFICATION_SOCIETY_ID, e.FLANGE_ID, e.PATH_TO_IMAGE, e.NOTE,
                        e.CREATED_AT, e.LAST_UPDATE)
                .values(engine.getManufacturerId(), engine.getSeries(), engine.getModel(), engine.getAssignmentId(), engine.getEngineRatingId(),
                        engine.getOperatingTimeYear(), engine.getOperatingTimeFirstTs(), engine.getOperatingTimeToRepair(),
                        engine.getPowerRating(), engine.getRotationFrequency(), engine.getTorqueMax(), engine.getFuelRate(),
                        engine.getFuelRateNominalPower(), engine.getCylinderWorkingVolume(), engine.getCylinderQuantityId(),
                        engine.getCylinderDiameter(), engine.getPistonStroke(), engine.getCompressionRatio(), engine.getInjectionTypeId(),
                        engine.getInjectionPressure(), engine.getCylinderMaxPressure(), engine.getCylinderArrangementId(),
                        engine.getCylinderDegrees(), engine.getWeightDryNoImplements(), engine.getWeightWithImplements(),
                        engine.getCoolingSystemTypeId(), engine.getLength(), engine.getWidth(), engine.getHeight(),
                        engine.getOilRate(), engine.getOilSystemVolume(), engine.getCoolingSystemVolume(), engine.getImoEcoStandardId(),
                        engine.getEpaEcoStandardId(), engine.getEuEcoStandardId(), engine.getUicEcoStandardId(), engine.getVesselTypeId(),
                        engine.getClassificationSocietyId(), engine.getFlangeId(), engine.getPathToImage(), engine.getNote(),
                        currentTimestamp(), currentTimestamp())
                .returningResult(e.ENGINE_ID)
                .fetchOne();

        return engineIdRecord.getValue(e.ENGINE_ID);
    }

    public void updateEngine(UpdateEngineDTO engine) {
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
                .set(e.ROTATION_FREQUENCY, engine.getRotationFrequency())
                .set(e.TORQUE_MAX, engine.getTorqueMax())
                .set(e.FUEL_RATE, engine.getFuelRate())
                .set(e.FUEL_RATE_NOMINAL_POWER, engine.getFuelRateNominalPower())
                .set(e.CYLINDER_WORKING_VOLUME, engine.getCylinderWorkingVolume())
                .set(e.CYLINDER_QUANTITY_ID, engine.getCylinderQuantityId())
                .set(e.CYLINDER_DIAMETER, engine.getCylinderDiameter())
                .set(e.PISTON_STROKE, engine.getPistonStroke())
                .set(e.COMPRESSION_RATIO, engine.getCompressionRatio())
                .set(e.INJECTION_TYPE_ID, engine.getInjectionTypeId())
                .set(e.INJECTION_PRESSURE, engine.getInjectionPressure())
                .set(e.CYLINDER_MAX_PRESSURE, engine.getCylinderMaxPressure())
                .set(e.CYLINDER_ARRANGEMENT_ID, engine.getCylinderArrangementId())
                .set(e.CYLINDER_DEGREES, engine.getCylinderDegrees())
                .set(e.WEIGHT_DRY_NO_IMPLEMENTS, engine.getWeightDryNoImplements())
                .set(e.WEIGHT_WITH_IMPLEMENTS, engine.getWeightWithImplements())
                .set(e.COOLING_SYSTEM_TYPE_ID, engine.getCoolingSystemTypeId())
                .set(e.LENGTH, engine.getLength())
                .set(e.WIDTH, engine.getWidth())
                .set(e.HEIGHT, engine.getHeight())
                .set(e.OIL_RATE, engine.getOilRate())
                .set(e.OIL_SYSTEM_VOLUME, engine.getOilSystemVolume())
                .set(e.COOLING_SYSTEM_VOLUME, engine.getCoolingSystemVolume())
                .set(e.IMO_ECO_STANDARD_ID, engine.getImoEcoStandardId())
                .set(e.EPA_ECO_STANDARD_ID, engine.getEpaEcoStandardId())
                .set(e.EU_ECO_STANDARD_ID, engine.getEuEcoStandardId())
                .set(e.UIC_ECO_STANDARD_ID, engine.getUicEcoStandardId())
                .set(e.VESSEL_TYPE_ID, engine.getVesselTypeId())
                .set(e.CLASSIFICATION_SOCIETY_ID, engine.getClassificationSocietyId())
                .set(e.FLANGE_ID, engine.getFlangeId())
                .where(e.ENGINE_ID.eq(engine.getEngineId()))
                .execute();
    }

    public UpdateEngineDTO getEngineDataForUpdate(Long id) {
        return context
                .select(e.MANUFACTURER_ID, e.SERIES, e.MODEL, e.ASSIGNMENT_ID, e.RATING_ID.as("engineRatingId"), e.OPERATING_TIME_YEAR,
                       e.OPERATING_TIME_FIRST_TS , e.OPERATING_TIME_TO_REPAIR, e.POWER_RATING, e.ROTATION_FREQUENCY,
                       e.TORQUE_MAX, e.FUEL_RATE, e.FUEL_RATE_NOMINAL_POWER, e.CYLINDER_WORKING_VOLUME,
                       e.CYLINDER_QUANTITY_ID, e.CYLINDER_DIAMETER, e.PISTON_STROKE, e.COMPRESSION_RATIO,
                       e.INJECTION_TYPE_ID, e.INJECTION_PRESSURE, e.CYLINDER_MAX_PRESSURE, e.CYLINDER_ARRANGEMENT_ID,
                       e.CYLINDER_DEGREES, e.WEIGHT_DRY_NO_IMPLEMENTS, e.WEIGHT_WITH_IMPLEMENTS, e.COOLING_SYSTEM_TYPE_ID,
                       e.LENGTH, e.WIDTH, e.HEIGHT, e.OIL_RATE, e.OIL_SYSTEM_VOLUME, e.COOLING_SYSTEM_VOLUME,
                       e.IMO_ECO_STANDARD_ID, e.EPA_ECO_STANDARD_ID, e.EU_ECO_STANDARD_ID, e.UIC_ECO_STANDARD_ID,
                       e.VESSEL_TYPE_ID, e.CLASSIFICATION_SOCIETY_ID, e.FLANGE_ID)
                .from(e)
                .where(e.ENGINE_ID.eq(id))
                .fetchOne()
                .into(UpdateEngineDTO.class);
    }

    public Optional<String> deleteEngine(Long id) {
        var engine =  context.deleteFrom(e)
                      .where(e.ENGINE_ID.eq(id))
                      .returning()
                      .fetchOne();

        var imgPath = engine.getPathToImage();
        if (imgPath == null) {
            return  Optional.empty();
        }

        return Optional.of(imgPath);
    }

    public Optional<String> findImagePath(Long engineId) {
        return context
                .select(e.PATH_TO_IMAGE)
                .from(e)
                .where(e.ENGINE_ID.eq(engineId))
                .fetchOptionalInto(String.class);
    }

    public String deleteEngineImage(Long engineId) {
        var path = context
                .select(e.PATH_TO_IMAGE)
                .from(e)
                .where(e.ENGINE_ID.eq(engineId))
                .fetchOne()
                .getValue(e.PATH_TO_IMAGE);

        context
                .update(e)
                .setNull(e.PATH_TO_IMAGE)
                .where(e.ENGINE_ID.eq(engineId))
                .execute();

        return path;
    }

    public void updateEngineImage(String pathToImg, Long engineId) {
        context
                .update(e)
                .set(e.PATH_TO_IMAGE, pathToImg)
                .where(e.ENGINE_ID.eq(engineId))
                .execute();
    }
}

