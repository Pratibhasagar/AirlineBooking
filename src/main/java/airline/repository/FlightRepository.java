package airline.repository;

import airline.model.Flight;
import airline.model.FlightBuilder;
import airline.model.TravelClass;
import airline.model.TravelClassType;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Pratibhasagar V.
 */

@Repository
public class FlightRepository {
    private final List<Flight> flights;

    public FlightRepository() {
        flights = new ArrayList<>();
        createDefaultFlights();
    }

    public void createDefaultFlights() {

        final Flight flight1 = new FlightBuilder()
                .withFlightNumber("F1")
                .withSource("HYD")
                .withDestination("BLR")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).toString())
                .withAirplane("Boeing", "777-200LR(77L)")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(20000, 8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(13000, 35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(6000, 195, 40))
                .build();
        final Flight flight2 = new FlightBuilder()
                .withFlightNumber("F2")
                .withSource("HYD")
                .withDestination("PUN")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Airbus", "A319 V2")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(0, 0))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(0, 0))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(4000, 144))
                .build();
        final Flight flight3 = new FlightBuilder()
                .withFlightNumber("F3")
                .withSource("BLR")
                .withDestination("PUN")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Airbus", "A321")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(0, 0))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(10000, 20))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(5000, 152))
                .build();
        final Flight flight4 = new FlightBuilder()
                .withFlightNumber("F4")
                .withSource("HYD")
                .withDestination("BLR")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Boeing", "777-200LR(77L)")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(20000, 8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(13000, 35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(6000, 195, 40))
                .build();

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}


