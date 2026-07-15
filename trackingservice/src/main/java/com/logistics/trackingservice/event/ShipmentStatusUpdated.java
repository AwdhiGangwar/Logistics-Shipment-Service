package com.logistics.trackingservice.event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

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
