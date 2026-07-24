package com.umesh.distributed_notification_service.domain.notification.service;

import com.umesh.distributed_notification_service.common.data.NotificationTestDataFactory;
import com.umesh.distributed_notification_service.common.util.IdGenerator;
import com.umesh.distributed_notification_service.domain.notification.dto.request.CreateNotificationRequest;
import com.umesh.distributed_notification_service.domain.notification.dto.response.NotificationResponse;
import com.umesh.distributed_notification_service.domain.notification.entity.Notification;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationStatus;
import com.umesh.distributed_notification_service.domain.notification.event.mapper.NotificationRequestedEventMapper;
import com.umesh.distributed_notification_service.domain.notification.mapper.NotificationMapper;
import com.umesh.distributed_notification_service.domain.notification.repository.NotificationRepository;
import com.umesh.distributed_notification_service.domain.notification.service.impl.NotificationServiceImpl;
import com.umesh.distributed_notification_service.domain.outbox.constants.AggregateType;
import com.umesh.distributed_notification_service.domain.outbox.constants.NotificationEventType;
import com.umesh.distributed_notification_service.domain.outbox.entity.OutboxEvent;
import com.umesh.distributed_notification_service.domain.outbox.mapper.OutboxMapper;
import com.umesh.distributed_notification_service.domain.outbox.service.OutboxService;
import com.umesh.distributed_notification_service.infrastructure.metrics.NotificationMetrics;
import com.umesh.shared.event.NotificationRequestedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationMapper notificationMapper;

    @Mock
    private IdGenerator idGenerator;

    @Mock
    private NotificationRequestedEventMapper eventMapper;

    @Mock
    private OutboxMapper outboxMapper;

    @Mock
    private OutboxService outboxService;

    @Mock
    private NotificationMetrics notificationMetrics;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    private CreateNotificationRequest request;

    private Notification notification;

    private NotificationResponse response;

    @BeforeEach
    void setUp() {

        request = NotificationTestDataFactory.pendingRequest();

        notification = NotificationTestDataFactory.pendingNotification();

        response = new NotificationMapper().toResponse(notification);

    }

    @Test
    void shouldCreateNotification() {

        UUID eventId = UUID.randomUUID();

        notification.setStatus(NotificationStatus.PENDING);

        when(notificationMapper.toEntity(request))
                .thenReturn(notification);

        when(idGenerator.generateEventId())
                .thenReturn(eventId);

        when(notificationRepository.save(any(Notification.class)))
        .thenAnswer(invocation -> {
            Notification saved = invocation.getArgument(0);
            saved.setId(1L); // Simulate JPA-generated ID
            return saved;
        });

        NotificationRequestedEvent event =
                mock(NotificationRequestedEvent.class);

        when(eventMapper.toEvent(any(Notification.class)))
                .thenReturn(event);

        OutboxEvent outbox =
                mock(OutboxEvent.class);

        when(outboxMapper.toOutboxEvent(
                any(AggregateType.class),
                anyString(),
                any(NotificationEventType.class),
                any()))
                .thenReturn(outbox);

        when(outboxService.save(outbox))
                .thenReturn(outbox);

        when(notificationMapper.toResponse(any(Notification.class)))
                .thenReturn(response);

        NotificationResponse result =
                notificationService.createNotification(request);

        assertNotNull(result);

        verify(idGenerator).generateEventId();

        verify(notificationRepository).save(any(Notification.class));

        verify(eventMapper).toEvent(any(Notification.class));

        verify(outboxMapper).toOutboxEvent(
                any(),
                anyString(),
                any(),
                any());

        verify(outboxService).save(any(OutboxEvent.class));

        verify(notificationMetrics)
                .incrementCreated(notification.getChannel());

    }

}