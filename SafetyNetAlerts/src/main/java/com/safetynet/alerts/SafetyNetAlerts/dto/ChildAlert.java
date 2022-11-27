package com.safetynet.alerts.SafetyNetAlerts.dto;

import com.safetynet.alerts.SafetyNetAlerts.model.Person;

import java.util.List;

public class ChildAlert {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<Person> adultsLivingWithChildren;

    public ChildAlert(String firstName, String lastName, Integer age, List<Person> adultsLivingWithChildren) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.adultsLivingWithChildren = adultsLivingWithChildren;
    }

    public ChildAlert() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Person> getAdultsLivingWithChildren() {
        return adultsLivingWithChildren;
    }

    public void setAdultsLivingWithChildren(List<Person> adultsLivingWithChildren) {
        this.adultsLivingWithChildren = adultsLivingWithChildren;
    }

    @Override
    public String toString() {
        return "ChildAlert{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", adultsLivingWithChildren=" + adultsLivingWithChildren +
                '}';
    }
}
