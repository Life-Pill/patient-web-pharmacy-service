package com.lifepill.pharmacyservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifepill.pharmacyservice.model.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findByPharmacyAddressCity(String pharmacyAddressCity);

    List<Pharmacy> findByPharmacyAddressDistrict(String pharmacyAddressDistrict);
}
