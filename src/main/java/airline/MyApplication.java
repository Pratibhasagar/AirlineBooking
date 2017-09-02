package airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rajashrk on 8/8/17.
 */
@Controller
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        System.out.println("There you go");
    }

    @RequestMapping("/")
    public String getSearchCriteria(Model model) {
        AirportAdmin airportAdmin = AirportAdmin.getInstance();
        model.addAttribute("airportAdmin", airportAdmin);
        return "flightSearch";
    }

    @GetMapping("/search")
    public String searchFlights(Model model,
                                @ModelAttribute AirportAdmin admin,
                                @RequestParam("fromDropDown") String source,
                                @RequestParam("toDropDown") String destination) {

        boolean searchResultsAvailable = false;
        List<Flight> searchResults = new ArrayList<Flight>();
        for (Map.Entry<String, Flight> entry : admin.getFlightManager().getFlightsList().entrySet()) {
            if (entry.getValue().getFrom().equals(source) && entry.getValue().getTo().equals(destination)) {
                searchResults.add(entry.getValue());
            }
        }
        if (searchResults.size() > 0) {
            searchResultsAvailable = true;
            model.addAttribute("searchResults", searchResults);

        }
        model.addAttribute("searchResultsAvailable", searchResultsAvailable);
        return "flightSearch";
    }


}
