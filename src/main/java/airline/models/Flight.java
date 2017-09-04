package airline.models;

public class Flight {
    private String flightNumber;
    private String source;
    private String destination;
    private int totalSeats;
    private int availableSeats;
    private String dateOfDeparture;

    public Flight() {

    }

    public Flight(String flightNumber, String source, String destination, int totalSeats, String date) {
        this.flightNumber = flightNumber;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.dateOfDeparture = date;
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

    public int getAvailableSeats() {
        return availableSeats;
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(String datetime) {
        this.dateOfDeparture = datetime;
    }
}
