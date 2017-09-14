package airline.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: Pratibhasagar V.
 */
public class CityRepositoryTest {
    private CityRepository cityRepository;

    @Before
    public void setUp() throws Exception {
        cityRepository = new CityRepository();
    }

    @Test
    public void mustHaveDefaultCities() {
        Assert.assertEquals(4, cityRepository.getCities().size());
    }

}
