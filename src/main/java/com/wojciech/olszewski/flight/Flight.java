package com.wojciech.olszewski.flight;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Flight {

    private long flightId;
    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private Date departureDate;
}
