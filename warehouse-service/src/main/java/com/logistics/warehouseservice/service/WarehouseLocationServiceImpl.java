package com.logistics.warehouseservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.logistics.warehouseservice.dto.WarehouseLocationRequest;
import com.logistics.warehouseservice.dto.WarehouseLocationResponse;
import com.logistics.warehouseservice.entity.WarehouseLocation;
import com.logistics.warehouseservice.repository.WarehouseLocationRepository;

@Service
public class WarehouseLocationServiceImpl implements WarehouseLocationService {

    private final WarehouseLocationRepository repository;

    public WarehouseLocationServiceImpl(WarehouseLocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public WarehouseLocationResponse createWarehouseLocation(WarehouseLocationRequest request) {
        WarehouseLocation entity = new WarehouseLocation();
        entity.setShipmentId(request.getShipmentId());
        entity.setWarehouseName(request.getWarehouseName());
        entity.setWarehouseAddress(request.getWarehouseAddress());
        entity.setStatus(request.getStatus());
        entity.setRecordedAt(LocalDateTime.now());

        WarehouseLocation saved = repository.save(entity);
        return mapToResponse(saved);
    }

    @Override
    public List<WarehouseLocationResponse> getLocationsByShipmentId(UUID shipmentId) {
        return repository.findAllByShipmentId(shipmentId)
                .stream()
                .map((WarehouseLocation location) -> mapToResponse(location))
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseLocationResponse updateWarehouseLocation(UUID locationId, WarehouseLocationRequest request) {
        WarehouseLocation entity = repository.findById(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Warehouse location not found"));
        entity.setWarehouseName(request.getWarehouseName());
        entity.setWarehouseAddress(request.getWarehouseAddress());
        entity.setStatus(request.getStatus());
        entity.setRecordedAt(LocalDateTime.now());

        WarehouseLocation updated = repository.save(entity);
        return mapToResponse(updated);
    }

    @Override
    public void deleteWarehouseLocation(UUID locationId) {
        repository.deleteById(locationId);
    }

    private WarehouseLocationResponse mapToResponse(WarehouseLocation entity) {
        return WarehouseLocationResponse.builder()
                .id(entity.getId())
                .shipmentId(entity.getShipmentId())
                .warehouseName(entity.getWarehouseName())
                .warehouseAddress(entity.getWarehouseAddress())
                .status(entity.getStatus())
                .recordedAt(entity.getRecordedAt())
                .build();
    }
}
