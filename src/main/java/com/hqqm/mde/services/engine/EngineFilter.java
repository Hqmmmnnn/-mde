package com.hqqm.mde.services.engine;

import com.hqqm.mde.controllers.engine.RequestParamsForEngineFiltration;
import com.hqqm.mde.jooq.enums.EpaEcoStandards;
import com.hqqm.mde.jooq.enums.EuEcoStandards;
import com.hqqm.mde.jooq.enums.ImoEcoStandards;
import com.hqqm.mde.jooq.enums.UicEcoStandards;
import com.hqqm.mde.jooq.tables.Engines;
import com.hqqm.mde.jooq.tables.Flanges;
import com.hqqm.mde.jooq.tables.Manufacturers;
import com.hqqm.mde.lib.FromRequestParamsMappers.StringToEnumConverter;
import com.hqqm.mde.lib.FromRequestParamsMappers.StringToRangeConverter;

import org.jooq.Condition;
import static org.jooq.impl.DSL.trueCondition;

class EngineFilter {
    private final RequestParamsForEngineFiltration e;
    private Condition condition;
    private final Long lastFetchedEngineId;

    public EngineFilter(RequestParamsForEngineFiltration e) {
        this.e = e;
        condition = trueCondition();
        lastFetchedEngineId = e.getLastFetchedEngineId() == null ? 0 : e.getLastFetchedEngineId();
    }

    public Long getLastFetchedEngineId() {
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
            int[] range = StringToRangeConverter.convert(powerRating);
            condition = condition.and(Engines.ENGINES_.POWER_RATING.between(range[0]).and(range[1]));
        }

        if (rotationSpeed != null) {
            condition = condition.and(Engines.ENGINES_.ROTATION_SPEED.eq(rotationSpeed));
        }

        if (cylinderQuantity != null)
            condition = condition.and(Engines.ENGINES_.CYLINDER_QUANTITY.eq(cylinderQuantity));

        if (weightDryNoImplements != null) {
            int[] range = StringToRangeConverter.convert(weightDryNoImplements);
            condition = condition.and(Engines.ENGINES_.WEIGHT_DRY_NO_IMPLEMENTS.between(range[0]).and(range[1]));
        }

        if (length != null) {
            int[] range = StringToRangeConverter.convert(length);
            condition = condition.and(Engines.ENGINES_.LENGTH.between(range[0]).and(range[1]));
        }

        if (width != null) {
            int[] range = StringToRangeConverter.convert(width);
            condition = condition.and(Engines.ENGINES_.WIDTH.between(range[0]).and(range[1]));
        }

        if (height != null) {
            int[] range = StringToRangeConverter.convert(height);
            condition = condition.and(Engines.ENGINES_.HEIGHT.between(range[0]).and(range[1]));
        }

        if (flangeType != null)
            condition = condition.and(Flanges.FLANGES.TYPE.eq(flangeType));

        if (imoEcoStandard != null) {
            ImoEcoStandards imo = StringToEnumConverter.convert(imoEcoStandard, ImoEcoStandards.values());
            if (imo != null)
                condition = condition.and(Engines.ENGINES_.IMO_ECO_STANDARD.eq(imo));
        }

        if (epaEcoStandard != null) {
            EpaEcoStandards epa = StringToEnumConverter.convert(epaEcoStandard, EpaEcoStandards.values());
            if (epa != null)
                condition = condition.and(Engines.ENGINES_.EPA_ECO_STANDARD.eq(epa));
        }

        if (euEcoStandard != null) {
            EuEcoStandards eu = StringToEnumConverter.convert(euEcoStandard, EuEcoStandards.values());
            if (eu != null)
                condition = condition.and(Engines.ENGINES_.EU_ECO_STANDARD.eq(eu));
        }

        if (uicEcoStandard != null) {
            UicEcoStandards uic = StringToEnumConverter.convert(uicEcoStandard, UicEcoStandards.values());
            if (uic != null)
                condition = condition.and(Engines.ENGINES_.UIC_ECO_STANDARD.eq(uic));
        }

        return condition;
    }
}