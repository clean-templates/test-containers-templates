package com.notifcaiton.service.infra;


import com.notifcaiton.service.service.INotificationHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RabbitListenerConfig {


    private INotificationHandler notificationHandler;

    @RabbitListener(queues = "notification-queue")
    public void RabbitListener(ApprovedOrderApiResponse response) {
        log.info("Message Received: " + response);
        notificationHandler.notify(response.getDriverId());
    }
}
