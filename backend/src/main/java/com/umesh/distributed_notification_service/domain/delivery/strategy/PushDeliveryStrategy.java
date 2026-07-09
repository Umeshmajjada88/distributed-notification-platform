package com.umesh.distributed_notification_service.domain.delivery.strategy;

import com.umesh.distributed_notification_service.domain.notification.event.dto.NotificationEvent;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PushDeliveryStrategy implements NotificationDeliveryStrategy {

    @Override
    public NotificationChannel getSupportedChannel() {
        return NotificationChannel.PUSH;
    }

    @Override
    public void deliver(NotificationEvent event) {

        log.info("""

                =============== PUSH ===================

                Event Id      : {}
                Notification  : {}
                User Id       : {}

                Push notification delivered successfully.

                ========================================
                """,
                event.getEventId(),
                event.getNotificationId(),
                event.getUserId());
    }
}