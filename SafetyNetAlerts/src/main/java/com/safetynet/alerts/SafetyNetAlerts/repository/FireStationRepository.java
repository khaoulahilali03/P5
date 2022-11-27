package com.safetynet.alerts.SafetyNetAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.SafetyNetAlerts.database.DataStore;
import com.safetynet.alerts.SafetyNetAlerts.dto.Flood;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonCoveredByStationNumber;
import com.safetynet.alerts.SafetyNetAlerts.model.FireStation;
import com.safetynet.alerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynet.alerts.SafetyNetAlerts.model.Person;
import com.safetynet.alerts.SafetyNetAlerts.service.MedicalRecordService;
import com.safetynet.alerts.SafetyNetAlerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class FireStationRepository {

    String fileName = "D:/OPC/Formation_Java/P5/SafetyNetAlertsbis/SafetyNetAlerts/src/main/resources/data.json";
    ObjectMapper mapper = new ObjectMapper();
    DataStore store = mapper.readValue(Paths.get(fileName).toFile(), DataStore.class);
    List<FireStation> fireStations = store.getFirestations();

@Autowired
    PersonService personService;
@Autowired
    MedicalRecordService medicalRecordService;

    public FireStationRepository() throws IOException {
    }

    public List<FireStation> findAlL() {
        return fireStations;
    }

    /* public List<FireStation> findByNumberStation (String station){
         List<FireStation>fireStationList = new ArrayList<>();
         for (FireStation fs : fireStations){
             if (fs.getStation().equals(station)){
                  fireStationList.add(fs);
             }
         }return fireStationList;
     }

     */
    public FireStation findByAddress(String address) {

        for (FireStation fs : fireStations) {
            if (fs.getAddress().equals(address)) {

                return fs;
            }
        }return null;
    }

    public List<FireStation> addFireStation(FireStation fireStation) {
        fireStations.add(fireStation);
        return fireStations;
    }

    public FireStation saveFireStation(FireStation fireStation) {

        String station = fireStation.getStation();
        fireStation.setStation(station);
        if (fireStation.getStation().equals(station)) {
            return null;
        } else {
            return fireStation;
        }
    }

    public List<FireStation> deleteFireStation(String station) {
        for (FireStation fs : fireStations) {
            if (fs.getStation().equals(station)) {
                fireStations.remove(fs);
            }
        }
        return fireStations;
    }

    //http://localhost:8080/firestation?stationNumber=<station_number>
    public List<PersonCoveredByStationNumber> personCoveredBySpecificFireStationList(String station) throws ParseException {
        /*Set<FireStation> fireStationsNonDuplicate = new LinkedHashSet<>(fireStations);
        List<FireStation> fireStationList = new ArrayList<>(fireStationsNonDuplicate);*/
        List<PersonCoveredByStationNumber> personCoveredBySpecificFireStationList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        int childCount = personService.countChilds(personList);
        int adultCount = personService.countAdults(personList);
        for (FireStation fireStation: fireStations){
            if (fireStation.getStation().equals(station)){
                for (Person person: personService.findAll()){
                    if (fireStation.getAddress().equals(person.getAddress())) {
               /*         MedicalRecord mr = medicalRecordService.findByName(person.getFirstName(),person.getLastName());
                        if (mr.getAge() <= 18) {
                            childCount =personService.countChilds(personList);
                        }else {
                            adultCount=personService.countAdults(personList);}*/
                        PersonCoveredByStationNumber personCoveredBySpecificFireStation = new PersonCoveredByStationNumber(person.getFirstName(),person.getLastName(),person.getAddress(),person.getPhone(),childCount,adultCount);
                        personCoveredBySpecificFireStationList.add(personCoveredBySpecificFireStation);
                    }
                }
            }
        }return personCoveredBySpecificFireStationList;
    }



    //http://localhost:8080/phoneAlert?firestation=<firestation_number>
   public List<String> getPhoneNumbersForAStation(String station) {
        List<String> phoneNumbers = new ArrayList<>();
            for (FireStation f : fireStations) {
                if (f.getStation().equals(station)){
                    for (Person p : personService.findAll()){
                        if (f.getAddress().equals(p.getAddress())){
                            phoneNumbers.add(p.getPhone());
                        }
                    }
                }
            }return phoneNumbers;
    }
    //http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    public List<Flood> getFlood(List<String> stations) throws ParseException {
        List<Flood> floodList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        for (String station : stations){
            for (FireStation fireStation:fireStations){
                if (fireStation.getStation().equals(station)){
                    for (Person person: personService.findAll()){
                        if (fireStation.getAddress().equals(person.getAddress())){
                            MedicalRecord mr = medicalRecordService.findByName(person.getFirstName(),person.getLastName());
                            Flood flood = new Flood(person.getFirstName(),person.getLastName(),person.getPhone(),mr.getAge(),mr.getMedications(),mr.getAllergies());
                            floodList.add(flood);
                        }

                    }
                }
            }
        }return floodList;
    }
}




