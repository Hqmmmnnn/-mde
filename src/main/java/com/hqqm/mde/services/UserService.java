package com.hqqm.mde.services;

import com.hqqm.mde.models.SignupRequestDTO;
import com.hqqm.mde.models.UpdateUserDTO;
import com.hqqm.mde.models.User;
import com.hqqm.mde.models.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAll();
    void create(SignupRequestDTO signupRequestDTO);
    void update(UpdateUserDTO user);
    Optional<User> findByEmail(String email);
}
