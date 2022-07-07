package com.wojciech.olszewski.cargo;

import com.wojciech.olszewski.flight.Flight;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FlightCargoService {

    private final static String DEFAULT_JSON_DATA = "/cargo.json";
    private List<FlightCargo> flightCargoData = new ArrayList<>();

    @Value("classpath:cargo.json")
    Resource resourceFile;

    @PostConstruct
    public void postConstruct() throws IOException {

        InputStream inputStream;
        if (resourceFile != null) {
            inputStream = resourceFile.getInputStream();
        } else {
            inputStream = Flight.class.getResourceAsStream(DEFAULT_JSON_DATA);
        }
        Gson gson = new Gson();
        FlightCargo[] data = gson.fromJson(new InputStreamReader(inputStream), FlightCargo[].class);
        this.flightCargoData.addAll(Arrays.asList(data));
    }

    public FlightCargo getCargo(long id) {
        return flightCargoData.stream().filter(flightCargo -> flightCargo.getFlightId() == id).findFirst().orElse(null);
    }

    public int countBaggagePiecesForFlights(List<Flight> flights) {
        return flights.stream().map(flight -> getCargo(flight.getFlightId())).map(FlightCargo::getBaggage).flatMap(List::stream).mapToInt(Items::getPieces).sum();
    }

}
