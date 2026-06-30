package com.Logistics.shipmentservice.service;
import com.Logistics.shipmentservice.dto.request.CreateShipmentRequest;
import com.Logistics.shipmentservice.dto.response.CreateShipmentResponse;

public interface ShipmentService {

    CreateShipmentResponse createShipment(CreateShipmentRequest request);

}
