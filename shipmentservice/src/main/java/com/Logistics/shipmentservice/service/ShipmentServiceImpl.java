package com.Logistics.shipmentservice.service;
import com.Logistics.shipmentservice.security.JwtService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Logistics.shipmentservice.dto.request.CreateShipmentRequest;
import com.Logistics.shipmentservice.dto.request.UpdateShipmentRequest;
import com.Logistics.shipmentservice.dto.request.UpdateShipmentStatusRequest;
import com.Logistics.shipmentservice.dto.response.CreateShipmentResponse;
import com.Logistics.shipmentservice.dto.response.GetShipmentResponse;
import com.Logistics.shipmentservice.dto.response.UpdateShipmentResponse;
import com.Logistics.shipmentservice.entity.ShipmentEntity;
import com.Logistics.shipmentservice.enums.ShipmentStatus;
import com.Logistics.shipmentservice.enums.ShipmentType;
import com.Logistics.shipmentservice.event.ShipmentStatusUpdated;
import com.Logistics.shipmentservice.exception.ResourceNotFoundException;
import com.Logistics.shipmentservice.producer.ShipmentEventProducer;
import com.Logistics.shipmentservice.repository.ShipmentRepository;
import com.Logistics.shipmentservice.specifications.ShipmentSpecification;

@Service
public class ShipmentServiceImpl implements ShipmentService {
    private static final String SHIPMENT_NOT_FOUND = "Shipment not found with ID : ";
    private final JwtService jwtService;
    private final ShipmentRepository shipmentRepository;
    private final ShipmentEventProducer shipmentEventProducer;

    public ShipmentServiceImpl(ShipmentRepository shipmentRepository, ShipmentEventProducer shipmentEventProducer , JwtService jwtService) {
        this.shipmentRepository = shipmentRepository;
        this.shipmentEventProducer = shipmentEventProducer;
        this.jwtService = jwtService;
    }

    @Override
    public CreateShipmentResponse createShipment(CreateShipmentRequest request , String authHeader) {

        String token = authHeader.substring(7); // "Bearer " ko hatao
        
        UUID senderId = extractSenderIdFromToken(token); // JWT token se senderId nikalne ka logic implement karo

        String trackingNumber = generateTrackingNumber();

        ShipmentEntity shipment = mapToEntity(request, trackingNumber, senderId);

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

    private UUID extractSenderIdFromToken(String token) {
        return jwtService.extractUserId(token);
    }

    private ShipmentEntity mapToEntity(CreateShipmentRequest request,
            String trackingNumber , UUID senderId) {

        ShipmentEntity shipment = new ShipmentEntity();

        shipment.setTrackingNumber(trackingNumber);
        shipment.setSenderId(senderId);
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
                SHIPMENT_NOT_FOUND + shipmentId));

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
    public Page<GetShipmentResponse> getAllShipments(
            int page,
            int size,
            String sortBy,
            String direction,
            ShipmentStatus status,
            ShipmentType shipmentType,
            UUID senderId,
            UUID receiverId) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ShipmentEntity> shipmentPage = shipmentRepository.findAll(
                ShipmentSpecification.filterShipments(
                        status,
                        shipmentType,
                        senderId,
                        receiverId),
                pageable);

        return shipmentPage.map(this::mapToGetShipmentResponse);
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
                SHIPMENT_NOT_FOUND + shipmentId));

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

    @Override
    public UpdateShipmentResponse updateShipmentStatus(
            UUID shipmentId,
            UpdateShipmentStatusRequest request) {

        // 1. Shipment find karo
        ShipmentEntity shipment = shipmentRepository.findById(shipmentId)
            .orElseThrow(() -> new ResourceNotFoundException(
                SHIPMENT_NOT_FOUND + shipmentId));

        // 2. Purana status save karo
        String oldStatus = shipment.getStatus().name();

        if (shipment.getStatus() == request.getStatus()) {
            throw new IllegalArgumentException(
                    "Shipment is already in status: " + request.getStatus());
        }
        // 3. Naya status set karo
        shipment.setStatus(request.getStatus());
        shipment.setUpdatedAt(LocalDateTime.now());

        // 4. Database mein save karo
        ShipmentEntity updatedShipment
                = shipmentRepository.save(shipment);

        // 5. Kafka event banao
        ShipmentStatusUpdated event = ShipmentStatusUpdated.builder()
                .shipmentId(updatedShipment.getId())
                .trackingNumber(updatedShipment.getTrackingNumber())
                .oldStatus(oldStatus)
                .newStatus(updatedShipment.getStatus().name())
                .updatedAt(updatedShipment.getUpdatedAt())
                .build();

        // 6. Kafka par publish karo
        shipmentEventProducer.publishStatusUpdatedEvent(event);

        // 7. Response banao
        UpdateShipmentResponse response = new UpdateShipmentResponse();

        response.setTrackingNumber(updatedShipment.getTrackingNumber());
        response.setStatus(updatedShipment.getStatus());
        response.setUpdatedAt(updatedShipment.getUpdatedAt());
        response.setMessage("Shipment status updated successfully.");

        return response;
    }
}
