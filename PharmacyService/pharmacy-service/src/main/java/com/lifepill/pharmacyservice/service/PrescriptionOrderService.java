package com.lifepill.pharmacyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifepill.pharmacyservice.model.PrescriptionOrder;
import com.lifepill.pharmacyservice.repo.PrescriptionOrderRepository;
import com.lifepill.pharmacyservice.util.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionOrderService {

    @Autowired
    private PrescriptionOrderRepository prescriptionOrderRepository;

    // get all active prescription orders
    public List<PrescriptionOrder> getAllPrescriptionOrders(Long pharmacyId) {
        return prescriptionOrderRepository.findByOrderStatus(false);
    }

    // get a specific prescription orders related to a specific pharmacy
    public Optional<PrescriptionOrder> getPrescriptionOrder(String prescriptionOrderId) {
        Optional<PrescriptionOrder> prescriptionOrder = prescriptionOrderRepository.findById(prescriptionOrderId);

        if (prescriptionOrder.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId + " not found.");
        } else {
            return prescriptionOrder;
        }
    }

    // update the status of the available pharmacies list
    public PrescriptionOrder updatePrescriptionOrderAvailablility(String prescriptionOrderId, Long pharmacyId,
            String reply) {
        Optional<PrescriptionOrder> prescriptionOrder = prescriptionOrderRepository.findById(prescriptionOrderId);

        if (prescriptionOrder.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + prescriptionOrderId +
                    " not found.");
        }

        PrescriptionOrder existingPrescriptionOrder = prescriptionOrder.get();

        if (existingPrescriptionOrder.isOrderStatus()) {
            throw new ResourceNotFoundException("Order has been closed.");
        }

        existingPrescriptionOrder.getAvailablePharmacies().put(pharmacyId, reply);
        prescriptionOrderRepository.save(existingPrescriptionOrder);
        return existingPrescriptionOrder;
    }
}
