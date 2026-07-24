package com.logistics.warehouseservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.warehouseservice.dto.WarehouseLocationRequest;
import com.logistics.warehouseservice.dto.WarehouseLocationResponse;
import com.logistics.warehouseservice.service.WarehouseLocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseLocationController {

    private final WarehouseLocationService service;

    public WarehouseLocationController(WarehouseLocationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WarehouseLocationResponse> createLocation(
            @Valid @RequestBody WarehouseLocationRequest request) {
        WarehouseLocationResponse response = service.createWarehouseLocation(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<WarehouseLocationResponse>> getLocationsByShipmentId(
            @PathVariable UUID shipmentId) {
        List<WarehouseLocationResponse> response = service.getLocationsByShipmentId(shipmentId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<WarehouseLocationResponse> updateLocation(
            @PathVariable UUID locationId,
            @Valid @RequestBody WarehouseLocationRequest request) {
        WarehouseLocationResponse response = service.updateWarehouseLocation(locationId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID locationId) {
        service.deleteWarehouseLocation(locationId);
        return ResponseEntity.noContent().build();
    }
}
