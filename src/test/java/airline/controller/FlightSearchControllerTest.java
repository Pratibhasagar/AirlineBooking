package airline.controller;

import airline.AirlineBookingApp;
import airline.model.City;
import airline.repository.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author: Pratibhasagar V.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AirlineBookingApp.class})
@WebAppConfiguration
public class FlightSearchControllerTest {

    MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;

    @MockBean
    private CityRepository cityRepository;

    List<City> cities = new ArrayList<>();

    @Before
    public void Setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

        cities.add(new City("HYD", "Hyderabad"));
        cities.add(new City("BLR", "Bengaluru"));
        cities.add(new City("PUN", "Pune"));
        cities.add(new City("DLH", "Delhi"));
        Mockito.when(cityRepository.getCities()).thenReturn(cities);
    }

    @Test
    public void getCities_ShouldAddCitiesToTheModelAndRenderCityListView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("flightSearch"))
                .andExpect(model().attribute("cities", hasSize(4)))
                .andExpect(model().attribute("cities", hasItem(
                        allOf(
                                hasProperty("id", is("HYD")),
                                hasProperty("name", is("Hyderabad"))
                        )
                )))
                .andExpect(model().attribute("cities", hasItem(
                        allOf(
                                hasProperty("id", is("BLR")),
                                hasProperty("name", is("Bengaluru"))
                        )
                )))
                .andExpect(model().attribute("cities", hasItem(
                        allOf(
                                hasProperty("id", is("PUN")),
                                hasProperty("name", is("Pune"))
                        )
                )))
                .andExpect(model().attribute("cities", hasItem(
                        allOf(
                                hasProperty("id", is("DLH")),
                                hasProperty("name", is("Delhi"))
                        )
                )));
    }

    @Test
    public void getFlights() throws Exception {
    }

}