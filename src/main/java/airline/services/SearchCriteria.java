package airline.services;

public class SearchCriteria {
    private String source;
    private String destination;
    private int numberOfPassengers;
    private String date;

    public SearchCriteria() {
    }

    public SearchCriteria(String source, String destination, int numberOfPassengers, String date) {
        this.source = source;
        this.destination = destination;
        this.numberOfPassengers = numberOfPassengers;
        this.date = date;
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


}
