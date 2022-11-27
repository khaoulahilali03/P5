package com.safetynet.alerts.SafetyNetAlerts.dto;

import java.util.List;

public class PersonInfoAndNumberOfStation {

    private String firstName;
    private String lastName;
    private String phone;
    private Integer age;
    private List<String > medications;
    private List<String> allergies;
    private String numberStation;

    public PersonInfoAndNumberOfStation() {
    }

    public PersonInfoAndNumberOfStation(String firstName, String lastName, String phone, Integer age, List<String> medications, List<String> allergies, String numberStation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
        this.numberStation = numberStation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public String getNumberStation() {
        return numberStation;
    }

    public void setNumberStation(String numberStation) {
        this.numberStation = numberStation;
    }

    @Override
    public String toString() {
        return "PersonInfoAndNumberOfStation{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", medications=" + medications +
                ", allergies=" + allergies +
                ", numberStation='" + numberStation + '\'' +
                '}';
    }
}
