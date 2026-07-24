package com.logistics.warehouseservice.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseLocationRequest {

    @NotNull
    private UUID shipmentId;

    @NotBlank
    private String warehouseName;

    @NotBlank
    private String warehouseAddress;

    @NotBlank
    private String status;
}
