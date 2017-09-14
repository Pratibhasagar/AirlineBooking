package airline.service;

import airline.model.Flight;
import airline.model.TravelClassType;
import airline.viewbean.SearchCriteria;
import airline.viewbean.SearchResult;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import static java.time.DayOfWeek.*;

/**
 * @author: Pratibhasagar V.
 */
@Service
public class FlightFareService {

    // Each SLAB represents a seat-range with ending limit
    private static final float ECONOMY_FIRST_SLAB_SEATS_LIMIT = 0.40f;
    private static final float ECONOMY_SECOND_SLAB_SEATS_LIMIT = 0.90f;
    private static final float ECONOMY_THIRD_SLAB_SEATS_LIMIT = 1;
    // Each SLAB has a different pricing strategy
    private static final float ECONOMY_FIRST_SLAB_PRICE_MULTIPLIER = 1;
    private static final float ECONOMY_SECOND_SLAB_PRICE_MULTIPLIER = 1.30f;
    private static final float ECONOMY_THIRD_SLAB_PRICE_MULTIPLIER = 1.60f;
    private static final float BUSINESS_FIRST_SLAB_PRICE_MULTIPLIER = 1;
    private static final float BUSINESS_SECOND_SLAB_PRICE_MULTIPLIER = 1.40f;

    public List<SearchResult> getNetPriceForAllFlights(List<Flight> flights, SearchCriteria searchCriteria) {
        List<SearchResult> searchResultList = new ArrayList<>();
        float netPrice = 0;

        for (Flight flight : flights) {
            netPrice = getNetPriceForFlight(flight, searchCriteria);
            SearchResult searchResult = new SearchResult(flight, netPrice);
            searchResultList.add(searchResult);
        }
        return searchResultList;
    }

    private float getNetPriceForFlight(Flight flight, SearchCriteria searchCriteria) {
        float pricePerSeat = 0;
        switch (searchCriteria.getTravelClassType()) {
            case ECONOMY:
                pricePerSeat = applyPricingStrategyForEconomyClass(flight);
                break;
            case BUSINESS:
                pricePerSeat = applyPricingStrategyForBusinessClass(flight);
                break;
            case FIRST:
                pricePerSeat = applyPricingStrategyForFirstClass(flight);
                break;
        }
        return pricePerSeat * searchCriteria.getNumberOfPassengers();
    }

    private float applyPricingStrategyForEconomyClass(Flight flight) {
        int bookedSeats = flight.getBookedSeatsForTravelClass(TravelClassType.ECONOMY);
        int totalSeats = flight.getTotalSeatsForTravelClass(TravelClassType.ECONOMY);
        float priceMultiplier = 0;

        if (bookedSeats < ECONOMY_FIRST_SLAB_SEATS_LIMIT * totalSeats) {
            priceMultiplier = ECONOMY_FIRST_SLAB_PRICE_MULTIPLIER;
        } else if (bookedSeats < ECONOMY_SECOND_SLAB_SEATS_LIMIT * totalSeats) {
            priceMultiplier = ECONOMY_SECOND_SLAB_PRICE_MULTIPLIER;
        } else if (bookedSeats < ECONOMY_THIRD_SLAB_SEATS_LIMIT * totalSeats) {
            priceMultiplier = ECONOMY_THIRD_SLAB_PRICE_MULTIPLIER;
        }
        return priceMultiplier * flight.getBaseFareForTravelClass(TravelClassType.ECONOMY);
    }

    private float applyPricingStrategyForBusinessClass(Flight flight) {
        DayOfWeek dayOfTravel = ZonedDateTime.parse(flight.getDateOfDeparture()).toLocalDate().getDayOfWeek();
        float priceMultiplier = 0;
        switch (dayOfTravel) {
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case SATURDAY:
                priceMultiplier = BUSINESS_FIRST_SLAB_PRICE_MULTIPLIER;
                break;
            case MONDAY:
            case FRIDAY:
            case SUNDAY:
                priceMultiplier = BUSINESS_SECOND_SLAB_PRICE_MULTIPLIER;
                break;
        }
        return priceMultiplier * flight.getBaseFareForTravelClass(TravelClassType.BUSINESS);
    }

    private float applyPricingStrategyForFirstClass(Flight flight) {
        return flight.getBaseFareForTravelClass(TravelClassType.FIRST);
    }
}
