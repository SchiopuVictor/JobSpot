package com.jobspot.service;

import com.jobspot.dto.NotificationRequestDto;
import com.jobspot.entity.Notification;
import com.jobspot.entity.User;
import com.jobspot.mapper.NotificationMapper;
import com.jobspot.repository.NotificationRepository;
import com.jobspot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public Notification createNotification(NotificationRequestDto request){
        User user = userRepository
                .findById(request.getUser_id()).orElseThrow(()->new RuntimeException("User not found!!"));
        Notification notification = NotificationMapper.toEntity(request);
        notification.setUser(user);
        notification.setCreatedAt(LocalDate.now());

        return notificationRepository.save(notification);

    }

    public Notification getNotification(Long id){
        return notificationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Notification not found!!"));
    }

    @Transactional
    public Notification updateNotification(NotificationRequestDto request, Long id){
        User user = userRepository
                .findById(request.getUser_id())
                .orElseThrow(()->new RuntimeException("User not found!!"));

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Notification not foud!!"));

        notification.setUser(user);
        notification.setTitle(request.getTitle());
        notification.setMessage(request.getMessage());
        notification.setCreatedAt(LocalDate.now());

        return notificationRepository.save(notification);
    }

    @Transactional
    public void deleteNotification(Long id){
        notificationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Notification not found!!"));

        notificationRepository.deleteById(id);

    }

}
