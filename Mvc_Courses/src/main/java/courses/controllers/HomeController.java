package courses.controllers;

import courses.models.actors.Guest;
import courses.services.actors.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private GuestService guestService;

    @RequestMapping("/")
    public String index(Guest guest) {

        return "index";
    }
}
