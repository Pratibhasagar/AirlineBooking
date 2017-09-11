package airline.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author: Pratibhasagar V.
 */
public class FlightTest {

    Flight flight;

    @Before
    public void setUp() throws Exception {

        flight = new FlightBuilder().
                withFlightNumber("F1")
                .withSource("HYD")
                .withDestination("BLR")
                .withDateOfDeparture(ZonedDateTime.ofInstant(ZonedDateTime.now().toInstant(), ZoneId.of("UTC")).toString())
                .withAirplane("Boeing", "\"777-200LR(77L)")
                .withAirplane("Boeing", "777-200LR(77L)")
                .withTravelClassMap(TravelClassType.FIRST, new TravelClass(20000, 8))
                .withTravelClassMap(TravelClassType.BUSINESS, new TravelClass(13000, 35))
                .withTravelClassMap(TravelClassType.ECONOMY, new TravelClass(6000, 195))
                .build();
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
        Assert.assertEquals(8, flight.getAvailableSeatsForTravelClass(TravelClassType.FIRST));
    }

    @Test
    public void shouldReturnAvailableSeatsForBusinessClass() throws Exception {
        Assert.assertEquals(35, flight.getAvailableSeatsForTravelClass(TravelClassType.BUSINESS));
    }

    @Test
    public void shouldReturnAvailableSeatsForEconomyClass() throws Exception {
        Assert.assertEquals(195, flight.getAvailableSeatsForTravelClass(TravelClassType.ECONOMY));
    }

    @Test
    public void shouldReturnCorrectBaseFareForEconomyClass() throws Exception {
        Assert.assertEquals(6000, flight.getBasePriceForTravelClass(TravelClassType.ECONOMY), 0);
    }
}