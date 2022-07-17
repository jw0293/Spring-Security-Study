package com.example.security.app.user.service;

import com.example.security.app.user.domain.User;
import com.example.security.app.user.dto.SignUpDto;
import com.example.security.app.user.repository.UserRepository;
import com.example.security.enums.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(@Lazy UserRepository userRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signUp(final SignUpDto signUpDto){
        final User user = User.builder()
                .email(signUpDto.getEmail())
                .pw(passwordEncoder.encode(signUpDto.getPw()))
                .role(UserRole.ROLE_USER)
                .build();

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(final String email){
        return userRepository.findByEmail(email);
    }

    public boolean isEmailDuplicated(final String email){
        return userRepository.existsByEmail(email);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
