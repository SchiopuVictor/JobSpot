package com.jobspot.controller;

import com.jobspot.dto.UserRequestDto;
import com.jobspot.dto.UserResponseDto;
import com.jobspot.entity.User;
import com.jobspot.mapper.UserMapper;
import com.jobspot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(UserMapper.toDto(userService.getUser(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping("/{id}")
     public ResponseEntity<UserResponseDto> updateUser (
             @Valid @RequestBody UserRequestDto request,
             @PathVariable Long id){
        return ResponseEntity.ok(UserMapper.toDto(userService.updateUser(request,id)));

    }

}
