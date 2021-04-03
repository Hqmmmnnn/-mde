package com.hqqm.mde.lib.FromRequestParamsMappers;

import org.jooq.EnumType;

public class StringToEnumConverter {
    public static <T extends EnumType> T convert(String s, T[] enums) {
        for (EnumType a : enums) {
            if (a.getLiteral().equals(s))
                return (T) a;
        }

        return null;
    }
}
