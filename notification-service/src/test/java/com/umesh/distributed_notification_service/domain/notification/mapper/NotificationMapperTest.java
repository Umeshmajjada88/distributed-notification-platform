package com.umesh.distributed_notification_service.domain.notification.mapper;

import com.umesh.distributed_notification_service.common.data.NotificationTestDataFactory;
import com.umesh.distributed_notification_service.domain.notification.dto.request.CreateNotificationRequest;
import com.umesh.distributed_notification_service.domain.notification.dto.response.NotificationResponse;
import com.umesh.distributed_notification_service.domain.notification.entity.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationMapperTest {

    private NotificationMapper mapper;

    @BeforeEach
    void setUp() {

        mapper = new NotificationMapper();

    }

    @Test
    void shouldMapRequestToEntity() {

        CreateNotificationRequest request =
                NotificationTestDataFactory.pendingRequest();

        Notification notification =
                mapper.toEntity(request);

        assertNotNull(notification);

        assertEquals(
                request.getUserId(),
                notification.getUserId());

        assertEquals(
                request.getChannel(),
                notification.getChannel());

        assertEquals(
                request.getSubject(),
                notification.getSubject());

        assertEquals(
                request.getMessage(),
                notification.getMessage());

        assertEquals(
                request.getScheduledAt(),
                notification.getScheduledAt());

    }

    @Test
    void shouldMapEntityToResponse() {

        Notification notification =
                NotificationTestDataFactory.pendingNotification();

        NotificationResponse response =
                mapper.toResponse(notification);

        assertNotNull(response);

        assertEquals(
                notification.getEventId(),
                response.getEventId());

        assertEquals(
                notification.getUserId(),
                response.getUserId());

        assertEquals(
                notification.getChannel(),
                response.getChannel());

        assertEquals(
                notification.getStatus(),
                response.getStatus());

        assertEquals(
                notification.getSubject(),
                response.getSubject());

        assertEquals(
                notification.getMessage(),
                response.getMessage());

        assertEquals(
                notification.getRetryCount(),
                response.getRetryCount());

    }

    @Test
    void shouldReturnNullWhenRequestIsNull() {

        assertNull(
                mapper.toEntity(null));

    }

    @Test
    void shouldReturnNullWhenNotificationIsNull() {

        assertNull(
                mapper.toResponse(null));

    }

}