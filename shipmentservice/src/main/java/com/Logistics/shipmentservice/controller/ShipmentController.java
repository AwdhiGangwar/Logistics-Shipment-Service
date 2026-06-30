package com.Logistics.shipmentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Logistics.shipmentservice.dto.request.CreateShipmentRequest;
import com.Logistics.shipmentservice.dto.response.CreateShipmentResponse;
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
}
