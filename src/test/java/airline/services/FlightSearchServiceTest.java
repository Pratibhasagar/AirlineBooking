package airline.services;

import airline.repositories.FlightRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchServiceTest {
    @InjectMocks
    private FlightSearchService flightSearchService;;
    @Mock
    private FlightRepository flightRepository;

    @Test
    public void hasTwoFlightBetweenHydAndBlr() {
        Assert.assertEquals(2,
                flightSearchService.search("HYD", "BLR", 2, "").size());
    }

}
