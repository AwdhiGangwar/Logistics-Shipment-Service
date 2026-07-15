package com.logistics.trackingservice.service;

import java.util.List;
import java.util.UUID;

import com.logistics.trackingservice.entity.TrackingEvent;
import com.logistics.trackingservice.event.ShipmentStatusUpdated;

public interface TrackingService {

    void saveTrackingEvent(ShipmentStatusUpdated event);

    List<TrackingEvent> getTrackingHistoryByShipmentId(UUID shipmentId);

    List<TrackingEvent> getTrackingHistoryByTrackingNumber(String trackingNumber);

}