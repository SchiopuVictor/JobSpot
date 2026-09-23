package com.jobspot.service;

import com.jobspot.dto.UserRequestDto;
import com.jobspot.entity.User;
import com.jobspot.mapper.UserMapper;
import com.jobspot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserRequestDto request){
        return userRepository.save(UserMapper.toEntity(request));
    }
}
