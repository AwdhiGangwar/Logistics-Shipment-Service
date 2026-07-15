package com.logistics.trackingservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.logistics.trackingservice.event.ShipmentStatusUpdated;
import com.logistics.trackingservice.service.TrackingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentEventConsumer {

    private final TrackingService trackingService;

    @KafkaListener(
            topics = "shipment-events",
            groupId = "tracking-group"
    )
    public void consumeShipmentStatusUpdated(ShipmentStatusUpdated event) {

        trackingService.saveTrackingEvent(event);

    }
}