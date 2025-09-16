package com.collegeMS.CMS.Controllers;

import com.collegeMS.CMS.DTOs.AdmissionRecordDTO;
import com.collegeMS.CMS.Services.AdmissionRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/CMS/AdmissionRecord/")
public class AdmissionRecordController {
    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @GetMapping(value="{admissionId}")
    public ResponseEntity<AdmissionRecordDTO> getAddmissionRecordById(@PathVariable Long admissionId){
        return ResponseEntity.ok(admissionRecordService.getAddmissionRecordById(admissionId));
    }

    @GetMapping
    public ResponseEntity<List<AdmissionRecordDTO>> getAllAdmissionDetails(){
        return ResponseEntity.ok(admissionRecordService.getAllAdmissionDetails());
    }

    @PostMapping
    public ResponseEntity<AdmissionRecordDTO> addAddmissionRecord(@Valid @RequestBody AdmissionRecordDTO admissionDTO){
        return new ResponseEntity<>(admissionRecordService.addAddmissionRecord(admissionDTO), HttpStatus.CREATED);
    }

    @PutMapping(value="{admissionRecId}")
    public ResponseEntity<AdmissionRecordDTO> updateAddmissionRecord(@PathVariable Long admissionRecId, @Valid @RequestBody AdmissionRecordDTO admissionDTO){
        return ResponseEntity.ok(admissionRecordService.updateAddmissionRecord(admissionRecId,admissionDTO));
    }

    @DeleteMapping(value="{admissionRecId}")
    public ResponseEntity<Void> deleteAdmissionRecord(@PathVariable Long admissionRecId){
        boolean isDeleted = admissionRecordService.deleteAdmissionRecord(admissionRecId);
        return ResponseEntity.noContent().build();
    }
}
