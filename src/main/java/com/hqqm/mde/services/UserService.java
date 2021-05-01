package com.hqqm.mde.services;

import com.hqqm.mde.models.SignupRequestDTO;
import com.hqqm.mde.models.User;

import java.util.Optional;

public interface UserService {
    void create(SignupRequestDTO signupRequestDTO);
    Optional<User> findByEmail(String email);
}
