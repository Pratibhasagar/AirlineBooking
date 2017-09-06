package airline.services;


import airline.models.Airplane;
import airline.models.Flight;
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
import java.util.*;

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

        final Map<TravelClassType, TravelClass> travelClassMap1 = new HashMap<TravelClassType, TravelClass>();
        travelClassMap1.put(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 8));
        travelClassMap1.put(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 35));
        travelClassMap1.put(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 195));
        final Airplane airplane1 = new Airplane("Boeing", "777-200LR(77L)");

        final Map<TravelClassType, TravelClass> travelClassMap2 = new HashMap<TravelClassType, TravelClass>();
        travelClassMap2.put(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 0));
        travelClassMap2.put(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 0));
        travelClassMap2.put(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 144));
        final Airplane airplane2 = new Airplane("Airbus", "A319 V2");

        final Map<TravelClassType, TravelClass> travelClassMap3 = new HashMap<TravelClassType, TravelClass>();
        travelClassMap3.put(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 0));
        travelClassMap3.put(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 20));
        travelClassMap3.put(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 152));
        final Airplane airplane3 = new Airplane("Airbus", "A321");

        ZonedDateTime zdtInUTC = ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC"));

        flight1 = new Flight("F1", "HYD", "BLR",
                zdtInUTC.toString(), airplane1, travelClassMap1);
        flight2 = new Flight("F2", "HYD", "PUN",
                zdtInUTC.plusDays(1).toString(), airplane2, travelClassMap2);
        flight3 = new Flight("F3", "BLR", "PUN",
                zdtInUTC.plusDays(1).toString(), airplane3, travelClassMap3);
        flight4 = new Flight("F4", "HYD", "BLR",
                zdtInUTC.plusDays(1).toString(), airplane1, travelClassMap1);

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
    public void shouldReturnAllFlightsIfCriteriaNotSpecified(){
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(flight1, flight2, flight3, flight4));
        List<Flight> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList, actualResult);
    }

    @Test
    public void shouldReturnAllFlightsStartingFromHyd(){
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(flight1, flight2, flight4));
        searchCriteria.setSource("HYD");
        List<Flight> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList, actualResult);
    }

    @Test
    public void shouldReturnAllFlightsReachingPUN(){
        expectedFlightList = new ArrayList<Flight>(Arrays.asList(flight2, flight3));
        searchCriteria.setDestination("PUN");
        List<Flight> actualResult = flightSearchService.search(searchCriteria);
        Assert.assertEquals(expectedFlightList, actualResult);
    }
}
