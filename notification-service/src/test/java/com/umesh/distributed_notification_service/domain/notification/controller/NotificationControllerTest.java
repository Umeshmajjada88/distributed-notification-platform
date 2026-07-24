package com.umesh.distributed_notification_service.domain.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umesh.distributed_notification_service.common.data.NotificationTestDataFactory;
import com.umesh.distributed_notification_service.domain.notification.dto.request.CreateNotificationRequest;
import com.umesh.distributed_notification_service.domain.notification.dto.response.NotificationResponse;
import com.umesh.distributed_notification_service.domain.notification.dto.response.NotificationStatisticsResponse;
import com.umesh.distributed_notification_service.domain.notification.service.NotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificationService notificationService;

    @Test
    @DisplayName("Should create notification")
    void shouldCreateNotification() throws Exception {

        CreateNotificationRequest request =
                NotificationTestDataFactory.pendingRequest();

        NotificationResponse response =
                new NotificationResponse();

        response.setId(1L);

        when(notificationService.createNotification(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isCreated())

                .andExpect(jsonPath("$.success").value(true))

                .andExpect(jsonPath("$.data.id").value(1));

        verify(notificationService)
                .createNotification(any());

    }

    @Test
    @DisplayName("Should return notification by id")
    void shouldReturnNotificationById() throws Exception {

        NotificationResponse response =
                new NotificationResponse();

        response.setId(1L);

        when(notificationService.getNotificationById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/notifications/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success").value(true))

                .andExpect(jsonPath("$.data.id").value(1));

    }

    @Test
    @DisplayName("Should return notification by event id")
    void shouldReturnNotificationByEventId() throws Exception {

        UUID eventId = UUID.randomUUID();

        NotificationResponse response =
                new NotificationResponse();

        response.setEventId(eventId);

        when(notificationService.getNotificationByEventId(eventId))
                .thenReturn(response);

        mockMvc.perform(
                get("/api/v1/notifications/event/{eventId}", eventId))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success").value(true));

    }

    @Test
    @DisplayName("Should return all notifications")
    void shouldReturnAllNotifications() throws Exception {

        NotificationResponse response =
                new NotificationResponse();

        response.setId(1L);

        when(notificationService.getAllNotifications())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/notifications"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success").value(true))

                .andExpect(jsonPath("$.data.length()").value(1));

    }

    @Test
    @DisplayName("Should return statistics")
    void shouldReturnStatistics() throws Exception {

        NotificationStatisticsResponse statistics =
                NotificationStatisticsResponse.builder()
                        .total(10)
                        .pending(3)
                        .sent(7)
                        .build();

        when(notificationService.getStatistics())
                .thenReturn(statistics);

        mockMvc.perform(get("/api/v1/notifications/statistics"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.total").value(10))

                .andExpect(jsonPath("$.pending").value(3))

                .andExpect(jsonPath("$.sent").value(7));

    }

}