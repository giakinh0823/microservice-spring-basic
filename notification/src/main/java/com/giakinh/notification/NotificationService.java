package com.giakinh.notification;

import com.giakinh.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest request) {
        notificationRepository.save(Notification.builder()
                .toCustomerEmail(request.getToCustomerEmail())
                .toCustomerId(request.getToCustomerId())
                .sender(request.getSender())
                .message(request.getMessage())
                .send_at(LocalDateTime.now())
                .build());
    }
}
