package airline.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: Pratibhasagar V.
 */
public class FlightRepositoryTest {
    private FlightRepository flightRepository;

    @Before
    public void setUp() throws Exception {
        flightRepository = new FlightRepository();
    }

    @Test
    public void mustHaveDefaultFlights() {
        Assert.assertEquals(4, flightRepository.getFlights().size());
    }

}
