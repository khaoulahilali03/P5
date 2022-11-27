package com.safetynet.alerts.SafetyNetAlerts.dto;

import java.util.List;


public class PersonCoveredByStationNumber {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private int childcount;
    private int adultCount;


    public PersonCoveredByStationNumber() {
    }

    public PersonCoveredByStationNumber(String firstName, String lastName, String address, String phone, int childcount, int adultCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.childcount = childcount;
        this.adultCount = adultCount;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getChildcount() {
        return childcount;
    }

    public void setChildcount(int childcount) {
        this.childcount = childcount;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }
}
