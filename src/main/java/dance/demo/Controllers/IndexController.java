package dance.demo.Controllers;


import dance.demo.Entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/home")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(User user) {
        return "views/loginForm";
    }
}
