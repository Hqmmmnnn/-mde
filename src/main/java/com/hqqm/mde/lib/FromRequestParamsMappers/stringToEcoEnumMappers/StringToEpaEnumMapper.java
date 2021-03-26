package com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers;

import com.hqqm.mde.jooq.enums.EpaEcoStandards;

import java.util.Optional;

public class StringToEpaEnumMapper {
    public static Optional<EpaEcoStandards> convert(String s) {
        for (EpaEcoStandards ecoStandard : EpaEcoStandards.values())
            if (ecoStandard.getLiteral().equals(s))
                return Optional.of(ecoStandard);

        return Optional.empty();
    }
}
