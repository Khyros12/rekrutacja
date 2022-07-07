package com.wojciech.olszewski.flight;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private final static String DEFAULT_JSON_DATA = "/flight.json";
    private final List<Flight> flightsData = new ArrayList<>();

    @Value("classpath:flight.json")
    Resource resourceFile;

    @PostConstruct
    public void postConstruct() throws IOException {
        InputStream inputStream;
        if (resourceFile != null) {
            inputStream = resourceFile.getInputStream();
        } else {
            inputStream = Flight.class.getResourceAsStream(DEFAULT_JSON_DATA);
        }

        Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-dd'T'hh:mm:ssZ").create();
        assert inputStream != null;
        Flight[] data = gson.fromJson(new InputStreamReader(inputStream), Flight[].class);
        this.flightsData.addAll(Arrays.asList(data));
    }

    public Flight getFlight(int flightNumber, Date departureDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return flightsData.stream()//
                .filter(flight -> flight.getFlightNumber() == flightNumber && format.format(flight.getDepartureDate())//
                        .equals(format.format(departureDate))).findFirst().orElse(null);
    }

    public List<Flight> getDepartingFlights(final String iataCode, Date date) {
        return flightsData.stream().filter(flight -> departingFilter(flight, iataCode, date)).collect(Collectors.toList());
    }

    public List<Flight> getArrivingFlights(final String iataCode, Date date) {
        return flightsData.stream().filter(flight -> arrivingFilter(flight, iataCode, date)).collect(Collectors.toList());
    }


    private boolean departingFilter(Flight flight, String iataAirportCode, Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return flight.getDepartureAirportIATACode().equals(iataAirportCode) &&//
                format.format(flight.getDepartureDate()).equals(format.format(date));
    }


    private boolean arrivingFilter(Flight flight, String iataAirportCode, Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return flight.getArrivalAirportIATACode().equals(iataAirportCode) &&//
                format.format(flight.getDepartureDate()).equals(format.format(date));
    }
}
