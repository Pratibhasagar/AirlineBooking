package airline;

public class Flight {
    private String flightNumber;
    private String from;
    private String to;

    public Flight (String flightNumber, String from, String to) {
        this.flightNumber = flightNumber.toUpperCase();
        this.from = from;
        this.to = to;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}

