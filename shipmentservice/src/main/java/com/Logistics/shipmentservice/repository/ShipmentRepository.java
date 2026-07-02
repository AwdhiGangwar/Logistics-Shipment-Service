package com.Logistics.shipmentservice.repository;

import com.Logistics.shipmentservice.entity.ShipmentEntity;
import com.Logistics.shipmentservice.enums.ShipmentStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentEntity, UUID> {

    Optional<ShipmentEntity> findByTrackingNumber(String trackingNumber);

    boolean existsByTrackingNumber(String trackingNumber);

    List<ShipmentEntity> findBySenderId(UUID senderId);

    List<ShipmentEntity> findByReceiverId(UUID receiverId);

    List<ShipmentEntity> findByStatus(ShipmentStatus status);

}