package pl.brewingbuddy.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.entities.BeerStyle;
import pl.brewingbuddy.entities.User;
import pl.brewingbuddy.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //   @Autowired
//   private UserService userService;

    @GetMapping("/")
    public String viewLoginUser() {
        return "userSelect";
    }

    @PostMapping("/")
    public String loginUser(@RequestParam Long userId, HttpSession session) {
        session.setAttribute("userId", userId);
        return "redirect:/home";
    }

    @ModelAttribute("availableUsers")
    List<User> availableUsers() {
        return userRepository.findAll();
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "/WEB-INF/views/login.jsp";
    }

    @GetMapping("/register")
    public String registerNewUserView(Model model) {
        model.addAttribute("user", new User());
        return "/WEB-INF/views/register.jsp";
    }



 //   @PostMapping("/register")
 //   public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model) {
 //       if (bindingResult.hasErrors()) {
 //           return "register";
 //       }
 //       model.addAttribute("user", new User());
 //       userService.saveUser(user);
 //       return "redirect:/";
 //   }

 //   @GetMapping("/create-user")
 //   @ResponseBody
 //   public String createUser() {
 //       User user = new User();
 //       user.setUsername("admin1");
 //       user.setPassword("admin1");
 //       user.setPassword("admin@admin.wp.pl");
 //       userService.saveUser(user);
 //       return "admin";
 //   }

}
