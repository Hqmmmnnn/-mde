package com.hqqm.mde.repositories;

import com.hqqm.mde.models.UpdateUserDTO;
import com.hqqm.mde.models.User;
import com.hqqm.mde.models.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<UserDTO> getAll();
    Optional<User> findByEmail(String email);
    void save(User user);
    void update(UpdateUserDTO user);
}
