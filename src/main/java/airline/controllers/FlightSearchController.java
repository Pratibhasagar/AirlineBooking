package airline.controllers;

import airline.models.SearchResult;
import airline.models.TravelClassType;
import airline.repositories.CityRepository;
import airline.services.FlightSearchService;
import airline.services.SearchCriteria;
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
        model.addAttribute("cities", cityRepository.getCities());
        model.addAttribute("travelClassType", TravelClassType.values());
        model.addAttribute("searchCriteria", new SearchCriteria());
        return "flightSearch";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getFlights(@ModelAttribute(value = "searchCriteria") SearchCriteria searchCriteria, Model model) {
        boolean foundResults = false;
        List<SearchResult> matchedFlights = flightSearchService.search(searchCriteria);
        model.addAttribute("searchResults", matchedFlights);
        if (matchedFlights.size() > 0) {
            foundResults = true;
        }
        model.addAttribute("foundResults", foundResults);
        model.addAttribute("cities", cityRepository.getCities());
        return "flightSearch";
    }
}
