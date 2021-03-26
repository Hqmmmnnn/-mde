package com.hqqm.mde.services;

import static org.jooq.impl.DSL.*;

import com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers.StringToEpaEnumMapper;
import com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers.StringToEuEnumMapper;
import com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers.StringToImoEnumMapper;
import com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers.StringToUicEnumMapper;

import com.hqqm.mde.DTOs.EngineDTO;
import com.hqqm.mde.jooq.enums.EpaEcoStandards;
import com.hqqm.mde.jooq.enums.EuEcoStandards;
import com.hqqm.mde.jooq.enums.ImoEcoStandards;
import com.hqqm.mde.jooq.enums.UicEcoStandards;
import com.hqqm.mde.jooq.tables.Engines;
import com.hqqm.mde.jooq.tables.Flanges;
import com.hqqm.mde.jooq.tables.Manufacturers;
import com.hqqm.mde.lib.FromRequestParamsMappers.StringToRangeMapper;
import com.hqqm.mde.repositories.EngineRepository;
import lombok.AllArgsConstructor;
import org.jooq.Condition;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EngineController {
    private final EngineRepository engineRepository;

    @GetMapping("/engines")
    public List<EngineDTO> getEngines(
            @RequestParam Optional<String> model, @RequestParam Optional<String> manufacturerName,
            @RequestParam Optional<String> powerRating, @RequestParam Optional<String> rotationSpeed,
            @RequestParam Optional<Integer> cylinderQuantity, @RequestParam Optional<String> weightDryNoImplements,
            @RequestParam Optional<String> length, @RequestParam Optional<String> width,
            @RequestParam Optional<String> height, @RequestParam Optional<String> flangeType,
            @RequestParam Optional<String> imoEcoStandard, @RequestParam Optional<String> epaEcoStandard,
            @RequestParam Optional<String> euEcoStandard, @RequestParam Optional<String> uicEcoStandard

    ) {
        Condition condition = trueCondition();

        if (model.isPresent())
            condition = condition.and(Engines.ENGINES_.MODEL.like("%" + model.get() + "%"));

        if (manufacturerName.isPresent())
            condition = condition.and(Manufacturers.MANUFACTURERS.NAME.eq(manufacturerName.get()));

        if (powerRating.isPresent()) {
            int[] range = StringToRangeMapper.convert(powerRating.get());
            condition = condition.and(Engines.ENGINES_.POWER_RATING.between(range[0]).and(range[1]));
        }

        if (rotationSpeed.isPresent()) {
            int[] range = StringToRangeMapper.convert(rotationSpeed.get());
            condition = condition.and(Engines.ENGINES_.ROTATION_SPEED.between(range[0]).and(range[1]));
        }

        if (cylinderQuantity.isPresent())
            condition = condition.and(Engines.ENGINES_.CYLINDER_QUANTITY.eq(cylinderQuantity.get()));

        if (weightDryNoImplements.isPresent()) {
            int[] range = StringToRangeMapper.convert(weightDryNoImplements.get());
            condition = condition.and(Engines.ENGINES_.WEIGHT_DRY_NO_IMPLEMENTS.between(range[0]).and(range[1]));
        }

        if (length.isPresent()) {
            int[] range = StringToRangeMapper.convert(length.get());
            condition = condition.and(Engines.ENGINES_.LENGTH.between(range[0]).and(range[1]));
        }

        if (width.isPresent()) {
            int[] range = StringToRangeMapper.convert(width.get());
            condition = condition.and(Engines.ENGINES_.WIDTH.between(range[0]).and(range[1]));
        }

        if (height.isPresent()) {
            int[] range = StringToRangeMapper.convert(height.get());
            condition = condition.and(Engines.ENGINES_.HEIGHT.between(range[0]).and(range[1]));
        }

        if (flangeType.isPresent())
            condition = condition.and(Flanges.FLANGES.TYPE.eq(flangeType.get()));

        if (imoEcoStandard.isPresent()) {
            Optional<ImoEcoStandards> imo = StringToImoEnumMapper.convert(imoEcoStandard.get());

            if (imo.isPresent())
                condition = condition.and(Engines.ENGINES_.IMO_ECO_STANDARD.eq(imo.get()));
        }

       if (epaEcoStandard.isPresent()) {
           Optional<EpaEcoStandards> epa = StringToEpaEnumMapper.convert(epaEcoStandard.get());

           if (epa.isPresent())
            condition = condition.and(Engines.ENGINES_.EPA_ECO_STANDARD.eq(epa.get()));
       }

        if (euEcoStandard.isPresent()) {
            Optional<EuEcoStandards> eu = StringToEuEnumMapper.convert(euEcoStandard.get());

            if (eu.isPresent())
                condition = condition.and(Engines.ENGINES_.EU_ECO_STANDARD.eq(eu.get()));
        }

        if (uicEcoStandard.isPresent()) {
            Optional<UicEcoStandards> uic = StringToUicEnumMapper.convert(uicEcoStandard.get());

            if (uic.isPresent())
                condition = condition.and(Engines.ENGINES_.UIC_ECO_STANDARD.eq(uic.get()));
        }

        return engineRepository.getEngines(condition);
    }
}


