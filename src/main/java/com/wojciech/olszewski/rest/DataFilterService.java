package com.wojciech.olszewski.rest;

import com.wojciech.olszewski.cargo.FlightCargo;
import com.wojciech.olszewski.cargo.FlightCargoService;
import com.wojciech.olszewski.flight.Flight;
import com.wojciech.olszewski.flight.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataFilterService {

    private final FlightService flightService;
    private final FlightCargoService flightCargoService;

    @Autowired
    DataFilterService(FlightService flightService, FlightCargoService flightCargoService) {
        this.flightService = flightService;
        this.flightCargoService = flightCargoService;
    }

    public FlightWeightDTO getCargoAndBaggageWeight(int flightNumber, Date departureDate) {
        Flight flight = flightService.getFlight(flightNumber, departureDate);
        FlightCargo flightCargo = flightCargoService.getCargo(flight.getFlightId());
        return new FlightWeightDTO(flight.getFlightId(), flightCargo);
    }

    public BaggageAirportInfo getFlightsAndBaggage(String iataCode, Date date) {
        List<Flight> departingFlights = flightService.getDepartingFlights(iataCode, date);
        List<Flight> arrivingFlights = flightService.getArrivingFlights(iataCode, date);

        return new BaggageAirportInfo(departingFlights.size(), arrivingFlights.size(),
                flightCargoService.countBaggagePiecesForFlights(departingFlights),
                flightCargoService.countBaggagePiecesForFlights(arrivingFlights));
    }
}