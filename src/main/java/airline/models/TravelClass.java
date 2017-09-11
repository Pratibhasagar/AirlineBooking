package airline.models;

/**
 * @author: Pratibhasagar V.
 */
public class TravelClass {
    private int baseFare;
    private int totalSeats;
    private int availableSeats;

    public TravelClass(int baseFare, int totalSeats) {
        this.baseFare = baseFare;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getBaseFare() {
        return baseFare;
    }
}
