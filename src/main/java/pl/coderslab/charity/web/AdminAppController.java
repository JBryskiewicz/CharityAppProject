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

@Controller
@RequestMapping("/admin")
public class AdminAppController {

    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public AdminAppController(InstitutionRepository institutionRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String AdminDashboard(){
        return "admin/admin_dashboard";
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

    @GetMapping("/user-list")
    public String UserList(Model model){
        model.addAttribute("Users", userRepository.findAll());
        //pass all users with ADMIN_ROLE, search by role id.
        return "admin/user_list";
    }

    @GetMapping("/profile")
    public String UserProfile(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        model.addAttribute("User", userRepository.findById(user.getId()).get());
        return "admin/user_profile";
    }
}
