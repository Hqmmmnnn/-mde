package com.hqqm.mde.models;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AuthenticationDTO {

    @Data
    @AllArgsConstructor
    public static class Request {
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        private String token;
    }
}