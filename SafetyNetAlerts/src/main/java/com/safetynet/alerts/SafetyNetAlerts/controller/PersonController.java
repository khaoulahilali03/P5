package com.safetynet.alerts.SafetyNetAlerts.controller;

import com.safetynet.alerts.SafetyNetAlerts.dto.ChildAlert;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonInfo;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonInfoAndNumberOfStation;
import com.safetynet.alerts.SafetyNetAlerts.model.Person;
import com.safetynet.alerts.SafetyNetAlerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
public class PersonController {
    private PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("person")
    public List<Person> personList(){
        return personService.findAll();
    }


    @PostMapping(value ="person")
    public List<Person> createPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @PutMapping("person/{firstName}/{lastName}")
    public List<Person> updatePerson(@PathVariable("firstName") final String firstName, @PathVariable("lastName") final String lastname, @RequestBody Person person){

        Person currentPerson = personService.findByName(firstName,lastname);
        if(currentPerson != null){

            String address = person.getAddress();
            if (address != null){
                currentPerson.setAddress(address);
            }

            String city = person.getCity();
            if (city != null){
                currentPerson.setCity(city);
            }

            String zip = person.getZip();
            if (zip != null){
                currentPerson.setZip(zip);
            }

            String phone = person.getPhone();
            if (phone != null){
                currentPerson.setPhone(phone);
            }

            String email = person.getEmail();
            if (email != null){
                currentPerson.setEmail(email);
            }

            personService.savePerson(currentPerson);
            return personService.addPerson(currentPerson);
        }else {
            return null;
        }
    }

    @DeleteMapping("person/{firstName}/{lastName}")
    public List<Person> deletePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        return personService.deletePerson(firstName,lastName);

    }

    //localhost:8080/communityemail?city=<city>
    @GetMapping("communityemail")
    public List<String> getMailsByCity(@RequestParam ("city")String city){
        List<String> emails = personService.getMailsByCity(city);
        return emails;
    }
    //localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    @GetMapping("personinfo")
    public PersonInfo getDataByFirstNameAndLastName (@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) throws ParseException {
        PersonInfo personinfo = personService.getDataByFirstNameAndLastName(firstName,lastName);
        return personinfo;
    }

    // http://localhost:8080/fire?address=<address>
    @GetMapping("fire")
    public List<PersonInfoAndNumberOfStation> personInfoAndNumberOfStationList(@RequestParam("address") String address) throws ParseException {
        return personService.personInfoAndNumberOfStationList(address);
    }
    //http://localhost:8080/childAlert?address=<address>
    @GetMapping("childalert")
    public List<ChildAlert> getChildAlertListByAddress(@RequestParam("address") String address) throws ParseException{
        return personService.getChildAlertListByAddress(address);
    }




}
