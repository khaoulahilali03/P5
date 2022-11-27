package com.safetynet.alerts.SafetyNetAlerts.controller;

import com.safetynet.alerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynet.alerts.SafetyNetAlerts.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MedicalRecordController {
    private MedicalRecordService medicalRecordService;
    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }



    @GetMapping("medicalrecord")
    public List<MedicalRecord> medicalRecordList(){
        return medicalRecordService.findAll();
    }

    @PostMapping("medicalrecord")
    public List<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        return medicalRecordService.addMedicalRecord(medicalRecord);
    }

    @PutMapping("medicalrecord/{firstName}/{lastName}")
    public List<MedicalRecord> updateMedicalRecord(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastName,@RequestBody MedicalRecord medicalRecord){
        MedicalRecord currentMedicalRecord = medicalRecordService.findByName(firstName,lastName);
        if (currentMedicalRecord != null){

            String birthdate = medicalRecord.getBirthdate();
            if (birthdate != null){
                currentMedicalRecord.setBirthdate(birthdate);
            }

            List<String> medications = medicalRecord.getMedications();
            if (medications != null){
                currentMedicalRecord.setMedications(medications);
            }

            List<String> allergies = medicalRecord.getAllergies();
            if (allergies != null){
                currentMedicalRecord.setAllergies(allergies);
            }

            medicalRecordService.saveMedicalRecord(currentMedicalRecord);
            return medicalRecordService.addMedicalRecord(currentMedicalRecord);
        }else{
            return null;
        }
    }

    @DeleteMapping("medicalrecord/{firstName}/{lastName}")
    public List<MedicalRecord> deleteMedicalRecord(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        return medicalRecordService.deleteMedicalRecord(firstName,lastName);
    }
}
