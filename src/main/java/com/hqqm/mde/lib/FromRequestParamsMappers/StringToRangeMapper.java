package com.hqqm.mde.lib.FromRequestParamsMappers;

import java.util.Arrays;

public class StringToRangeMapper {
    public static int[] convert(String s) {
        return Arrays.stream(s.split("-"))
                     .mapToInt(Integer::parseInt)
                     .toArray();
    }
}
