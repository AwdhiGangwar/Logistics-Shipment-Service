package com.logistics.warehouseservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.warehouseservice.entity.WarehouseLocation;

@Repository
public interface WarehouseLocationRepository extends JpaRepository<WarehouseLocation, UUID> {

    List<WarehouseLocation> findAllByShipmentId(UUID shipmentId);
}
