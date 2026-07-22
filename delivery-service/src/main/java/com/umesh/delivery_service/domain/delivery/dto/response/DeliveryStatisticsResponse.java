package com.umesh.delivery_service.domain.delivery.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DeliveryStatisticsResponse {

    long total;

    long pending;

    long inProgress;

    long retryPending;

    long delivered;

    long failed;

}