package com.Logistics.shipmentservice.dto.response;

import java.time.LocalDateTime;

import com.Logistics.shipmentservice.enums.ShipmentStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateShipmentResponse {
    private String trackingNumber;

private ShipmentStatus status;

private LocalDateTime updatedAt;

private String message;
}
