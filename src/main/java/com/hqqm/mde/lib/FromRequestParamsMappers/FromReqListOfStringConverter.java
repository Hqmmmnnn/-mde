package com.hqqm.mde.lib.FromRequestParamsMappers;

import java.util.List;
import java.util.stream.Collectors;

public class FromReqListOfStringConverter {
    public static List<String> toFormattedStrings(List<String> listOfParams) {
        return listOfParams.stream()
                .map(p -> p.replaceAll("_", " "))
                .collect(Collectors.toList());
    }

    public static List<Integer> toIntegers(List<String> listOfParams) {
        return listOfParams.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
