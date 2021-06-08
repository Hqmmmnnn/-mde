package com.hqqm.mde.services;

import com.hqqm.mde.models.*;
import com.hqqm.mde.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAll() {
        var users = userRepository.getAll();
        users.forEach(user -> user.setInformationAboutRole(user.getRole().getInformation()));
        return users;
    }

    @Override
    public void create(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDTO.getPassword()));
        user.setFirstName(signupRequestDTO.getFirstName());
        user.setLastName(signupRequestDTO.getLastName());
        user.setRole(Role.READER);
        userRepository.save(user);
    }

    @Override
    public void update(UpdateUserDTO user) {
        userRepository.update(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
