package com.hqqm.mde.controllers;

import com.hqqm.mde.models.UserDTO;
import com.hqqm.mde.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/currentUser")
    public UserDTO getUser(Authentication auth) {
        var optUser = userService.findByEmail(auth.getName());
        var user = optUser.get();

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail((user.getEmail()));
        userDTO.setFirstName((user.getFirstName()));
        userDTO.setLastName((user.getLastName()));

        return userDTO;
    }
}
