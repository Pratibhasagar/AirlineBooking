package airline.model;

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

    public TravelClass(int baseFare, int totalSeats, int availableSeats) {
        this.baseFare = baseFare;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getBookedSeats() {
        return totalSeats - availableSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBaseFare() {
        return baseFare;
    }
}
