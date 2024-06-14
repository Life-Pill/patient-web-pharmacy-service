package com.lifepill.pharmacyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "prescriptionOrders")
@Data
@AllArgsConstructor
@Builder
public class PrescriptionOrder {
    @Id
    private String id;
    private Long customerId;
    private String prescriptionId;
    private List<String> availablePharmacies;
    private String selectedPharmacyId;
    private boolean orderStatus;
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
