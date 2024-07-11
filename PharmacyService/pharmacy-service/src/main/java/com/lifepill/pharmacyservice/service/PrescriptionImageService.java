package com.lifepill.pharmacyservice.service;

import com.lifepill.pharmacyservice.model.PrescriptionImage;
import com.mongodb.client.gridfs.model.GridFSFile;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PrescriptionImageService {
    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    public PrescriptionImage getPrescription(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));

        PrescriptionImage prescription = new PrescriptionImage();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            prescription.setFileName(gridFSFile.getFilename());

            prescription.setFileType(gridFSFile.getMetadata().get("_contentType").toString());

            prescription.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());

            prescription.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
        }

        return prescription;
    }
}
