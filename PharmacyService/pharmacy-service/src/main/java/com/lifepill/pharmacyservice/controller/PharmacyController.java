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
    @GetMapping("/{pharmacyId}")
    public Optional<Pharmacy> getPharmacy(@PathVariable Long pharmacyId) {
        return pharmacyService.getPharmacy(pharmacyId);
    }

    // add new pharmacy
    @Tag(name = "post", description = "POST methods of Pharmacy service API")
    @Operation(description = "Create a new pharmacy")
    @PostMapping
    public Pharmacy addPharmacy(@RequestBody Pharmacy newPharmacy) {
        return pharmacyService.addPharmacy(newPharmacy);
    }

    // update pharmacy details
    @Tag(name = "put", description = "PUT methods of Pharmacy service API")
    @Operation(description = "Update pharmacy details")
    @PutMapping("/{pharmacyId}")
    public Pharmacy updatePharmacy(@PathVariable Long pharmacyId, @RequestBody Pharmacy updatedPharmacy) {
        return pharmacyService.updatePharmacy(pharmacyId, updatedPharmacy);
    }

    // update pharmacy open status
    @Tag(name = "put", description = "PUT methods of Pharmacy service API")
    @Operation(description = "Update open status of a pharmacy")
    @PutMapping("/status/{pharmacyId}")
    public Pharmacy updatePharmacyOpenStatus(@PathVariable Long pharmacyId) {
        return pharmacyService.updatePharmacyOpenStatus(pharmacyId);
    }

    // update password
    @Tag(name = "put", description = "PUT methods of Pharmacy service API")
    @Operation(description = "Change the password of an existing pharmacy")
    @PutMapping("changePassword/{pharmacyId}")
    public String updatePharmacyPassword(@PathVariable Long pharmacyId, @RequestBody Pharmacy updatedPharmacy) {
        return pharmacyService.updatePharmacyPassword(pharmacyId, updatedPharmacy.getPharmacyPassword());
    }

    // delete pharmacy
    @Tag(name = "delete", description = "DELETE methods of Pharmacy service API")
    @Operation(description = "Delete a specific pharmacy")
    @DeleteMapping("/{pharmacyId}")
    public String deletePharmacy(@PathVariable Long pharmacyId) {
        pharmacyService.deletePharmacy(pharmacyId);

        return "Pharmacy with the id " + pharmacyId + " deleted successfully";
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
