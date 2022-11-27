package com.safetynet.alerts.SafetyNetAlerts.service;

import com.safetynet.alerts.SafetyNetAlerts.dto.Flood;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonCoveredByStationNumber;
import com.safetynet.alerts.SafetyNetAlerts.model.FireStation;
import com.safetynet.alerts.SafetyNetAlerts.model.Person;
import com.safetynet.alerts.SafetyNetAlerts.repository.FireStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@Service
public class FireStationService {
    @Autowired
    FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }
    public List<FireStation> findAlL(){
        return fireStationRepository.findAlL();
    }
    public FireStation findByAddress(String address){
        return fireStationRepository.findByAddress(address);
    }
    public List<FireStation> addFireStation(FireStation fireStation){
        return fireStationRepository.addFireStation(fireStation);
    }
    public FireStation saveFireStation(FireStation fireStation){
        return fireStationRepository.saveFireStation(fireStation);
    }
    public List<FireStation> deleteFireStation(String station){
        return fireStationRepository.deleteFireStation(station);
    }
    //localhost:8080/phoneAlert?firestation=<firestation_number>
    public List<String> getPhoneNumbersForAStation(String station) { return fireStationRepository.getPhoneNumbersForAStation(station); }

    //http://localhost:8080/firestation?stationNumber=<station_number>
    public List<PersonCoveredByStationNumber> personCoveredBySpecificFireStationList(String station) throws ParseException { return fireStationRepository.personCoveredBySpecificFireStationList(station);}

    public List<Flood> getFlood(List<String> stations) throws ParseException { return fireStationRepository.getFlood(stations);}

}
