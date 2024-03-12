package com.lifepill.pharmacyservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lifepill.pharmacyservice.model.Pharmacy;
import com.lifepill.pharmacyservice.service.PharmacyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;

    // get all pharmacies
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Get all the pharmacies")
    @GetMapping
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyService.getAllPharmacies();
    }

    // get all pharmacies by district
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Get all the pharmacies located in a specific district")
    @GetMapping("/district={district}")
    public List<Pharmacy> getPharmacyByDistrict(@PathVariable String district) {
        return pharmacyService.getPharmacyByDistrict(district);
    }

    // get all pharmacies by city
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Get all the pharmacies located in a specific city")
    @GetMapping("/city={city}")
    public List<Pharmacy> getPharmacyByCity(@PathVariable String city) {
        return pharmacyService.getPharmacyByCity(city);
    }

    // get pharmacy by id
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Get a specific pharmacy")
    @GetMapping("/{id}")
    public Optional<Pharmacy> getPharmacy(@PathVariable Long id) {
        return pharmacyService.getPharmacy(id);
    }

    // add new pharmacy
    @Tag(name = "post", description = "POST methods of Pharmacy service API")
    @Operation(description = "Create a new pharmacy")
    @PostMapping
    public Pharmacy addPharmacy(@RequestBody Pharmacy newPharmacy) {
        return pharmacyService.addPharmacy(newPharmacy);
    }

    // update pharmacy open status
    @Tag(name = "put", description = "PUT methods of Pharmacy service API")
    @Operation(description = "Update open status of a pharmacy")
    @PutMapping("/{id}")
    public Pharmacy updatePharmacyOpenStatus(@PathVariable Long id) {
        return pharmacyService.updatePharmacyOpenStatus(id);
    }

    // delete pharmacy
    @Tag(name = "delete", description = "DELETE methods of Pharmacy service API")
    @Operation(description = "Delete a specific pharmacy")
    @DeleteMapping("/{id}")
    public String deletePharmacy(@PathVariable Long id) {
        pharmacyService.deletePharmacy(id);

        return "Pharmacy with the id " + id + " deleted successfully";
    }

    // delete all pharmacies
    @Tag(name = "delete", description = "DELETE methods of Pharmacy service API")
    @Operation(description = "Delete all the pharmacies")
    @DeleteMapping
    public String deleteAllPharmacies() {
        pharmacyService.deleteAllPharmacies();

        return "All pharmacies deleted successfully";
    }
}
