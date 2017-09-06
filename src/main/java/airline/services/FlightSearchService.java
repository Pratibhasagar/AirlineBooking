package airline.services;

import airline.models.Flight;
import airline.models.TravelClassType;
import airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FlightSearchService {
    List<Flight> availableFlights;
    @Autowired
    FlightRepository flightRepository;

    public List<Flight> search(SearchCriteria searchCriteria) {
        availableFlights = new ArrayList<>();

        availableFlights = flightRepository.getFlights().stream()
                .filter(sourceCriteriaMatches(searchCriteria.getSource()))
                .filter(destinationCriteriaMatches(searchCriteria.getDestination()))
                .filter(seatsCriteriaMatches(searchCriteria.getTravelClassType(), searchCriteria.getNumberOfPassengers()))
                .filter(dateOfDepartureCriteriaMatches(searchCriteria.getDate()))
                .collect(Collectors.toList());
        return availableFlights;
    }

    private static Predicate<Flight> dateOfDepartureCriteriaMatches(String dateOfDeparture) {
        if ("".equals(dateOfDeparture) || null == dateOfDeparture)
            return f -> !(ZonedDateTime.parse(f.getDateOfDeparture()).toLocalDate().isBefore(ZonedDateTime.now().toLocalDate()));
        return f -> f.travelsOnDate(dateOfDeparture);
    }

    private static Predicate<Flight> seatsCriteriaMatches(TravelClassType travelClass, int requestedSeats) {
        return f -> null == travelClass || f.getAvailableSeatsForClass(travelClass) >= requestedSeats;
    }

    private static Predicate<Flight> sourceCriteriaMatches(String source) {
        return f -> null == source || "".equals(source) || f.startsAtSource(source);
    }

    private static Predicate<Flight> destinationCriteriaMatches(String destination) {
        return f -> null == destination || "".equals(destination) || f.reachesDestination(destination);
    }
}
