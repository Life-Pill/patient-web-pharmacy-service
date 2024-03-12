package com.lifepill.pharmacyservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pharmacy")
public class Pharmacy {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pharm_id")
    private Long pharmacyId;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name", nullable = false, length = 200)
    private String pharmacyName;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String pharmacyEmail;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "mobile_number", nullable = false, unique = true, length = 50)
    private String pharmacyMobileNumber;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address_street", nullable = false, length = 200)
    private String pharmacyAddressStreet;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address_city", nullable = false, length = 200)
    private String pharmacyAddressCity;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address_district", nullable = false, length = 100)
    private String pharmacyAddressDistrict;

    @Column(name = "open_status")
    private Boolean openStatus;

    @Column(name = "rating")
    private int pharmacyRating;
}
