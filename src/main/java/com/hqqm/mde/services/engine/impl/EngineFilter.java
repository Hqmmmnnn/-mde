package com.hqqm.mde.services.engine.impl;

import com.hqqm.mde.jooq.tables.*;
import com.hqqm.mde.lib.FromRequestParamsMappers.FromReqListOfStringConverter;
import com.hqqm.mde.models.RequestParamsForEngineFiltration;
import com.hqqm.mde.lib.FromRequestParamsMappers.StringToRangeConverter;

import org.jooq.Condition;

import java.util.List;

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
        var manufacturerNames = e.getManufacturerNames();
        var powerRating = e.getPowerRating();
        var rotationFrequencies = e.getRotationFrequencies();
        var cylinderQuantity = e.getCylindersQuantity();
        var weightDryNoImplements = e.getWeightDryNoImplements();
        var length = e.getLength();
        var width = e.getWidth();
        var height = e.getHeight();
        var flangeTypes = e.getFlangeTypes();
        var imoEcoStandards = e.getImoEcoStandards();
        var epaEcoStandards = e.getEpaEcoStandards();
        var euEcoStandards = e.getEuEcoStandards();
        var uicEcoStandards = e.getUicEcoStandards();

        if (model != null)
            condition = condition.and(Engines.ENGINES_.MODEL.likeIgnoreCase("%" + model + "%"));

        if (manufacturerNames != null && manufacturerNames.size() > 0) {
            List<String> formattedManufacturers = FromReqListOfStringConverter.toFormattedStrings(manufacturerNames);
            condition = condition.and(Manufacturers.MANUFACTURERS.NAME.in(formattedManufacturers));
        }

        if (powerRating != null) {
            int[] range = StringToRangeConverter.convert(powerRating);
            condition = condition.and(Engines.ENGINES_.POWER_RATING.between(range[0]).and(range[1]));
        }

        if (rotationFrequencies != null) {
            int[] range = StringToRangeConverter.convert(rotationFrequencies);
            condition = condition.and(Engines.ENGINES_.ROTATION_FREQUENCY.between(range[0]).and(range[1]));
        }

        if (cylinderQuantity != null && cylinderQuantity.size() > 0) {
            List<Integer> cylinders = FromReqListOfStringConverter.toIntegers(cylinderQuantity);
            condition = condition.and(CylinderQuantity.CYLINDER_QUANTITY.QUANTITY.in(cylinders));
        }

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

        if (flangeTypes != null && flangeTypes.size() > 0) {
            List<String> formattedFlangeTypes = FromReqListOfStringConverter.toFormattedStrings(flangeTypes);
            condition = condition.and(Flanges.FLANGES.TYPE.in(formattedFlangeTypes));
        }

        if (imoEcoStandards != null && imoEcoStandards.size() > 0) {
            List<String> formattedImoEcoStandards = FromReqListOfStringConverter.toFormattedStrings(imoEcoStandards);
            condition = condition.and(ImoEcoStandard.IMO_ECO_STANDARD.QUOTE_NAME.in(formattedImoEcoStandards));
        }

        if (epaEcoStandards != null && epaEcoStandards.size() > 0) {
            List<String> formattedEpaEcoStandards = FromReqListOfStringConverter.toFormattedStrings(epaEcoStandards);
            condition = condition.and(EpaEcoStandard.EPA_ECO_STANDARD.QUOTE_NAME.in(formattedEpaEcoStandards));
        }

        if (euEcoStandards != null && euEcoStandards.size() > 0) {
            List<String>  formattedEuEcoStandards = FromReqListOfStringConverter.toFormattedStrings(euEcoStandards);
            condition = condition.and(EuEcoStandard.EU_ECO_STANDARD.QUOTE_NAME.in(formattedEuEcoStandards));
        }

        if (uicEcoStandards != null && uicEcoStandards.size() > 0) {
            List<String> formattedUicEcoStandards = FromReqListOfStringConverter.toFormattedStrings(uicEcoStandards);
            condition = condition.and(UicEcoStandard.UIC_ECO_STANDARD.QUOTE_NAME.in(formattedUicEcoStandards));
        }

        return condition;
    }
}