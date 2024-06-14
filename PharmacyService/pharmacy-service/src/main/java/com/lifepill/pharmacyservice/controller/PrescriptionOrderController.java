package com.lifepill.pharmacyservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lifepill.pharmacyservice.model.PrescriptionOrder;
import com.lifepill.pharmacyservice.service.PrescriptionOrderService;

@RestController
@RequestMapping("prescriptionOrders")
public class PrescriptionOrderController {
    @Autowired
    private PrescriptionOrderService prescriptionOrderService;

    // get all prescription orders
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Get all prescription orders related to a pharmacy")
    @GetMapping("/{pharmacyId}")
    public List<PrescriptionOrder> getAllPrescriptionOrders(@PathVariable Long pharmacyId) {
        return prescriptionOrderService.getAllPrescriptionOrders(pharmacyId);
    }

    // get a specific order
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Get a specific prescription order")
    @GetMapping("/{prescriptionOrderId}")
    public Optional<PrescriptionOrder> getPrescriptionOrder(@PathVariable String prescriptionOrderId) {
        return prescriptionOrderService.getPrescriptionOrder(prescriptionOrderId);
    }

    // update the selected prescription order
    @Tag(name = "put", description = "PUT methods of Pharmacy service API")
    @Operation(description = "Update an existing prescription order")
    @PutMapping("/{prescriptionOrderId}/{selectedPharmacyId}")
    public PrescriptionOrder updatePrescriptionOrderAvailablility(@PathVariable String prescriptionOrderId,
            @PathVariable Long pharmacyId) {
        return prescriptionOrderService.updatePrescriptionOrderAvailablility(prescriptionOrderId, pharmacyId);
    }
}
