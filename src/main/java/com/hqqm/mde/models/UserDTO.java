package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private List<String> informationAboutRole;
}
