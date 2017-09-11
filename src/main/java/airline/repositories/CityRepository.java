package airline.repositories;
import airline.models.City;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Pratibhasagar V.
 */

@Repository
public class CityRepository {
    private static List<City> cities;

    public CityRepository() {
        cities = new ArrayList<>();
        createDefaultCities();
    }

    private void createDefaultCities() {
        cities.add(new City("HYD", "Hyderabad"));
        cities.add(new City("BLR", "Bengaluru"));
        cities.add(new City("PUN", "Pune"));
        cities.add(new City("DLH", "Delhi"));
    }

    public List<City> getCities() {
        return cities;
    }
}
