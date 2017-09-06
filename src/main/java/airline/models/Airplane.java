package airline.models;

public class Airplane {
    private String manufacturer;
    private String modelNumber;

    public Airplane(String manufacturer, String modelNumber) {
        this.manufacturer = manufacturer;
        this.modelNumber = modelNumber;
    }

    public String getAirplaneInfo() {
        return (this.manufacturer + " " + this.modelNumber);
    }
}
