package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private List<String> informationAboutRole;
}
