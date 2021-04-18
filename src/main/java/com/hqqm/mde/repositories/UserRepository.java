package com.hqqm.mde.repositories;

import com.hqqm.mde.models.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
