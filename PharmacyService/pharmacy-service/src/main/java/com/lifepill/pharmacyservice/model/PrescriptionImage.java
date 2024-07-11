package com.lifepill.pharmacyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionImage {
    private String fileName;
    private String fileType;
    private String fileSize;
    private byte[] file;
}
