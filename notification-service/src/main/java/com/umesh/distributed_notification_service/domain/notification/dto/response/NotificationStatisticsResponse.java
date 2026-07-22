package com.umesh.distributed_notification_service.domain.notification.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NotificationStatisticsResponse {

    long total;

    long pending;

    long processing;

    long retrying;

    long scheduled;

    long sent;

    long failed;

}