package com.logistics.trackingservice.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.trackingservice.entity.TrackingEvent;

public interface TrackingEventRepository
        extends JpaRepository<TrackingEvent, UUID> {

    List<TrackingEvent> findByShipmentIdOrderByEventTimeAsc(UUID shipmentId);

    List<TrackingEvent> findByTrackingNumberOrderByEventTimeAsc(String trackingNumber);

}
