package com.safetynet.alerts.SafetyNetAlerts.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.SafetyNetAlerts.database.DataStore;
import com.safetynet.alerts.SafetyNetAlerts.dto.ChildAlert;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonInfo;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonInfoAndNumberOfStation;
import com.safetynet.alerts.SafetyNetAlerts.model.FireStation;
import com.safetynet.alerts.SafetyNetAlerts.model.MedicalRecord;
import com.safetynet.alerts.SafetyNetAlerts.model.Person;
import com.safetynet.alerts.SafetyNetAlerts.service.FireStationService;
import com.safetynet.alerts.SafetyNetAlerts.service.MedicalRecordService;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Repository
public class PersonRepository  {
    String fileName = "D:/OPC/Formation_Java/P5/SafetyNetAlertsbis/SafetyNetAlerts/src/main/resources/data.json";
    ObjectMapper mapper = new ObjectMapper();
    DataStore store = mapper.readValue(Paths.get(fileName).toFile(), DataStore.class);
    List<Person> persons = store.getPersons();

    //@Autowired
    MedicalRecordService medicalRecordService;
    //@Autowired
    FireStationService fireStationService;

    public PersonRepository() throws IOException {
    }
    public List<Person> findAll() {
        return persons;
    }
    public Person findByName(String firstName, String lastName) {
        for (Person person : persons) {

            if ((person.getFirstName().equals(firstName)) && (person.getLastName().equals(lastName))) {
                return person;
            }
        }
        return null;
    }
    public Person findByAddress(String address){

        for (Person person : persons){
            if (person.getAddress().equals(address)){
                 return person;
            }
        }return null;
    }
    public List<Person> findByCity(String city){
        List<Person> p = new ArrayList<>();
        for (Person person : persons){
            if (person.getCity().equals(city)){
                p.add(person);
            }
        }return p;
    }

    public List<Person> addPerson(Person person) {
        persons.add(person);
        return persons;
    }

    public Person savePerson (Person person) {
        String address = person.getAddress();
        person.setAddress(address);
        String city = person.getCity();
        person.setCity(city);
        String zip = person.getZip();
        person.setZip(zip);
        String phone = person.getPhone();
        person.setPhone(phone);
        String email = person.getEmail();
        person.setEmail(email);
        if ((person.getAddress().equals(address)) && (person.getCity().equals(city)) && (person.getZip().equals(zip)) && (person.getPhone().equals(phone)) && (person.getEmail().equals(email))) {
            return null;

        }else {
            return person;
        }
    }

    public List<Person> deletePerson(String firstName, String lastName) {

        for (Person person : persons) {

            if ((person.getFirstName().equals(firstName)) && (person.getLastName().equals(lastName))) {
                persons.remove(person);
            }

        } return persons;
    }
    //http://localhost:8080/childAlert?address=<address>
    public List<ChildAlert> getChildAlertListByAddress(String address) throws ParseException {
        List<ChildAlert> childAlertList = new ArrayList<>();
        List<Person> adultPerson = new ArrayList<>();
        for (Person person : persons){
            if (person.getAddress().equals(address)){
                MedicalRecord medicalRecord = medicalRecordService.findByName(person.getFirstName(),person.getLastName());
                if (medicalRecord.getAge() > 18){
                    adultPerson.add(person);
                }else {
                    ChildAlert childAlert = new ChildAlert(person.getFirstName(),person.getLastName(),medicalRecord.getAge(),adultPerson);
                    childAlertList.add(childAlert);
                }
            }
        }return childAlertList;

    }
    // http://localhost:8080/fire?address=<address>
    public List<PersonInfoAndNumberOfStation> personInfoAndNumberOfStationList(String address) throws ParseException {
        List<PersonInfoAndNumberOfStation> personInfoAndNumberOfStationList = new ArrayList<>();

        for (Person person : persons){
            if (person.getAddress().equals(address)){
                MedicalRecord medicalRecord = medicalRecordService.findByName(person.getFirstName(),person.getLastName());
                FireStation fireStation = fireStationService.findByAddress(address);
                PersonInfoAndNumberOfStation personInfoAndNumberOfStation = new PersonInfoAndNumberOfStation(person.getFirstName(),person.getLastName(),person.getPhone(),medicalRecord.getAge(),medicalRecord.getMedications(),medicalRecord.getAllergies(),fireStation.getStation());
                personInfoAndNumberOfStationList.add(personInfoAndNumberOfStation);
            }
        }return personInfoAndNumberOfStationList;
    }







    //communityemail
    public List<String> getMailsByCity(String city){
        List<Person> personList = new ArrayList<>();
        List<String> emails = new ArrayList<>();
        for (Person p : persons){
            if (p.getCity().equals(city)){
                personList.add(p);
            }
        }
        for (Person person :personList){

                emails.add(person.getEmail());
        }
        return emails;
    }

    //localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    public PersonInfo getDataByFirstNameAndLastName(String firstName, String lastName) throws ParseException {
        for (Person p : persons){
                if ((p.getFirstName().equals(firstName))&&(p.getLastName().equals(lastName))){
                    MedicalRecord mr = medicalRecordService.findByName(firstName,lastName);
                    PersonInfo personInfo = new PersonInfo(p.getFirstName(), p.getLastName(),p.getAddress(),p.getEmail(),mr.getAge(),mr.getMedications(),mr.getAllergies());
                    return personInfo;

            }
        }return null;
    }




    // fire (probleme de type sur la parametre age !!!! A RESOUDRE RAPIDEMENT)
/*    public List<String > getPersonInfoAndNumberOfFireStationByAddress(String address) throws ParseException {
        List<String> person = new ArrayList<>();
        for (Person p : persons){
                if (p.getAddress().equals(address)){
                    MedicalRecord mr = medicalRecordService.findByName(p.getFirstName(),p.getLastName());{
                            person.add(p.getFirstName());
                            person.add(p.getLastName());
                            person.add(p.getPhone());
                            //person.add(fs.getStation());
                            person.add(String.valueOf(mr.getAge()));
                            person.add(String.valueOf(mr.getMedications()));
                            person.add(String.valueOf(mr.getAllergies()));
                        }
                    }
                }return person;
            }*/



    //A completer pour la liste des autres habitants du foyer à par les mineurs
/*    public List<String> getChildListByAddress(String address) throws ParseException {
        List<String> childList = new ArrayList<>();
        List<String > personToAdd = new ArrayList<>();
        for (Person p : persons){
            MedicalRecord mr = medicalRecordService.findByName(p.getFirstName(),p.getLastName());
                if ((p.getAddress().equals(address)) && (mr.getAge() <= 18)){
                    childList.add(p.getFirstName());
                    childList.add(p.getLastName());
                    childList.add(String.valueOf(mr.getAge()));
                    if (p.getAddress().equals(address)){
                        personToAdd.add(String.valueOf(p));

                    }


        }*/

    public int countAdults(List<Person> personList) throws ParseException {
        int adultsCount = 0;
        for (MedicalRecord mr : store.getMedicalrecords()){
            if (mr.getAge() > 18)
                adultsCount++;
        }
        return adultsCount;
    }

    public int countChilds(List<Person> personList) throws ParseException {
        int childsCount = 0;
        for (MedicalRecord mr : store.getMedicalrecords()){
            if (mr.getAge() <= 18)
                childsCount++;
        }

        return childsCount;
    }



}
