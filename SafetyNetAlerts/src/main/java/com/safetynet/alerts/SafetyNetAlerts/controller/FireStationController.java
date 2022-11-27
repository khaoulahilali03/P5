package com.safetynet.alerts.SafetyNetAlerts.controller;

import com.safetynet.alerts.SafetyNetAlerts.dto.Flood;
import com.safetynet.alerts.SafetyNetAlerts.dto.PersonCoveredByStationNumber;
import com.safetynet.alerts.SafetyNetAlerts.model.FireStation;
import com.safetynet.alerts.SafetyNetAlerts.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.awt.datatransfer.FlavorListener;
import java.text.ParseException;
import java.util.List;
import java.util.Set;


@RestController
public class FireStationController {
    private FireStationService fireStationService;

    @Autowired
    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @GetMapping("firestation")
    public List<FireStation> fireStationList(){
        return fireStationService.findAlL();
    }

    @PostMapping("firestation")
    public List<FireStation> createFireStation(@RequestBody FireStation fireStation){
        return fireStationService.addFireStation(fireStation);
    }

    @PutMapping("firestation/{address}")
    public List<FireStation> updateFireStation(@PathVariable("address") final String address, @RequestBody FireStation fireStation){
        FireStation currentFireStation= fireStationService.findByAddress(address);
        if (currentFireStation != null){

            String station = fireStation.getStation();
            if (station != null){
                currentFireStation.setStation(station);
            }

            fireStationService.saveFireStation(currentFireStation);
            return fireStationService.addFireStation(currentFireStation);

        }else{
            return null;
        }
    }

    @DeleteMapping("firestation/{station}")
    public List<FireStation> deleteFireStation(@PathVariable("station") String station){
        return fireStationService.deleteFireStation(station);
    }

    //localhost:8080/phoneAlert?firestation=<firestation_number>
    @GetMapping("phonealert")
    public List<String> getPhoneNumbersForAStation(@RequestParam("station") String station){ return fireStationService.getPhoneNumbersForAStation(station); }

    //http://localhost:8080/firestation?stationNumber=<station_number>
    @GetMapping("firestationsn")
    public List<PersonCoveredByStationNumber> personCoveredBySpecificFireStationList(@RequestParam("station") String station) throws ParseException {
        return fireStationService.personCoveredBySpecificFireStationList(station);
    }

    @GetMapping("flood/stations")
    public List<Flood> getFlood(@RequestParam("stations") List<String> stations) throws ParseException {
        return fireStationService.getFlood(stations);
    }

}
