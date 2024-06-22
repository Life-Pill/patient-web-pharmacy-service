package com.lifepill.pharmacyservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lifepill.pharmacyservice.model.PrescriptionImage;
import com.lifepill.pharmacyservice.service.PrescriptionImageService;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("prescriptionImages")
public class PrescriptionImageController {
    @Autowired
    private PrescriptionImageService prescriptionService;

    // fetch a prescription image
    @Tag(name = "get", description = "GET methods of Pharmacy service API")
    @Operation(description = "Fetch a prescription image")
    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
        PrescriptionImage prescription = prescriptionService.getPrescription(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(prescription.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + prescription.getFileName() + "\"")
                .body(new ByteArrayResource(prescription.getFile()));
    }
}
