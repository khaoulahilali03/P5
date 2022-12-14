package com.safetynet.alerts.SafetyNetAlerts.service;

import com.safetynet.alerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynet.alerts.SafetyNetAlerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecordService() {
    }

    public List<MedicalRecord> findAll(){
        return medicalRecordRepository.findAll();
    }

    public MedicalRecord findByName(String firstName, String lastName) { return medicalRecordRepository.findByName(firstName,lastName);}

    public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord) { return medicalRecordRepository.addMedicalRecord(medicalRecord);}

    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {return medicalRecordRepository.saveMedicalRecord(medicalRecord);}
    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName) { return medicalRecordRepository.deleteMedicalRecord(firstName,lastName);}
}
