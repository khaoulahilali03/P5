package com.safetynet.alerts.SafetyNetAlerts.service;


import com.safetynet.alerts.SafetyNetAlerts.dto.ChildAlert;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonInfo;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonInfoAndNumberOfStation;
import com.safetynet.alerts.SafetyNetAlerts.model.Person;
import com.safetynet.alerts.SafetyNetAlerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;
@Service
public  class PersonService {
   @Autowired
   PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonService() throws IOException {
    }


    public  List<Person> findAll(){
     return personRepository.findAll();
    }
    public Person findByName(String firstName, String lastName) { return  personRepository.findByName(firstName,lastName); }
    public Person findByAddress(String address) { return personRepository.findByAddress(address); }
    public List<Person> findByCity(String city) { return personRepository.findByCity(city);}
    public  List<Person> addPerson(Person person){
     return personRepository.addPerson(person);
    }
    public Person savePerson(Person person) { return personRepository.savePerson(person); }
    public List<Person> deletePerson(String firstName, String lastName) { return personRepository.deletePerson(firstName,lastName); }
    public int countAdults(List<Person> personList) throws ParseException { return personRepository.countAdults(personList);}
    public int countChilds(List<Person> personList) throws ParseException { return personRepository.countChilds(personList);}


    //localhost:8080/communityemail?city=<city>
    public List<String> getMailsByCity(String city) { return personRepository.getMailsByCity(city); }



    //localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    public PersonInfo getDataByFirstNameAndLastName(String firstName, String lastName) throws ParseException { return personRepository.getDataByFirstNameAndLastName(firstName,lastName);}


    //http://localhost:8080/childAlert?address=<address>
    public List<ChildAlert> getChildAlertListByAddress(String address) throws ParseException{
        return personRepository.getChildAlertListByAddress(address);
    }

    // http://localhost:8080/fire?address=<address>
    public List<PersonInfoAndNumberOfStation> personInfoAndNumberOfStationList(String address) throws ParseException {
        return personRepository.personInfoAndNumberOfStationList(address);
    }






}
