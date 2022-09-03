package com.giakinh.notification;

import com.giakinh.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/notification")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest request){
      log.info("New notification... {}", request);
        notificationService.send(request);
    };
}
