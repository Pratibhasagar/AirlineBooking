package airline.models;

import java.util.Map;

public class Airplane {
    private String manufacturer;
    private String modelNumber;
    private Map<TravelClassType, TravelClass> travelClassMap;

    public Airplane(String manufacturer, String modelNumber, Map<TravelClassType, TravelClass> travelClassMap) {
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
        this.travelClassMap = travelClassMap;
    }

    public int getAvailableSeatsForClass(TravelClassType travelClassType) {
        return travelClassMap.get(travelClassType).getAvailableSeats();
    }

    public String getAirplaneInfo() {
        return (this.manufacturer + " " + this.modelNumber);
    }
}
