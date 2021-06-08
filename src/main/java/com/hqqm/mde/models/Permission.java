package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permission {
    ENGINES_READ("engines:read", "просмотр и скачивание информации о двигателях"),
    ENGINES_WRITE("engines:write", "добавление информации о двигателе в базу данных"),
    ENGINES_UPDATE("engines:update", "обновление информации о двигателе в базе данных"),
    ENGINES_DELETE("engines:delete", "удаление двигателя из базы данных"),

    USERS_READ("users:read", "просматривать информацию о пользователе"),
    USERS_UPDATE("users:update", "обновлять информацию о пользователе");

    @Getter
    private final String permission;

    @Getter
    private final String info;
}
