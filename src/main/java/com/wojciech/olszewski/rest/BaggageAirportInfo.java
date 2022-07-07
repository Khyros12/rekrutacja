package com.wojciech.olszewski.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BaggageAirportInfo {

    private final int flightsDeparting;
    private final int flightsArriving;
    private final int baggageDeparting;
    private final int baggageArriving;


}
