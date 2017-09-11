package airline.services;

import airline.models.TravelClassType;

/**
 * @author: Pratibhasagar V.
 */
public class SearchCriteria {
    private String source;
    private String destination;
    private int numberOfPassengers = 1;
    private String date;
    private TravelClassType travelClassType;

    public SearchCriteria() {
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public String getDate() {
        return date;
    }

    public TravelClassType getTravelClassType() {
        return travelClassType;
    }

    public void setTravelClassType(TravelClassType travelClassType) {
        this.travelClassType = travelClassType;
    }
}
