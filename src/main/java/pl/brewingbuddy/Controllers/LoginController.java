package pl.brewingbuddy.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.brewingbuddy.entities.User;


public class LoginController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String registerNewUser(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }
}
