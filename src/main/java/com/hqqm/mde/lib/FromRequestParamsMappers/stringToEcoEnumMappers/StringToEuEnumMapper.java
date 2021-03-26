package com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers;

import com.hqqm.mde.jooq.enums.EuEcoStandards;

import java.util.Optional;

public class StringToEuEnumMapper {
    public static Optional<EuEcoStandards> convert(String s) {
        for (EuEcoStandards ecoStandard : EuEcoStandards.values())
            if (ecoStandard.getLiteral().equals(s))
                return Optional.of(ecoStandard);

        return Optional.empty();
    }
}
