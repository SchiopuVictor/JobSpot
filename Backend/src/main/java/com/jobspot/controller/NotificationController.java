package com.jobspot.controller;

import com.jobspot.dto.NotificationRequestDto;
import com.jobspot.dto.NotificationResponseDto;
import com.jobspot.mapper.NotificationMapper;
import com.jobspot.service.NotificationService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponseDto>createNotification(
            @Valid @RequestBody NotificationRequestDto request)
    {
        return ResponseEntity
                .ok(NotificationMapper.toDto(notificationService.createNotification(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDto>getNotification(@PathVariable Long id){
        return ResponseEntity
                .ok(NotificationMapper.toDto(notificationService.getNotification(id)));
    }


    @PutMapping("/{id}")
    public ResponseEntity<NotificationResponseDto>updateNotification(
            @Valid @RequestBody NotificationRequestDto request,
            @PathVariable Long id)
    {
        return ResponseEntity
                .ok(NotificationMapper.toDto(notificationService.updateNotification(request,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id){

        notificationService.deleteNotification(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
