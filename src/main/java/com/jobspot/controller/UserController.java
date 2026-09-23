package com.jobspot.controller;

import com.jobspot.dto.UserRequestDto;
import com.jobspot.dto.UserResponseDto;
import com.jobspot.mapper.UserMapper;
import com.jobspot.repository.UserRepository;
import com.jobspot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto request){
        return ResponseEntity.ok(UserMapper.toDto(userService.createUser(request)));

    }

}
