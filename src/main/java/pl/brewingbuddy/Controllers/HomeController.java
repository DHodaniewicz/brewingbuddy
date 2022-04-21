package pl.brewingbuddy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/home")
    public String home(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        User user = userRepository.getById(userId);
        session.setAttribute("username", user.getUsername());
        return "home";
    }

    @GetMapping("/about")
    @ResponseBody
    public String about() { return "Here you can find some details for logged users"; }
}