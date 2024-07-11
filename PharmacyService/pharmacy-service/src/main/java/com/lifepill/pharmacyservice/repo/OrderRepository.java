package com.lifepill.pharmacyservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lifepill.pharmacyservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByPharmacyId(Long pharmacyId);
}