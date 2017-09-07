package airline.models;

/**
 * @author: Pratibhasagar V.
 */
public class City {
    public String id;
    public String name;

    public City(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
