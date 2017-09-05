package airline.services;

import airline.models.TravelClassType;
import airline.models.Flight;
import airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightSearchService {
    List<Flight> availableFlights;
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> search(SearchCriteria searchCriteria) {
        availableFlights = new ArrayList<Flight>();

        for (Flight flight : flightRepository.getFlights()) {
            if (sourceCriteriaMatches(searchCriteria.getSource(), flight) &&
                    destinationCriteriaMatches(searchCriteria.getDestination(), flight) &&
                    seatsCriteriaMatches(searchCriteria.getTravelClassType(), searchCriteria.getNumberOfPassengers(), flight) &&
                    dateOfDepartureCriteriaMatches(searchCriteria.getDate(), flight)) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }

    private boolean dateOfDepartureCriteriaMatches(String dateOfDeparture, Flight flight) {
        boolean criteriaMatch = false;
        if (!dateOfDeparture.isEmpty() &&
                dateOfDeparture.equals(ZonedDateTime.parse(flight.getDateOfDeparture()).toLocalDate().toString())) {
            criteriaMatch = true;
        } else if (dateOfDeparture.isEmpty()) {
            criteriaMatch = true;
        }
        return criteriaMatch;
    }

    private boolean seatsCriteriaMatches(TravelClassType travelClass, int requestedSeats, Flight flight) {
        return (requestedSeats <= flight.getAirplane().getAvailableSeatsForClass(travelClass));
    }

    private boolean destinationCriteriaMatches(String destination, Flight flight) {
        return destination.equals(flight.getDestination());
    }

    private boolean sourceCriteriaMatches(String source, Flight flight) {
        return source.equals(flight.getSource());
    }
}
