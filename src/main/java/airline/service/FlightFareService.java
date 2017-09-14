package airline.service;

import airline.model.Flight;
import airline.model.TravelClassType;
import airline.viewbean.SearchCriteria;
import airline.viewbean.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Pratibhasagar V.
 */
@Service
public class FlightFareService {

    // Each SLAB represents a seat-range with ending limit
    private final float ECONOMY_FIRST_SLAB_SEATS_LIMIT = 0.40f;
    private final float ECONOMY_SECOND_SLAB_SEATS_LIMIT = 0.90f;
    private final float ECONOMY_THIRD_SLAB_SEATS_LIMIT = 1;
    // Each SLAB has a different pricing strategy
    private final float ECONOMY_FIRST_SLAB_PRICE_MULTIPLIER = 1;
    private final float ECONOMY_SECOND_SLAB_PRICE_MULTIPLIER = 1.30f;
    private final float ECONOMY_THIRD_SLAB_PRICE_MULTIPLIER = 1.60f;

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
        return flight.getBaseFareForTravelClass(TravelClassType.BUSINESS);
    }

    private float applyPricingStrategyForFirstClass(Flight flight) {
        return flight.getBaseFareForTravelClass(TravelClassType.FIRST);
    }
}
