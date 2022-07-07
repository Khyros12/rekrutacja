package com.wojciech.olszewski.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(path = "api/flights")
public class DataFilterController {

    private final DataFilterService dataFilterService;

    @Autowired
    public DataFilterController(DataFilterService dataFilterService) {
        this.dataFilterService = dataFilterService;
    }

    @GetMapping(params = {"iataCode", "date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public BaggageAirportInfo getFlightsAndBaggage(@RequestParam String iataCode, @RequestParam String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return dataFilterService.getFlightsAndBaggage(iataCode, format.parse(date));
    }

    @GetMapping(params = {"flightNumber", "date"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public FlightWeightDTO getFlightsAndBaggage(@RequestParam int flightNumber, @RequestParam String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return dataFilterService.getCargoAndBaggageWeight(flightNumber, format.parse(date));
    }
}
