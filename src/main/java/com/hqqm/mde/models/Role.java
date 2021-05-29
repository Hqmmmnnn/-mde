package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum Role {
    ADMIN(Set.of(Permission.ENGINES_READ, Permission.ENGINES_WRITE, Permission.ENGINES_UPDATE, Permission.ENGINES_DELETE)),
    EDITOR(Set.of(Permission.ENGINES_READ, Permission.ENGINES_WRITE, Permission.ENGINES_UPDATE, Permission.ENGINES_DELETE)),
    PREEDITOR(Set.of(Permission.ENGINES_READ, Permission.ENGINES_WRITE, Permission.ENGINES_UPDATE)),
    READER(Set.of(Permission.ENGINES_READ));

    @Getter
    private final Set<Permission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toUnmodifiableSet());
    }
}
