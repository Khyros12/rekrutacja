package com.wojciech.olszewski.rest;

import com.wojciech.olszewski.cargo.FlightCargo;
import com.wojciech.olszewski.cargo.Items;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FlightWeightDTO {

    private final long flightId;
    private final int cargoWeight;
    private final int baggageWeight;
    private final int totalWeight;

    public FlightWeightDTO(long flightId, FlightCargo flightCargo) {
        this.flightId = flightId;
        this.cargoWeight = flightCargo
                        .getCargo().stream()
                        .mapToInt(Items::getWeightKg).sum();
        this.baggageWeight = flightCargo
                        .getBaggage().stream()
                        .mapToInt(Items::getWeightKg).sum();
        this.totalWeight = this.cargoWeight + this.baggageWeight;
    }
}
