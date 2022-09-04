package pl.coderslab.charity.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.CurrentUser;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.donation.DonationService;
import pl.coderslab.charity.service.user.UserService;

@Controller
@RequestMapping("/app")
public class UserAppController {
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final DonationService donationService;
    private final UserService userService;
    private final UserRepository userRepository;

    public UserAppController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository,
                             DonationService donationService, UserService userService, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationService = donationService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String UserDashboard(){
        return "app/dashboard";
    }

    @GetMapping("/fundation-list")
    public String FundationList(Model model){
        model.addAttribute("Instutution", institutionRepository.findAll());
        return "app/fundation_list";
    }
    @GetMapping("/category-list")
    public String CategoryList(Model model){
        model.addAttribute("Category", categoryRepository.findAll());
        return "app/category_list";
    }
    @GetMapping("/your-donations")
    public String PersonalDonations(Model model){
        //Pass list of user's donation to website
        return "app/donation_list";
    }

    @GetMapping("/profile")
    public String UserProfile(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        model.addAttribute("User", userRepository.findById(user.getId()).get());
        return "app/user_profile";
    }
}
