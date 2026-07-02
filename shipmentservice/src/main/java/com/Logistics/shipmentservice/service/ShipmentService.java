package com.Logistics.shipmentservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.Logistics.shipmentservice.dto.request.CreateShipmentRequest;
import com.Logistics.shipmentservice.dto.request.UpdateShipmentRequest;
import com.Logistics.shipmentservice.dto.response.CreateShipmentResponse;
import com.Logistics.shipmentservice.dto.response.GetShipmentResponse;
import com.Logistics.shipmentservice.dto.response.UpdateShipmentResponse;
import com.Logistics.shipmentservice.enums.ShipmentStatus;
import com.Logistics.shipmentservice.enums.ShipmentType;

public interface ShipmentService {

    CreateShipmentResponse createShipment(CreateShipmentRequest request);

    GetShipmentResponse getShipmentById(UUID shipmentId);

    Page<GetShipmentResponse> getAllShipments(
        int page,
        int size,
        String sortBy,
        String direction,
        ShipmentStatus status,
        ShipmentType shipmentType,
        UUID senderId,
        UUID receiverId
);

    UpdateShipmentResponse updateShipment(UUID shipmentId, UpdateShipmentRequest request);

    void deleteShipment(UUID shipmentId);

    List<GetShipmentResponse> getShipmentsByStatus(ShipmentStatus status);

    List<GetShipmentResponse> getShipmentsBySenderId(UUID senderId);

    List<GetShipmentResponse> getShipmentsByReceiverId(UUID receiverId);
}
