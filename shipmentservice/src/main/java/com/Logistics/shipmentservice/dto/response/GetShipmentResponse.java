package com.Logistics.shipmentservice.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.Logistics.shipmentservice.enums.ShipmentStatus;
import com.Logistics.shipmentservice.enums.ShipmentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetShipmentResponse {

    private UUID id;

    private String trackingNumber;

    private UUID senderId;

    private UUID receiverId;

    private String sourceAddress;

    private String destinationAddress;

    private Double weight;

    private ShipmentType shipmentType;

    private ShipmentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
