package com.umesh.distributed_notification_service.domain.delivery.factory;

import com.umesh.distributed_notification_service.domain.delivery.strategy.NotificationDeliveryStrategy;
import com.umesh.distributed_notification_service.domain.notification.enums.NotificationChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeliveryStrategyFactory {

    private final List<NotificationDeliveryStrategy> strategies;

    private Map<NotificationChannel, NotificationDeliveryStrategy> strategyMap;

    @jakarta.annotation.PostConstruct
    public void initialize() {

        strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        NotificationDeliveryStrategy::getSupportedChannel,
                        Function.identity()));
    }

    public NotificationDeliveryStrategy getStrategy(NotificationChannel channel) {

        NotificationDeliveryStrategy strategy = strategyMap.get(channel);

        if (strategy == null) {
            throw new IllegalArgumentException(
                    "No delivery strategy found for channel : " + channel);
        }

        return strategy;
    }
}