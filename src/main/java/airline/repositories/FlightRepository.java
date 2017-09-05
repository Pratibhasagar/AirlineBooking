package airline.repositories;

import airline.models.Airplane;
import airline.models.TravelClassType;
import airline.models.Flight;
import airline.models.TravelClass;
import org.springframework.stereotype.Repository;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FlightRepository {
    private final List<Flight> flights;

    public FlightRepository() {
        flights = new ArrayList<Flight>();
        createDefaultFlights();
    }

    public void createDefaultFlights() {

        final Map<TravelClassType, TravelClass> travelClassMap1 = new HashMap<TravelClassType, TravelClass>();
        travelClassMap1.put(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 8));
        travelClassMap1.put(TravelClassType.BUSINESS, new TravelClass(TravelClassType.FIRST, 35));
        travelClassMap1.put(TravelClassType.ECONOMY, new TravelClass(TravelClassType.FIRST, 195));
        final Airplane airplane1 = new Airplane("Boeing", "777-200LR(77L)", travelClassMap1);

        final Map<TravelClassType, TravelClass> travelClassMap2 = new HashMap<TravelClassType, TravelClass>();
        travelClassMap2.put(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 0));
        travelClassMap2.put(TravelClassType.BUSINESS, new TravelClass(TravelClassType.FIRST, 0));
        travelClassMap2.put(TravelClassType.ECONOMY, new TravelClass(TravelClassType.FIRST, 144));
        final Airplane airplane2 = new Airplane("Airbus", "A319 V2", travelClassMap2);

        final Map<TravelClassType, TravelClass> travelClassMap3 = new HashMap<TravelClassType, TravelClass>();
        travelClassMap3.put(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 0));
        travelClassMap3.put(TravelClassType.BUSINESS, new TravelClass(TravelClassType.FIRST, 20));
        travelClassMap3.put(TravelClassType.ECONOMY, new TravelClass(TravelClassType.FIRST, 152));
        final Airplane airplane3 = new Airplane("Airbus", "A321", travelClassMap3);

        ZonedDateTime zdtInUTC = ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC"));

        final Flight flight1 = new Flight("F1", "HYD", "BLR",
                zdtInUTC.toString(), airplane1);
        final Flight flight2 = new Flight("F2", "HYD", "PUN",
                zdtInUTC.plusDays(1).toString(), airplane2);
        final Flight flight3 = new Flight("F3", "BLR", "PUN",
                zdtInUTC.plusDays(1).toString(), airplane3);
        final Flight flight4 = new Flight("F4", "HYD", "BLR",
                zdtInUTC.plusDays(1).toString(), airplane1);

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
    }

    public List<Flight> getFlights() {
        return flights;
    }
}


