package airline.models;

import java.time.ZonedDateTime;
import java.util.EnumMap;

/**
 * @author: Pratibhasagar V.
 */
public class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private String dateOfDeparture;
    private Airplane airplane;
    private EnumMap<TravelClassType, TravelClass> travelClassMap;

    public Flight(String flightNumber, String source, String destination, String dateOfDeparture,
                  Airplane airplane, EnumMap<TravelClassType, TravelClass> travelClassMap) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.dateOfDeparture = dateOfDeparture;
        this.airplane = airplane;
        this.travelClassMap = travelClassMap;
    }

    public boolean startsAtSource(String source) {
        return this.source.equalsIgnoreCase(source);
    }

    public boolean reachesDestination(String destination) {
        return this.destination.equalsIgnoreCase(destination);
    }

    public int getAvailableSeatsForTravelClass(TravelClassType travelClassType) {
        return travelClassMap.get(travelClassType).getAvailableSeats();
    }

    public float getBasePriceForTravelClass(TravelClassType travelClassType) {
        return travelClassMap.get(travelClassType).getBaseFare();
    }

    public boolean travelsOnDate(String date) {
        return date.equals(ZonedDateTime.parse(this.dateOfDeparture).toLocalDate().toString());
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public Airplane getAirplane() {
        return airplane;
    }
}
