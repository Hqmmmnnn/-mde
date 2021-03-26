package com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers;

import com.hqqm.mde.jooq.enums.UicEcoStandards;

import java.util.Optional;

public class StringToUicEnumMapper {
    public static Optional<UicEcoStandards> convert(String s) {
        for (UicEcoStandards ecoStandard : UicEcoStandards.values())
            if (ecoStandard.getLiteral().equals(s))
                return Optional.of(ecoStandard);

        return Optional.empty();
    }
}
