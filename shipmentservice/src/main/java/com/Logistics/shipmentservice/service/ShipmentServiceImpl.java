package com.Logistics.shipmentservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.Logistics.shipmentservice.dto.request.CreateShipmentRequest;
import com.Logistics.shipmentservice.dto.request.UpdateShipmentRequest;
import com.Logistics.shipmentservice.dto.response.CreateShipmentResponse;
import com.Logistics.shipmentservice.dto.response.GetShipmentResponse;
import com.Logistics.shipmentservice.dto.response.UpdateShipmentResponse;
import com.Logistics.shipmentservice.entity.ShipmentEntity;
import com.Logistics.shipmentservice.enums.ShipmentStatus;
import com.Logistics.shipmentservice.exception.ResourceNotFoundException;
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

    @Override
    public GetShipmentResponse getShipmentById(UUID shipmentId) {

        ShipmentEntity shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Shipment not found with ID : " + shipmentId));

        GetShipmentResponse response = new GetShipmentResponse();

        response.setId(shipment.getId());
        response.setTrackingNumber(shipment.getTrackingNumber());
        response.setSenderId(shipment.getSenderId());
        response.setReceiverId(shipment.getReceiverId());
        response.setSourceAddress(shipment.getSourceAddress());
        response.setDestinationAddress(shipment.getDestinationAddress());
        response.setWeight(shipment.getWeight());
        response.setShipmentType(shipment.getShipmentType());
        response.setStatus(shipment.getStatus());
        response.setCreatedAt(shipment.getCreatedAt());
        response.setUpdatedAt(shipment.getUpdatedAt());

        return response;
    }

    @Override
    public List<GetShipmentResponse> getAllShipments() {

        List<ShipmentEntity> shipments = shipmentRepository.findAll();

        return shipments.stream()
                .map(this::mapToGetShipmentResponse)
                .toList();
    }

    private GetShipmentResponse mapToGetShipmentResponse(ShipmentEntity shipment) {

        GetShipmentResponse response = new GetShipmentResponse();

        response.setId(shipment.getId());
        response.setTrackingNumber(shipment.getTrackingNumber());
        response.setSenderId(shipment.getSenderId());
        response.setReceiverId(shipment.getReceiverId());
        response.setSourceAddress(shipment.getSourceAddress());
        response.setDestinationAddress(shipment.getDestinationAddress());
        response.setWeight(shipment.getWeight());
        response.setShipmentType(shipment.getShipmentType());
        response.setStatus(shipment.getStatus());
        response.setCreatedAt(shipment.getCreatedAt());
        response.setUpdatedAt(shipment.getUpdatedAt());

        return response;
    }

    @Override
    public UpdateShipmentResponse updateShipment(UUID shipmentId,
            UpdateShipmentRequest request) {

        ShipmentEntity shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Shipment not found with ID : " + shipmentId));

        shipment.setSenderId(request.getSenderId());
        shipment.setReceiverId(request.getReceiverId());
        shipment.setSourceAddress(request.getSourceAddress());
        shipment.setDestinationAddress(request.getDestinationAddress());
        shipment.setWeight(request.getWeight());
        shipment.setShipmentType(request.getShipmentType());
        shipment.setUpdatedAt(LocalDateTime.now());

        ShipmentEntity updatedShipment = shipmentRepository.save(shipment);

        UpdateShipmentResponse response = new UpdateShipmentResponse();

        response.setTrackingNumber(updatedShipment.getTrackingNumber());
        response.setStatus(updatedShipment.getStatus());
        response.setUpdatedAt(updatedShipment.getUpdatedAt());
        response.setMessage("Shipment updated successfully.");

        return response;
    }

    @Override
    public void deleteShipment(UUID shipmentId) {

        ShipmentEntity shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found with id: " + shipmentId));

        shipmentRepository.delete(shipment);
    }

    @Override
    public List<GetShipmentResponse> getShipmentsByStatus(ShipmentStatus status) {

        return shipmentRepository.findByStatus(status)
                .stream()
                .map(this::mapToGetShipmentResponse)
                .toList();
    }

    @Override
    public List<GetShipmentResponse> getShipmentsBySenderId(UUID senderId) {

        return shipmentRepository.findBySenderId(senderId)
                .stream()
                .map(this::mapToGetShipmentResponse)
                .toList();
    }
    
    @Override
    public List<GetShipmentResponse> getShipmentsByReceiverId(UUID receiverId) {

        return shipmentRepository.findByReceiverId(receiverId)
                .stream()
                .map(this::mapToGetShipmentResponse)
                .toList();
    }

}
