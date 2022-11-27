package com.safetynet.alerts.SafetyNetAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.SafetyNetAlerts.database.DataStore;
import com.safetynet.alerts.SafetyNetAlerts.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    String fileName = "D:/OPC/Formation_Java/P5/SafetyNetAlertsbis/SafetyNetAlerts/src/main/resources/data.json";
    ObjectMapper mapper = new ObjectMapper();
    DataStore store = mapper.readValue(Paths.get(fileName).toFile(), DataStore.class);
    List<MedicalRecord> medicalRecords = store.getMedicalrecords();

    public MedicalRecordRepository() throws IOException {
    }

    public List<MedicalRecord> findAll(){return medicalRecords;}

    public MedicalRecord findByName(String firstName, String lastName){
        for (MedicalRecord mr:medicalRecords) {
            if ((mr.getFirstName().equals(firstName)) && (mr.getLastName().equals(lastName))){
                return mr;
            }

        }
        return null;
    }

    public List<MedicalRecord> addMedicalRecord(MedicalRecord medicalRecord){
        medicalRecords.add(medicalRecord);
        return medicalRecords;
    }

    public MedicalRecord saveMedicalRecord (MedicalRecord medicalRecord){
         String birthdate = medicalRecord.getBirthdate();
         medicalRecord.setBirthdate(birthdate);
         List<String> medications = medicalRecord.getMedications();
         medicalRecord.setMedications(medications);
         List<String> allergies = medicalRecord.getAllergies();
         medicalRecord.setAllergies(allergies);
         if ((medicalRecord.getBirthdate().equals(birthdate)) && (medicalRecord.getMedications().equals(medications)) && (medicalRecord.getAllergies().equals(allergies))){
             return null;
         }else {
             return medicalRecord;
         }
    }
    public List<MedicalRecord> deleteMedicalRecord(String firstName, String lastName){
        for (MedicalRecord mr:medicalRecords) {
            if ((mr.getFirstName().equals(firstName)) && (mr.getLastName().equals(lastName))){
                medicalRecords.remove(mr);
            }
            }return medicalRecords;
    }
}
