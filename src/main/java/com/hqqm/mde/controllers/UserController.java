package com.hqqm.mde.controllers;

import com.hqqm.mde.models.UpdateUserDTO;
import com.hqqm.mde.models.UserDTO;
import com.hqqm.mde.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/currentUser")
    @PreAuthorize("isFullyAuthenticated()")
    public UserDTO getUser(Authentication auth) {
        var user = userService.findByEmail(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getRole().getInformation()
        );
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('users:read')")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @PatchMapping("/users")
    @PreAuthorize("hasAuthority('users:update')")
    public void changeUserData(@RequestBody UpdateUserDTO user) {
        userService.update(user);
    }
}
