package airline.services;

import airline.models.Flight;
import airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightSearchService {

    @Autowired
    FlightRepository flightRepository;

    public List<Flight> search(String source, String destination, int requestedSeats) {
        List<Flight> availableFlights = new ArrayList<Flight>();

        if (requestedSeats == 0) {
            requestedSeats = 1;
        }
        for (Flight flight : flightRepository.getFlights()) {
            if (source.equals(flight.getSource()) && destination.equals(flight.getDestination())
                    && (requestedSeats <= flight.getAvailableSeats())) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }
}
