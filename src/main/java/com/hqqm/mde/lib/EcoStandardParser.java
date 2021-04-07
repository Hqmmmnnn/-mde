package com.hqqm.mde.lib;

public class EcoStandardParser {
    public static String parse(String ecoStandard) {
        if (ecoStandard.contains("_")) {
            String[] parts = ecoStandard.split("_");
            ecoStandard = parts[0] + " " + parts[1];
        }

        return ecoStandard;
    }
}
