package airline;


import java.awt.datatransfer.FlavorListener;
import java.util.HashMap;
import java.util.Map;

public class AirportAdmin {
    private static final AirportAdmin instance = new AirportAdmin();
    private FlightManager flightManager;
    private LocationManager locationManager;

    public static AirportAdmin getInstance() {
        return instance;
    }

    private AirportAdmin() {

        flightManager = FlightManager.getInstance();
        locationManager = LocationManager.getInstance();

        Flight f1 = new Flight("Boeing 777-200LR", "Hyderabad", "Delhi");
        Flight f2 = new Flight("Airbus A319 V2", "Hyderabad", "Delhi");
        Flight f3 = new Flight("Airbus A320", "Mumbai", "Delhi");
        Flight f4 = new Flight("c1", "Delhi", "Mumbai");

        flightManager.addFlight(f1);
        locationManager.updateLocationsForNewFlight(f1);
        flightManager.addFlight(f2);
        locationManager.updateLocationsForNewFlight(f2);
        flightManager.addFlight(f3);
        locationManager.updateLocationsForNewFlight(f3);
        flightManager.addFlight(f4);
        locationManager.updateLocationsForNewFlight(f4);
    }

    public FlightManager getFlightManager() {
        return flightManager;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }




}
