package com.jobspot.service;

import com.jobspot.dto.UserRequestDto;
import com.jobspot.entity.User;
import com.jobspot.mapper.UserMapper;
import com.jobspot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserRequestDto request){
        User user = UserMapper.toEntity(request);
        user.setCreatedAt(LocalDate.now());
        return user;
    }

    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found!!"));

    }

    public User updateUser(UserRequestDto request, Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found!!"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setCreatedAt(LocalDate.now());
        user.setActive(request.getActive());
        user.setRole(request.getRole());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        return userRepository.save(user);

    }


    public void deleteUser(Long id){
        userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found!!"));

        userRepository.deleteById(id);
    }

}
