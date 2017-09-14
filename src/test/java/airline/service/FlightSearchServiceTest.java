package airline.service;

import airline.model.*;
import airline.repository.FlightRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Pratibhasagar V.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlightSearchService.class)
public class FlightSearchServiceTest {
    @Autowired
    private FlightSearchService flightSearchService;
    @MockBean
    private FlightRepository flightRepository;

    List<Flight> flightsInRepository;
    List<SearchResult> expectedFlightList;
    Flight flight1, flight2, flight3, flight4;
    SearchResult result1, result2, result3, result4;
    SearchCriteria searchCriteria = new SearchCriteria();

    @Before
    public void Setup() {
        searchCriteria.setNumberOfPassengers(1);

        flight1 = new FlightBuilder()
                .withFlightNumber("F1")
                .withSource("HYD")
                .withDestination("BLR")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).toString())
                .withAirplane("Boeing", "777-200LR(77L)")
                .withAirplane("Boeing", "777-200LR(77L)")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(20000, 8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(13000, 35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(6000, 195))
                .build();
        flight2 = new FlightBuilder()
                .withFlightNumber("F2")
                .withSource("HYD")
                .withDestination("PUN")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Airbus", "A319 V2")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(20000, 8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(13000, 35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(6000, 195))
                .build();
        flight3 = new FlightBuilder()
                .withFlightNumber("F3")
                .withSource("BLR")
                .withDestination("PUN")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Airbus", "A321")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(0, 0))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(10000, 20))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(5000, 152))
                .build();
        flight4 = new FlightBuilder()
                .withFlightNumber("F4")
                .withSource("HYD")
                .withDestination("BLR")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Boeing", "777-200LR(77L)")
                .withAirplane("Boeing", "777-200LR(77L)")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(20000, 8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(13000, 35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(6000, 195))
                .build();

        flightsInRepository = new ArrayList<>(Arrays.asList(flight1, flight2, flight3, flight4));
        Mockito.when(flightRepository.getFlights()).thenReturn(flightsInRepository);
    }

    @Test
    public void shouldReturnTwoFlightsBetweenHydAndBlr() {
        result1 = new SearchResult(flight1, 6000);
        result4 = new SearchResult(flight4, 6000);

        expectedFlightList = new ArrayList<>(Arrays.asList(result1, result4));
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("BLR");
        searchCriteria.setTravelClassType(TravelClassType.ECONOMY);
        List<SearchResult> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList.size(), actualResult.size());
    }

    @Test
    public void shouldReturnAllFlightsIfCriteriaNotSpecified() {
        result1 = new SearchResult(flight1, 6000);
        result2 = new SearchResult(flight2, 6000);
        result3 = new SearchResult(flight3, 5000);
        result4 = new SearchResult(flight4, 6000);
        expectedFlightList = new ArrayList<>(Arrays.asList(result1, result2, result3, result4));
        searchCriteria.setTravelClassType(TravelClassType.ECONOMY);
        List<SearchResult> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList.size(), actualResult.size());
    }

    @Test
    public void shouldReturnAllFlightsStartingFromHyd() {
        expectedFlightList = new ArrayList<>(Arrays.asList(result1, result2, result4));
        searchCriteria.setSource("HYD");
        searchCriteria.setTravelClassType(TravelClassType.ECONOMY);
        List<SearchResult> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList.size(), actualResult.size());
    }

    @Test
    public void shouldReturnAllFlightsReachingPUN() {
        expectedFlightList = new ArrayList<>(Arrays.asList(result2, result3));
        searchCriteria.setDestination("PUN");
        searchCriteria.setTravelClassType(TravelClassType.ECONOMY);
        List<SearchResult> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList.size(), actualResult.size());
    }
}
