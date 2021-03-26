package com.hqqm.mde.lib.FromRequestParamsMappers.stringToEcoEnumMappers;

import com.hqqm.mde.jooq.enums.ImoEcoStandards;
import java.util.Optional;

public class StringToImoEnumMapper {
    public static Optional<ImoEcoStandards> convert(String s) {
        for (ImoEcoStandards ecoStandard : ImoEcoStandards.values())
            if (ecoStandard.getLiteral().equals(s))
                return Optional.of(ecoStandard);

        return Optional.empty();
    }
}
