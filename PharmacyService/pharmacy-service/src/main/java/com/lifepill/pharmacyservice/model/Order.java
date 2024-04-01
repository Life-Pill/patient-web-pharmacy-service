package com.lifepill.pharmacyservice.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order")
public class Order {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @NotNull
    @Column(name = "pharm_id", nullable = false)
    private Long pharmacyId;

    @NotNull
    @Column(name = "pharm_id", nullable = false)
    private Long customerId;

    @NotNull
    @Column(name = "status", nullable = false)
    private boolean orderStatus;

    @NotNull
    @Column(name = "amount", nullable = false)
    private double paymentAmount;

    @NotNull
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdOn;

    @NotNull
    @Column(name = "updated_time", nullable = false)
    private LocalDateTime updatedOn;
}
