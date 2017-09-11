package airline.repositories;

import airline.models.Flight;
import airline.models.FlightBuilder;
import airline.models.TravelClass;
import airline.models.TravelClassType;
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
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 20000,8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 13000,35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 6000,195))
                .build();
        final Flight flight2 = new FlightBuilder()
                .withFlightNumber("F2")
                .withSource("HYD")
                .withDestination("PUN")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Airbus", "A319 V2")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 0,0))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 0,0))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 4000,144))
                .build();
        final Flight flight3 = new FlightBuilder()
                .withFlightNumber("F3")
                .withSource("BLR")
                .withDestination("PUN")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Airbus", "A321")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 0,0))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 10000,20))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 5000,152))
                .build();
        final Flight flight4 = new FlightBuilder()
                .withFlightNumber("F4")
                .withSource("HYD")
                .withDestination("BLR")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Boeing", "777-200LR(77L)")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 20000,8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 13000,35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 6000,195))
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


