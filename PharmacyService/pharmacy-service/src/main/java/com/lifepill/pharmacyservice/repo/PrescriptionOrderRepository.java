package com.lifepill.pharmacyservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lifepill.pharmacyservice.model.PrescriptionOrder;

import java.util.List;

public interface PrescriptionOrderRepository extends MongoRepository<PrescriptionOrder, String> {
    List<PrescriptionOrder> findByOrderStatus(boolean orderStatus);
}
