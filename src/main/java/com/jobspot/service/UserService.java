package com.jobspot.service;

import com.jobspot.dto.UserRequestDto;
import com.jobspot.entity.User;
import com.jobspot.exceptions.UserNotFoundExceptions;
import com.jobspot.mapper.UserMapper;
import com.jobspot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(UserRequestDto request){
        User user = UserMapper.toEntity(request);
        user.setCreatedAt(LocalDate.now());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundExceptions::new);

    }

    @Transactional
    public User updateUser(UserRequestDto request, Long id){
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundExceptions::new);
        user.setEmail(request.getEmail());
        user.setCreatedAt(LocalDate.now());
        user.setActive(request.getActive());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);

    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.findById(id)
                .orElseThrow(UserNotFoundExceptions::new);

        userRepository.deleteById(id);
    }

}
