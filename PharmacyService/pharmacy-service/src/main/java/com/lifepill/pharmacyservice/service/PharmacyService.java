package com.lifepill.pharmacyservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifepill.pharmacyservice.model.Pharmacy;
import com.lifepill.pharmacyservice.repo.PharmacyRepository;
import com.lifepill.pharmacyservice.util.MissingParameterException;
import com.lifepill.pharmacyservice.util.ResourceNotFoundException;

@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    // get all pharmacies
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }

    // get all pharmacies by district
    public List<Pharmacy> getPharmacyByDistrict(String district) {
        return pharmacyRepository.findByPharmacyAddressDistrict(district);
    }

    // get all pharmacies by city
    public List<Pharmacy> getPharmacyByCity(String city) {
        return pharmacyRepository.findByPharmacyAddressCity(city);
    }

    // get pharmacy by id
    public Pharmacy getPharmacy(Long id) {
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(id);

        if (pharmacy.isEmpty()) {
            throw new ResourceNotFoundException("Pharmacy with ID " + id + " not found.");
        } else {
            Pharmacy exisPharmacy = pharmacy.get();
            return exisPharmacy;
        }
    }

    // add new pharmacy
    public Pharmacy addPharmacy(Pharmacy newPharmacy) {
        // Empty full name error handling
        if (newPharmacy.getPharmacyName().isEmpty()) {
            throw new MissingParameterException("Full Name cannot be Empty");
        }

        // Empty email error handling
        if (newPharmacy.getPharmacyEmail().isEmpty()) {
            throw new MissingParameterException("Email cannot be Empty");
        }

        // Empty mobile number error handling
        if (newPharmacy.getPharmacyMobileNumber().isEmpty()) {
            throw new MissingParameterException("Mobile Number cannot be Empty");
        }

        // Empty district name error handling
        if (newPharmacy.getPharmacyAddressDistrict().isEmpty()) {
            throw new MissingParameterException("Password cannot be Empty");
        }

        // Empty street name error handling
        if (newPharmacy.getPharmacyAddressCity().isEmpty()) {
            throw new MissingParameterException("Address Street cannot be Empty");
        }

        // Empty city name error handling
        if (newPharmacy.getPharmacyAddressStreet().isEmpty()) {
            throw new MissingParameterException("Address City cannot be Empty");
        }

        // Setting pharmacy open status value if it is null
        if (newPharmacy.getOpenStatus() == null) {
            newPharmacy.setOpenStatus(false);
        }

        return pharmacyRepository.save(newPharmacy);
    }

    // update pharmacy open status
    public Pharmacy updatePharmacyOpenStatus(Long id) {
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(id);

        if (pharmacy.isEmpty()) {
            throw new ResourceNotFoundException("Pharmacy with ID " + id + " not found.");
        }

        Pharmacy existingPharmacy = pharmacy.get();

        // toggling the open status
        existingPharmacy.setOpenStatus(!existingPharmacy.getOpenStatus());

        return pharmacyRepository.save(existingPharmacy);
    }

    // update password
    public String updatePharmacyPassword(Long pharmacyId, String newPassword) {
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(pharmacyId);

        // Customer Id not found error handling
        if (pharmacy.isEmpty()) {
            throw new ResourceNotFoundException("Customer with ID " + pharmacyId + " not found.");
        }

        if (newPassword.isEmpty()) {
            throw new MissingParameterException("New Password cannot be Empty");
        }

        Pharmacy existingPharmacy = pharmacy.get();

        existingPharmacy.setPharmacyPassword(hashPassword(newPassword));
        pharmacyRepository.save(existingPharmacy);
        return "Password Successfully Updated";
    }

    // update pharmacy
    public Pharmacy updatePharmacy(Long pharmacyId, Pharmacy updatedPharmacy) {
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(pharmacyId);

        // error handling
        if (pharmacy.isEmpty()) {
            throw new ResourceNotFoundException("Order with ID " + pharmacyId + " not found.");
        }

        if (updatedPharmacy.getPharmacyName().isEmpty()) {
            throw new MissingParameterException("Pharmacy Name cannot be Empty");
        }

        if (updatedPharmacy.getPharmacyMobileNumber().isEmpty()) {
            throw new MissingParameterException("Pharmacy Mobile Number cannot be Empty");
        }

        if (updatedPharmacy.getPharmacyEmail().isEmpty()) {
            throw new MissingParameterException("Pharmacy Email cannot be Empty");
        }

        if (updatedPharmacy.getPharmacyAddressStreet().isEmpty()) {
            throw new MissingParameterException("Address Street cannot be Empty");
        }

        if (updatedPharmacy.getPharmacyAddressDistrict().isEmpty()) {
            throw new MissingParameterException("Address District cannot be Empty");
        }

        if (updatedPharmacy.getPharmacyAddressCity().isEmpty()) {
            throw new MissingParameterException("Address City cannot be Empty");
        }

        Pharmacy existingPharmacy = pharmacy.get();

        existingPharmacy.setPharmacyName(updatedPharmacy.getPharmacyName());
        existingPharmacy.setPharmacyEmail(updatedPharmacy.getPharmacyEmail());
        existingPharmacy.setPharmacyMobileNumber(updatedPharmacy.getPharmacyMobileNumber());
        existingPharmacy.setPharmacyAddressStreet(updatedPharmacy.getPharmacyAddressStreet());
        existingPharmacy.setPharmacyAddressDistrict(updatedPharmacy.getPharmacyAddressDistrict());
        existingPharmacy.setPharmacyAddressCity(updatedPharmacy.getPharmacyAddressCity());

        return pharmacyRepository.save(existingPharmacy);
    }

    // hashing the password
    public String hashPassword(String password) {
        return password;
    }

    // delete pharmacy
    public void deletePharmacy(Long id) {
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(id);

        // Customer Id not found error handling
        if (pharmacy.isEmpty()) {
            throw new ResourceNotFoundException("Pharmacy with ID " + id + " not found.");
        }

        pharmacyRepository.deleteById(id);
    }

    // delete all pharmacies
    public void deleteAllPharmacies() {
        pharmacyRepository.deleteAll();
    }
}
