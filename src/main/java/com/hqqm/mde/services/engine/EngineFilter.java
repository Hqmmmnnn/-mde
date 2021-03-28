package com.hqqm.mde.services.engine;

import com.hqqm.mde.controllers.engine.EngineRequestParamsForFiltration;
import com.hqqm.mde.jooq.enums.EpaEcoStandards;
import com.hqqm.mde.jooq.enums.EuEcoStandards;
import com.hqqm.mde.jooq.enums.ImoEcoStandards;
import com.hqqm.mde.jooq.enums.UicEcoStandards;
import com.hqqm.mde.jooq.tables.Engines;
import com.hqqm.mde.jooq.tables.Flanges;
import com.hqqm.mde.jooq.tables.Manufacturers;
import com.hqqm.mde.lib.FromRequestParamsMappers.StringToRangeMapper;
import com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers.StringToEpaEnumMapper;
import com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers.StringToEuEnumMapper;
import com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers.StringToImoEnumMapper;
import com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers.StringToUicEnumMapper;
import org.jooq.Condition;

import java.util.Optional;

import static org.jooq.impl.DSL.trueCondition;

class EngineFilter {
    private final EngineRequestParamsForFiltration e;
    private Condition condition;
    private final Integer lastFetchedEngineId;

    public EngineFilter(EngineRequestParamsForFiltration e) {
        this.e = e;
        condition = trueCondition();
        lastFetchedEngineId = e.getLastFetchedEngineId() == null ? 0 : e.getLastFetchedEngineId();
    }

    public Integer getLastFetchedEngineId() {
        return lastFetchedEngineId;
    }

    public Condition getCondition() {
        var model = e.getModel();
        var manufacturerName = e.getManufacturerName();
        var powerRating = e.getPowerRating();
        var rotationSpeed = e.getRotationSpeed();
        var cylinderQuantity = e.getCylinderQuantity();
        var weightDryNoImplements = e.getWeightDryNoImplements();
        var length = e.getLength();
        var width = e.getWidth();
        var height = e.getHeight();
        var flangeType = e.getFlangeType();
        var imoEcoStandard = e.getImoEcoStandard();
        var epaEcoStandard = e.getEpaEcoStandard();
        var euEcoStandard = e.getEuEcoStandard();
        var uicEcoStandard = e.getUicEcoStandard();

        if (model != null)
            condition = condition.and(Engines.ENGINES_.MODEL.like("%" + model + "%"));

        if (manufacturerName != null)
            condition = condition.and(Manufacturers.MANUFACTURERS.NAME.eq(manufacturerName));

        if (powerRating != null) {
            int[] range = StringToRangeMapper.convert(powerRating);
            condition = condition.and(Engines.ENGINES_.POWER_RATING.between(range[0]).and(range[1]));
        }

        if (rotationSpeed != null) {
            condition = condition.and(Engines.ENGINES_.ROTATION_SPEED.eq(rotationSpeed));
        }

        if (cylinderQuantity != null)
            condition = condition.and(Engines.ENGINES_.CYLINDER_QUANTITY.eq(cylinderQuantity));

        if (weightDryNoImplements != null) {
            int[] range = StringToRangeMapper.convert(weightDryNoImplements);
            condition = condition.and(Engines.ENGINES_.WEIGHT_DRY_NO_IMPLEMENTS.between(range[0]).and(range[1]));
        }

        if (length != null) {
            int[] range = StringToRangeMapper.convert(length);
            condition = condition.and(Engines.ENGINES_.LENGTH.between(range[0]).and(range[1]));
        }

        if (width != null) {
            int[] range = StringToRangeMapper.convert(width);
            condition = condition.and(Engines.ENGINES_.WIDTH.between(range[0]).and(range[1]));
        }

        if (height != null) {
            int[] range = StringToRangeMapper.convert(height);
            condition = condition.and(Engines.ENGINES_.HEIGHT.between(range[0]).and(range[1]));
        }

        if (flangeType != null)
            condition = condition.and(Flanges.FLANGES.TYPE.eq(flangeType));

        if (imoEcoStandard != null) {
            Optional<ImoEcoStandards> imo = StringToImoEnumMapper.convert(imoEcoStandard);
            imo.ifPresent(imoEcoStandards -> condition = condition.and(Engines.ENGINES_.IMO_ECO_STANDARD.eq(imoEcoStandards)));
        }

        if (epaEcoStandard != null) {
            Optional<EpaEcoStandards> epa = StringToEpaEnumMapper.convert(epaEcoStandard);
            epa.ifPresent(epaEcoStandards -> condition = condition.and(Engines.ENGINES_.EPA_ECO_STANDARD.eq(epaEcoStandards)));
        }

        if (euEcoStandard != null) {
            Optional<EuEcoStandards> eu = StringToEuEnumMapper.convert(euEcoStandard);
            eu.ifPresent(euEcoStandards -> condition = condition.and(Engines.ENGINES_.EU_ECO_STANDARD.eq(euEcoStandards)));
        }

        if (uicEcoStandard != null) {
            Optional<UicEcoStandards> uic = StringToUicEnumMapper.convert(uicEcoStandard);
            uic.ifPresent(uicEcoStandards -> condition = condition.and(Engines.ENGINES_.UIC_ECO_STANDARD.eq(uicEcoStandards)));
        }

        return condition;
    }
}