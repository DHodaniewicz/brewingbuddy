package pl.brewingbuddy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.brewingbuddy.entities.User;
import pl.brewingbuddy.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home(HttpSession session) {
        session.setAttribute("userId", 1);
        User user = userRepository.getById(1L);
        session.setAttribute("username", user.getUsername());
        return "home";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about() { return "Here you can find some details for logged users"; }
}