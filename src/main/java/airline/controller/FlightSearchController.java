package airline.controller;

import airline.model.TravelClassType;
import airline.repository.CityRepository;
import airline.service.FlightSearchService;
import airline.viewbean.SearchCriteria;
import airline.viewbean.SearchResult;
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
        boolean foundResults = true;
        List<SearchResult> matchedFlights = flightSearchService.search(searchCriteria);
        model.addAttribute("searchResults", matchedFlights);
        if (matchedFlights.isEmpty()) {
            foundResults = false;
        }
        model.addAttribute("foundResults", foundResults);
        model.addAttribute("cities", cityRepository.getCities());
        return "flightSearch";
    }
}
