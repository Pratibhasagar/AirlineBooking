package airline.controllers;

import airline.models.City;
import airline.models.Flight;
import airline.services.SearchCriteria;
import airline.repositories.CityRepository;
import airline.services.FlightSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by rajashrk on 8/8/17.
 */

@Controller
public class FlightSearchController {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private FlightSearchService flightSearchService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCities(Model model) {
        List<City> cities = cityRepository.getCities();
        model.addAttribute("cities", cities);
        model.addAttribute("searchCriteria", new SearchCriteria());
        return "flightSearch";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getFlights(@ModelAttribute(value = "searchCriteria") SearchCriteria searchCriteria, Model model) {
        boolean foundResults = false;
        List<Flight> matchedFlights = flightSearchService.search(searchCriteria.getSource(),
                searchCriteria.getDestination(), searchCriteria.getNumberOfPassengers(),searchCriteria.getDate());
        model.addAttribute("searchResults", matchedFlights);
        if (matchedFlights.size() > 0) {
            foundResults = true;
        }
        model.addAttribute("foundResults", foundResults);

        List<City> cities = cityRepository.getCities();
        model.addAttribute("cities", cities);
        return "flightSearch";
    }
}
