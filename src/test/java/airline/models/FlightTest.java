package airline.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class FlightTest {

    Flight flight;

    @Before
    public void setUp() throws Exception {

        ZonedDateTime zdtInUTC = ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC"));
        final Map<TravelClassType, TravelClass> travelClassMap1 = new HashMap<TravelClassType, TravelClass>();
        travelClassMap1.put(TravelClassType.FIRST, new TravelClass(TravelClassType.FIRST, 8));
        travelClassMap1.put(TravelClassType.BUSINESS, new TravelClass(TravelClassType.BUSINESS, 35));
        travelClassMap1.put(TravelClassType.ECONOMY, new TravelClass(TravelClassType.ECONOMY, 195));
        final Airplane airplane1 = new Airplane("Boeing", "777-200LR(77L)");

        flight = new Flight("F1", "HYD", "BLR",
                zdtInUTC.toString(), airplane1, travelClassMap1);
    }

    @Test
    public void shouldReturnTrueIfFlightStartsFromHYD() {
        Assert.assertTrue(flight.startsAtSource("HYD"));
    }

    @Test
    public void shouldReturnFalseIfFlightDoesNotStartFromBLR() {
        Assert.assertFalse(flight.startsAtSource("BLR"));
    }

    @Test
    public void shouldReturnTrueIfFlightReachesBLR() {
        Assert.assertTrue(flight.reachesDestination("BLR"));

    }

    @Test
    public void shouldReturnFalseIfFlightDoesNotReachPUN() {
        Assert.assertFalse(flight.reachesDestination("PUN"));
    }

    @Test
    public void shouldReturnTrueIfFlightTravelsOnDate() {
        Assert.assertTrue(flight.travelsOnDate("2017-09-06"));
    }

    @Test
    public void shouldReturnFalseIfFlightDoesNotTravelOnDate() {
        Assert.assertFalse(flight.travelsOnDate("2017-09-20"));
    }

    @Test
    public void shouldReturnAvailableSeatsForFirstClass() throws Exception {
        Assert.assertEquals(8,flight.getAvailableSeatsForClass(TravelClassType.FIRST));
    }

    @Test
    public void shouldReturnAvailableSeatsForBusinessClass() throws Exception {
        Assert.assertEquals(35,flight.getAvailableSeatsForClass(TravelClassType.BUSINESS));
    }

    @Test
    public void shouldReturnAvailableSeatsForEconomyClass() throws Exception {
        Assert.assertEquals(195,flight.getAvailableSeatsForClass(TravelClassType.ECONOMY));
    }
}