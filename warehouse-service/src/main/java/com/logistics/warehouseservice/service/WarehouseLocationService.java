package com.logistics.warehouseservice.service;

import java.util.List;
import java.util.UUID;

import com.logistics.warehouseservice.dto.WarehouseLocationRequest;
import com.logistics.warehouseservice.dto.WarehouseLocationResponse;

public interface WarehouseLocationService {

    WarehouseLocationResponse createWarehouseLocation(WarehouseLocationRequest request);

    List<WarehouseLocationResponse> getLocationsByShipmentId(UUID shipmentId);

    WarehouseLocationResponse updateWarehouseLocation(UUID locationId, WarehouseLocationRequest request);

    void deleteWarehouseLocation(UUID locationId);
}
