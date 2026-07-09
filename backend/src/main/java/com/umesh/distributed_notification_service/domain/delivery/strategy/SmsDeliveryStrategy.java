package com.umesh.distributed_notification_service.domain.delivery.strategy;

import com.umesh.distributed_notification_service.domain.notification.event.dto.NotificationEvent;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsDeliveryStrategy implements NotificationDeliveryStrategy {

    @Override
    public NotificationChannel getSupportedChannel() {
        return NotificationChannel.SMS;
    }

    @Override
    public void deliver(NotificationEvent event) {

        log.info("""

                ================ SMS ===================

                Event Id      : {}
                Notification  : {}
                User Id       : {}

                SMS delivered successfully.

                ========================================
                """,
                event.getEventId(),
                event.getNotificationId(),
                event.getUserId());
    }
}