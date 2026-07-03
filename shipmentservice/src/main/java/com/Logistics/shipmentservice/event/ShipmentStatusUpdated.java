package com.Logistics.shipmentservice.event;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentStatusUpdated {

    private UUID shipmentId;
    private String trackingNumber;
    private String oldStatus;
    private String newStatus;
    private LocalDateTime updatedAt;
}
