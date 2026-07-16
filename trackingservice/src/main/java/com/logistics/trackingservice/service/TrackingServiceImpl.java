package com.logistics.trackingservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logistics.trackingservice.entity.TrackingEvent;
import com.logistics.trackingservice.enums.TrackingStatus;
import com.logistics.trackingservice.event.ShipmentStatusUpdated;
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

        return repository.findByShipmentIdOrderByEventTimeAsc(shipmentId);
    }

    @Override
    public List<TrackingEvent> getTrackingHistoryByTrackingNumber(String trackingNumber) {

        return repository.findByTrackingNumberOrderByEventTimeAsc(trackingNumber);
    }
}