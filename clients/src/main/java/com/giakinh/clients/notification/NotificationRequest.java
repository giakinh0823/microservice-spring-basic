package com.giakinh.clients.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private Long toCustomerId;
    private String toCustomerEmail;
    private String message;
    private String sender;
}
