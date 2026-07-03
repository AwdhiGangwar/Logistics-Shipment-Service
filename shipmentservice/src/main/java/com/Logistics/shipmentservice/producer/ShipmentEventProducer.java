package com.Logistics.shipmentservice.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.Logistics.shipmentservice.event.ShipmentStatusUpdated;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentEventProducer {

    private static final String TOPIC = "shipment-events";

    private final KafkaTemplate<String, ShipmentStatusUpdated> kafkaTemplate;

    public void publishStatusUpdatedEvent(ShipmentStatusUpdated event) {

        kafkaTemplate.send(
                TOPIC,
                event.getShipmentId().toString(),
                event
        );
    }
}
