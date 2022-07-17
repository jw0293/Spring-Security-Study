package com.example.security.app.user.controller;

import com.example.security.app.user.dto.SignUpDto;
import com.example.security.app.user.dto.UserListResponseDTO;
import com.example.security.app.user.service.UserService;
import com.example.security.utils.TokenUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;


    public UserController(@Lazy UserService userService, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody final SignUpDto signUpDto){
        return userService.findByEmail(signUpDto.getEmail()).isPresent()
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(TokenUtils.generateJwtToken(userService.signUp(signUpDto)));
    }

    @GetMapping("/list")
    public ResponseEntity<UserListResponseDTO> findAll(){
        final UserListResponseDTO userListResponseDTO
                = UserListResponseDTO.builder()
                .userList(userService.findAll()).build();

        return ResponseEntity.ok(userListResponseDTO);
    }
}
