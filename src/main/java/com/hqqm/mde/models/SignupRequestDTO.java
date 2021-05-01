package com.hqqm.mde.models;

import lombok.Data;

@Data
public class SignupRequestDTO {
    String email;
    String password;
    String firstName;
    String lastName;
}
