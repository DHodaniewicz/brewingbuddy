package pl.brewingbuddy.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.brewingbuddy.entities.User;

@Controller
public class UserController {

//   @Autowired
//   private UserService userService;

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
