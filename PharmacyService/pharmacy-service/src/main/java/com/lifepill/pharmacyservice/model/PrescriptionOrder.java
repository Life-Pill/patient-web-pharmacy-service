package com.lifepill.pharmacyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "prescriptionOrders")
@Data
@AllArgsConstructor
@Builder
public class PrescriptionOrder {
    @Id
    private String id;
    private Long customerId;
    private String prescriptionId;
    private String prescriptionImageId;
    private Map<Long, String> availablePharmacies;
    private Long selectedPharmacyId;
    private boolean orderStatus;
    private String customerMessage;
    private LocalDateTime createdOn;

    public PrescriptionOrder() {
        this.createdOn = LocalDateTime.now();
    }

    public PrescriptionOrder(Long customerId, String prescriptionId, boolean orderStatus) {
        this.customerId = customerId;
        this.prescriptionId = prescriptionId;
        this.orderStatus = orderStatus;
        this.createdOn = LocalDateTime.now();
    }
}
