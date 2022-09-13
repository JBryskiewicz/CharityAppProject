package pl.coderslab.charity.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.service.user.UserService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/signup-result")
    public String registerResult(@Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/signup-confirm/{token}")
    @ResponseBody
    public String confirm(@PathVariable String token){
        return userService.confirmToken(token);
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
