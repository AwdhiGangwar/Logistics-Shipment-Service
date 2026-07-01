package com.Logistics.shipmentservice.controller;

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

import com.Logistics.shipmentservice.dto.request.CreateShipmentRequest;
import com.Logistics.shipmentservice.dto.request.UpdateShipmentRequest;
import com.Logistics.shipmentservice.dto.response.CreateShipmentResponse;
import com.Logistics.shipmentservice.dto.response.GetShipmentResponse;
import com.Logistics.shipmentservice.dto.response.UpdateShipmentResponse;
import com.Logistics.shipmentservice.service.ShipmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<CreateShipmentResponse> createShipment(
            @Valid @RequestBody CreateShipmentRequest request) {

        CreateShipmentResponse response = shipmentService.createShipment(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<GetShipmentResponse> getShipmentById(
            @PathVariable UUID shipmentId) {

        GetShipmentResponse response = shipmentService.getShipmentById(shipmentId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GetShipmentResponse>> getAllShipments() {

        List<GetShipmentResponse> response = shipmentService.getAllShipments();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{shipmentId}")
    public ResponseEntity<UpdateShipmentResponse> updateShipment(
            @PathVariable UUID shipmentId,
            @Valid @RequestBody UpdateShipmentRequest request) {

        UpdateShipmentResponse response = shipmentService.updateShipment(shipmentId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<String> deleteShipment(@PathVariable UUID shipmentId) {

        shipmentService.deleteShipment(shipmentId);

        return ResponseEntity.ok("Shipment deleted successfully");
    }
}
