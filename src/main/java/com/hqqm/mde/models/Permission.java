package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permission {
    ENGINES_READ("engines:read"),
    ENGINES_WRITE("engines:write"),
    ENGINES_UPDATE("engines:update"),
    ENGINES_DELETE("engines:delete");

    @Getter
    private final String permission;
}
