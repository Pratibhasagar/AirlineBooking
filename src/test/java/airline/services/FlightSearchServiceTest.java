package airline.services;

import airline.models.Flight;
import airline.models.FlightBuilder;
import airline.models.TravelClass;
import airline.models.TravelClassType;
import airline.repositories.FlightRepository;
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

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlightSearchService.class)
public class FlightSearchServiceTest {
    @Autowired
    private FlightSearchService flightSearchService;
    @MockBean
    private FlightRepository flightRepository;

    List<Flight> flightsInRepository;
    List<Flight> expectedFlightList;
    Flight flight1, flight2, flight3, flight4;
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
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 195))
                .build();
        flight2 = new FlightBuilder()
                .withFlightNumber("F2")
                .withSource("HYD")
                .withDestination("PUN")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Airbus", "A319 V2")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 0))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 0))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 144))
                .build();
        flight3 = new FlightBuilder()
                .withFlightNumber("F3")
                .withSource("BLR")
                .withDestination("PUN")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Airbus", "A321")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 0))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 20))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 152))
                .build();
        flight4 = new FlightBuilder()
                .withFlightNumber("F4")
                .withSource("HYD")
                .withDestination("BLR")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).plusDays(1).toString())
                .withAirplane("Boeing", "777-200LR(77L)")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 195))
                .build();

        flightsInRepository = new ArrayList<Flight>(Arrays.asList(flight1, flight2, flight3, flight4));
        Mockito.when(flightRepository.getFlights()).thenReturn(flightsInRepository);
    }

    @Test
    public void searchFlightsBetweenHydAndBlr() {
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(flight1, flight4));
        searchCriteria.setSource("HYD");
        searchCriteria.setDestination("BLR");
        List<Flight> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList, actualResult);
    }

    @Test
    public void shouldReturnAllFlightsIfCriteriaNotSpecified() {
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(flight1, flight2, flight3, flight4));
        List<Flight> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList, actualResult);
    }

    @Test
    public void shouldReturnAllFlightsStartingFromHyd() {
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(flight1, flight2, flight4));
        searchCriteria.setSource("HYD");
        List<Flight> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList, actualResult);
    }

    @Test
    public void shouldReturnAllFlightsReachingPUN() {
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(flight2, flight3));
        searchCriteria.setDestination("PUN");
        List<Flight> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList, actualResult);
    }
}
