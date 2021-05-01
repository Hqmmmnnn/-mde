package com.hqqm.mde.services;

import com.hqqm.mde.models.Role;
import com.hqqm.mde.models.SignupRequestDTO;
import com.hqqm.mde.models.User;
import com.hqqm.mde.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setFirstName(signupRequestDTO.getFirstName());
        user.setLastName(signupRequestDTO.getLastName());
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
