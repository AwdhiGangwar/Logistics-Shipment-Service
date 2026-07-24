package com.logistics.trackingservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logistics.trackingservice.entity.TrackingEvent;
import com.logistics.trackingservice.enums.TrackingStatus;
import com.logistics.trackingservice.event.ShipmentStatusUpdated;
import com.logistics.trackingservice.exception.ResourceNotFoundException;
import com.logistics.trackingservice.repository.TrackingEventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final TrackingEventRepository repository;

    @Override
    public void saveTrackingEvent(ShipmentStatusUpdated event) {

        TrackingEvent trackingEvent = new TrackingEvent();

        trackingEvent.setShipmentId(event.getShipmentId());
        trackingEvent.setTrackingNumber(event.getTrackingNumber());
        trackingEvent.setStatus(
                TrackingStatus.valueOf(event.getNewStatus())
        );
        trackingEvent.setEventTime(event.getUpdatedAt());

        repository.save(trackingEvent);
    }

    @Override
    public List<TrackingEvent> getTrackingHistoryByShipmentId(UUID shipmentId) {

        List<TrackingEvent> trackingEvents
                = repository.findByShipmentIdOrderByEventTimeAsc(shipmentId);

        if (trackingEvents.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No tracking history found for shipment id: " + shipmentId);
        }

        return trackingEvents;
    }

        @Override
    public List<TrackingEvent> getTrackingHistoryByTrackingNumber(String trackingNumber) {

        List<TrackingEvent> trackingEvents
                = repository.findByTrackingNumberOrderByEventTimeAsc(trackingNumber);

        if (trackingEvents.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No tracking history found for tracking number: " + trackingNumber);
        }

        return trackingEvents;
    }
}