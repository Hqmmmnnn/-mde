package com.hqqm.mde.controllers;

import com.hqqm.mde.models.*;
import com.hqqm.mde.security.JwtTokenProvider;
import com.hqqm.mde.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController @RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO request) {
        try {
            String email = request.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));
            User user = userService.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Такого пользователя не существует"));
            String token = jwtTokenProvider.createToken(email, user.getRole().name());
            var response = new AuthenticationResponseDTO(token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Неверный емейл или пароль", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDTO signupRequestDTO) {
        if(userService.findByEmail(signupRequestDTO.getEmail()).isPresent())
            return ResponseEntity.badRequest().body("Данное имя пользователя уже занято");

        userService.create(signupRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Регистрация прошла успешно");
    }
}
