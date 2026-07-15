package com.logistics.trackingservice.entity;
import com.logistics.trackingservice.enums.TrackingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tracking_events")
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID trackingId;

    @Column(nullable = false)
    private UUID shipmentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrackingStatus status;

    @Column(nullable = true)
    private String location;
    
    @Column(nullable = true , length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDateTime eventTime;
}