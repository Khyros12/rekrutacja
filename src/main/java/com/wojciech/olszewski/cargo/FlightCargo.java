package com.wojciech.olszewski.cargo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class FlightCargo {

    private long flightId;
    private List<Items> baggage;
    private List<Items> cargo;
}

