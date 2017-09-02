package airline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightManager {
    public static final FlightManager instance = new FlightManager();
    Map<String, Flight> flightsList;

    private FlightManager(){
        flightsList = new HashMap<String, Flight>();
    }
    public static FlightManager getInstance(){
        return instance;
    }

    public Map<String, Flight> getFlightsList() {
        return flightsList;
    }

    public void addFlight(Flight flight) {
        if (flightsList.containsKey(flight.getFlightNumber())) {
            System.out.println("Flight#" + flight.getFlightNumber() + " already in the system");
        }
        flightsList.put(flight.getFlightNumber(), flight);
    }

}
