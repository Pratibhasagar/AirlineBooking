package airline.model;

/**
 * @author: Pratibhasagar V.
 */
public class SearchResult {
    private Flight flight;
    private float price;

    public SearchResult() {
    }

    public SearchResult(Flight flight, float price) {
        this.flight = flight;
        this.price = price;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Flight getFlight() {
        return flight;
    }

    public float getPrice() {
        return price;
    }
}
