package airline.models;

/**
 * @author: Pratibhasagar V.
 */
public class TravelClass {
    private TravelClassType travelClassType;
    private int baseFare;
    private int totalSeats;
    private int availableSeats;

    public TravelClass(TravelClassType type, int baseFare, int totalSeats) {
        this.travelClassType = type;
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
