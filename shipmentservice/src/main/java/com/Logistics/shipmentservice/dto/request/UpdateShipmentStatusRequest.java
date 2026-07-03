package com.Logistics.shipmentservice.dto.request;

import com.Logistics.shipmentservice.enums.ShipmentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateShipmentStatusRequest {

    @NotNull(message = "Shipment status is required")
    private ShipmentStatus status;
}
