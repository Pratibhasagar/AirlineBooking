package airline.repositories;

import airline.models.Flight;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlightRepository {
    private static List<Flight> flights;

    public FlightRepository() {

        flights = new ArrayList<Flight>();
        createDefaultFlights();
    }

    public void createDefaultFlights() {


        ZonedDateTime zdtInUTC = ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC"));

        final Flight flight1 = new Flight("F1", "HYD", "BLR", 3,
                zdtInUTC.toString());
        final Flight flight2 = new Flight("F2", "HYD", "PUN", 2,
                zdtInUTC.plusDays(1).toString());
        final Flight flight3 = new Flight("F3", "BLR", "PUN", 2,
                zdtInUTC.plusDays(1).toString());
        final Flight flight4 = new Flight("F4", "HYD", "BLR", 2,
                zdtInUTC.plusDays(1).toString());

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}


