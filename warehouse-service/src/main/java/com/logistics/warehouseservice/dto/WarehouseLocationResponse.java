package main.java.com.logistics.warehouseservice.dto;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseLocationResponse {

    private UUID id;
    private UUID shipmentId;
    private String warehouseName;
    private String warehouseAddress;
    private String status;
    private LocalDateTime recordedAt;
}
