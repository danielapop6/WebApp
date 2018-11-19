package dance.demo.Controllers;

import dance.demo.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserController userController;

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user",new User());
        return "views/registerForm";
    }
    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "views/registerForm";
        }
        if (userController.isPresent(user.getUsername())) {
            model.addAttribute("exist", true);
            return "views/registerForm";
        }
        userController.create(user);
        return  "views/success";
    }
}
