package airline.model;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author: Pratibhasagar V.
 */
public class FlightBuilder {
    private String flightNumber;
    private String source;
    private String destination;
    private String dateOfDeparture;
    private Airplane airplane;
    private Map<TravelClassType, TravelClass> travelClassMap;

    public FlightBuilder(){
        this.travelClassMap = new EnumMap<>(TravelClassType.class);
    }

    public FlightBuilder withFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public FlightBuilder withSource(String source) {
        this.source = source;
        return this;
    }

    public FlightBuilder withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public FlightBuilder withDateOfDeparture(String dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
        return this;
    }

    public FlightBuilder withAirplane(String manufacturer, String modelNumber) {
        this.airplane = new Airplane(manufacturer, modelNumber);
        return this;
    }

    public FlightBuilder withTravelClassMap(TravelClassType travelClassType, TravelClass travelClass){
        this.travelClassMap.put(travelClassType,travelClass);
        return this;
    }

    public Flight build(){
        return new Flight( flightNumber, source, destination, dateOfDeparture,
                airplane, travelClassMap);
    }
}
