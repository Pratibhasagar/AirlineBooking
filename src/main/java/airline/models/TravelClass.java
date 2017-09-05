package airline.models;

public class TravelClass {
    private TravelClassType type;
    private int totalSeats;
    private int availableSeats;

    public TravelClass(TravelClassType type, int totalSeats) {
        this.type = type;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public TravelClassType getType() {
        return type;
    }

    public void setType(TravelClassType type) {
        this.type = type;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
