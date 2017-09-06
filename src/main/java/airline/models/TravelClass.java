package airline.models;

public class TravelClass {
    private TravelClassType travelClassType;
    private int totalSeats;
    private int availableSeats;

    public TravelClass(TravelClassType type, int totalSeats) {
        this.travelClassType = type;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}
