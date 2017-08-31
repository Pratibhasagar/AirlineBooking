package airline;

import java.util.ArrayList;
import java.util.List;

public class LocationManager {
    public static final LocationManager instance = new LocationManager();
    public List<String> sourceList;
    public List<String> destinationList;

    private LocationManager(){
        sourceList = new ArrayList<String>();
        destinationList = new ArrayList<String>();
    }
    public static LocationManager getInstance() {
        return instance;
    }

    public List<String> getDestinationList() {
        return destinationList;
    }

    public List<String> getSourceList() {
        return sourceList;
    }

    private void addSource(String source) {
        sourceList.add(source);
    }

    private void addDestination(String destination) {
        destinationList.add(destination);
    }

    public void updateLocationsForNewFlight(Flight flight) {
        addSource(flight.getFrom());
        addDestination(flight.getTo());
    }
}
