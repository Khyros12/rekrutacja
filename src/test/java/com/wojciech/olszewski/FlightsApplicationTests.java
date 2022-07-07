package com.wojciech.olszewski;

import com.wojciech.olszewski.cargo.FlightCargo;
import com.wojciech.olszewski.cargo.FlightCargoService;
import com.wojciech.olszewski.flight.Flight;
import com.wojciech.olszewski.flight.FlightService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

class FlightsApplicationTests {

	@Test
	void getFlightTest() throws IOException, ParseException {
		FlightService flightService = new FlightService();
		flightService.postConstruct();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		Flight flight = flightService.getFlight(6215, format.parse("2019-04-11T02:12:43-0200"));
		Assertions.assertNotNull(flight);
		Assertions.assertEquals("GDN", flight.getArrivalAirportIATACode());
	}

	@Test
	void getCargoTest() throws IOException {
		FlightCargoService flightCargoService = new FlightCargoService();
		flightCargoService.postConstruct();

		FlightCargo flightCargo = flightCargoService.getCargo(3);
		Assertions.assertEquals(3, flightCargo.getFlightId());
		Assertions.assertEquals(7, flightCargo.getBaggage().size());

	}
}
