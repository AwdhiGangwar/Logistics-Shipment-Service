package com.Logistics.shipmentservice.service;
import java.util.List;
import java.util.UUID;

import com.Logistics.shipmentservice.dto.request.CreateShipmentRequest;
import com.Logistics.shipmentservice.dto.request.UpdateShipmentRequest;
import com.Logistics.shipmentservice.dto.response.CreateShipmentResponse;
import com.Logistics.shipmentservice.dto.response.GetShipmentResponse;
import com.Logistics.shipmentservice.dto.response.UpdateShipmentResponse;

public interface ShipmentService {

    CreateShipmentResponse createShipment(CreateShipmentRequest request);

    GetShipmentResponse getShipmentById(UUID shipmentId);

    List<GetShipmentResponse> getAllShipments();

    UpdateShipmentResponse updateShipment(UUID shipmentId, UpdateShipmentRequest request);

    void deleteShipment(UUID shipmentId);
}
