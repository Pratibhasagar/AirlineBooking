package airline.models;

public class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private String dateOfDeparture;
    private Airplane airplane;

    public Flight(String flightNumber, String source, String destination,String date, Airplane airplane) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.dateOfDeparture = date;
        this.airplane = airplane;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(String datetime) {
        this.dateOfDeparture = datetime;
    }

    public Airplane getAirplane() {
        return airplane;
    }
    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}
