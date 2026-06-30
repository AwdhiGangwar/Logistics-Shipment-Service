package com.Logistics.shipmentservice.service;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Logistics.shipmentservice.dto.request.CreateShipmentRequest;
import com.Logistics.shipmentservice.dto.response.CreateShipmentResponse;
import com.Logistics.shipmentservice.entity.ShipmentEntity;
import com.Logistics.shipmentservice.enums.ShipmentStatus;
import com.Logistics.shipmentservice.repository.ShipmentRepository;
@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Override
    public CreateShipmentResponse createShipment(CreateShipmentRequest request) {

        String trackingNumber = generateTrackingNumber();

        ShipmentEntity shipment = mapToEntity(request, trackingNumber);

        ShipmentEntity savedShipment = shipmentRepository.save(shipment);

        return mapToResponse(savedShipment);
    }

    private String generateTrackingNumber() {

        String trackingNumber;

        do {
            trackingNumber = "TRK-"
                    + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (shipmentRepository.existsByTrackingNumber(trackingNumber));

        return trackingNumber;
    }

    private ShipmentEntity mapToEntity(CreateShipmentRequest request,
            String trackingNumber) {

        ShipmentEntity shipment = new ShipmentEntity();

        shipment.setTrackingNumber(trackingNumber);
        shipment.setSenderId(request.getSenderId());
        shipment.setReceiverId(request.getReceiverId());
        shipment.setSourceAddress(request.getSourceAddress());
        shipment.setDestinationAddress(request.getDestinationAddress());
        shipment.setWeight(request.getWeight());
        shipment.setShipmentType(request.getShipmentType());

        shipment.setStatus(ShipmentStatus.CREATED);

        shipment.setCreatedAt(LocalDateTime.now());
        shipment.setUpdatedAt(LocalDateTime.now());

        return shipment;
    }

    private CreateShipmentResponse mapToResponse(ShipmentEntity shipment) {

        CreateShipmentResponse response = new CreateShipmentResponse();

        response.setTrackingNumber(shipment.getTrackingNumber());
        response.setStatus(shipment.getStatus());
        response.setCreatedAt(shipment.getCreatedAt());
        response.setMessage("Shipment created successfully.");

        return response;
    }
}
