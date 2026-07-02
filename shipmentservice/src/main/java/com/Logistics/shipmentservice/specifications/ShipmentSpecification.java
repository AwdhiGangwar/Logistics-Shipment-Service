package com.Logistics.shipmentservice.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.Logistics.shipmentservice.entity.ShipmentEntity;
import com.Logistics.shipmentservice.enums.ShipmentStatus;
import com.Logistics.shipmentservice.enums.ShipmentType;

import jakarta.persistence.criteria.Predicate;

public class ShipmentSpecification {

    public static Specification<ShipmentEntity> filterShipments(
            ShipmentStatus status,
            ShipmentType shipmentType,
            UUID senderId,
            UUID receiverId) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("status"), status));
            }

            if (shipmentType != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("shipmentType"), shipmentType));
            }

            if (senderId != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("senderId"), senderId));
            }

            if (receiverId != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("receiverId"), receiverId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
