package com.logistics.trackingservice.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.logistics.trackingservice.dto.TrackingResponse;
import com.logistics.trackingservice.entity.TrackingEvent;
import com.logistics.trackingservice.service.TrackingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService trackingService;

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<TrackingResponse>> getTrackingByShipmentId(
            @PathVariable UUID shipmentId) {

        List<TrackingResponse> response = trackingService
                .getTrackingHistoryByShipmentId(shipmentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/tracking-number/{trackingNumber}")
    public ResponseEntity<List<TrackingResponse>> getTrackingByTrackingNumber(
            @PathVariable String trackingNumber) {

        List<TrackingResponse> response = trackingService
                .getTrackingHistoryByTrackingNumber(trackingNumber)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    private TrackingResponse mapToResponse(TrackingEvent event) {

        return TrackingResponse.builder()
                .shipmentId(event.getShipmentId())
                .trackingNumber(event.getTrackingNumber())
                .status(event.getStatus())
                .location(event.getLocation())
                .description(event.getDescription())
                .eventTime(event.getEventTime())
                .build();
    }
}