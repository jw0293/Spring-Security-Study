package com.example.security.app.user.dto;

import com.example.security.app.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Builder
public class UserListResponseDTO {

    private final List<User> userList;
}
