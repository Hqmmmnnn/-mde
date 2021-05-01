package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permission {
    ENGINES_READ("engines_read"),
    ENGINES_WRITE("engines_write"),
    ENGINES_UPDATE("engines_update"),
    ENGINES_DELETE("engines_delete");

    @Getter
    private final String permission;
}
