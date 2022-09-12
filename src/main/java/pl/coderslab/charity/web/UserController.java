package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.service.user.UserService;
import pl.coderslab.charity.service.verification_token.VerificationTokenService;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final VerificationTokenService tokenService;

    public UserController(UserService userService, VerificationTokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

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
