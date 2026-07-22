package com.umesh.delivery_service.domain.deadletter.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DeadLetterStatisticsResponse {

    long total;

    long pending;

    long replayed;

    long discarded;

}