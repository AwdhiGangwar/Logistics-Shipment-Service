package com.logistics.trackingservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.logistics.trackingservice.enums.TrackingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingResponse {

    private UUID shipmentId;
    private String trackingNumber;
    private TrackingStatus status;
    private String location;
    private String description;
    private LocalDateTime eventTime;

}