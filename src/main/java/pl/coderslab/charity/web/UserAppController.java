package pl.coderslab.charity.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.CurrentUser;
import pl.coderslab.charity.domain.Role;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.Iterator;
import java.util.Set;

@Controller
@RequestMapping("/app")
public class UserAppController {
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public UserAppController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository,
                             UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
    private final int checkAdminAttribute = 1;
    private final String adminRole = "ROLE_ADMIN";
    @GetMapping("/dashboard")
    public String UserDashboard(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        checkIfAdmin(currentUser, model);
        return "app/dashboard";
    }

    @GetMapping("/fundation-list")
    public String FundationList(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        checkIfAdmin(currentUser, model);
        model.addAttribute("Instutution", institutionRepository.findAll());
        return "app/fundation_list";
    }
    @GetMapping("/category-list")
    public String CategoryList(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        checkIfAdmin(currentUser, model);
        model.addAttribute("Category", categoryRepository.findAll());
        return "app/category_list";
    }
    @GetMapping("/your-donations")
    public String PersonalDonations(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        checkIfAdmin(currentUser, model);
        //Pass list of user's donation to website
        return "app/donation_list";
    }

    @GetMapping("/profile")
    public String UserProfile(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        checkIfAdmin(currentUser, model);
        model.addAttribute("User", userRepository.findById(currentUser.getUser().getId()).get());
        return "app/user_profile";
    }

    //Support method section - START

    public void checkIfAdmin(CurrentUser currentUser, Model model){
        Set<Role> roleList = currentUser.getUser().getRoles();
        Iterator<Role> it = roleList.iterator();
        while (it.hasNext()){
            if (it.next().getName().equals(adminRole)){
                model.addAttribute("CheckAdmin", checkAdminAttribute);
            }
        }
    }
}
