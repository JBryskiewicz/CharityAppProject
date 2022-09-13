package pl.coderslab.charity.web;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.domain.CurrentUser;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.domain.Role;
import pl.coderslab.charity.domain.User;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.institution.InstitutionService;
import pl.coderslab.charity.service.user.UserService;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminAppController {
    private static final long adminId = 1L;
    private static final long userId = 2L;
    private static final long bannedId = 3L;

    private final InstitutionRepository institutionRepository;
    private final InstitutionService institutionService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;



    @GetMapping("/dashboard")
    public String AdminDashboard(){
        return "admin/admin_dashboard";
    }

    // Fundation section - START
    @GetMapping("/fundation-list")
    public String FundationList(Model model){
        model.addAttribute("institution",institutionRepository.findAll());
        return "admin/fundation_list";
    }
    @GetMapping("/fundation-creator")
    public String FundationCreator(Model model){
        model.addAttribute("institution", new Institution());
        return "admin/fundation_creator";
    }
    @PostMapping("/fundation-creator-result")
    public String FundationCreatorResult(@Valid Institution institution, BindingResult result){
        if(result.hasErrors()){
            return "admin/fundation_creator";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/fundation-list";
    }
    @GetMapping("/fundation-details/{id}")
    public String FundationDetails(Model model, @PathVariable long id){
        model.addAttribute("institution", institutionRepository.findById(id).get());
        return "admin/fundation_details/"+id;
    }

    @GetMapping("/fundation-edit/{id}")
    public String FundationEditor(Model model, @PathVariable long id){
        model.addAttribute("institution", institutionRepository.findById(id).get());
        return "admin/fundation_editor";
    }
    @PostMapping("/fundation-edit-result")
    public String FundationEditorResult(@Valid Institution institution, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("institution", institutionRepository.findById(institution.getId()).get());
            return "admin/fundation_editor";
        }
        institutionService.editInstitution(institution);
        return "redirect:/admin/fundation-list";
    }
    @GetMapping("/fundation-delete/{id}")
    public String FundationDel(Model model, @PathVariable long id){
        model.addAttribute("institution", institutionRepository.findById(id).get());
        return "admin/fundation_del_confirm";
    }
    @GetMapping("/fundation-del-result/{id}")
    public String FundationDelResult(@PathVariable long id){
        institutionRepository.delete(institutionRepository.findById(id).get());
        return "redirect:/admin/fundation-list";
    }
    // Fundation section - END

    // User management section - START
    @GetMapping("/users")
    public String UserList(Model model){
        model.addAttribute("admins", userRepository.findAllByRole(setAdminRole(adminId)));
        model.addAttribute("users", userRepository.findAllByRole(setUserRole(userId)));
        model.addAttribute("bannedUsers", userRepository.findAllByRole(setBannedRole(bannedId)));
        return "admin/users_list";
    }
    @GetMapping("/user-admin/{id}")
    public String UserToAdmin(@PathVariable long id){
        User user = userRepository.findById(id).get();
        Set<Role> roleList = user.getRoles().stream().collect(Collectors.toSet());
        roleList.add(roleRepository.findById(adminId).get());
        user.setRoles(roleList);
        userService.softEditUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin-user/{id}")
    public String AdminToUser(@PathVariable long id){
        clearAndSetRole(id, userId);
        return "redirect:/admin/users";
    }
    @GetMapping("/ban-user/{id}")
    public String BanUser(@PathVariable long id){
        clearAndSetRole(id, bannedId);
        return "redirect:/admin/users";
    }
    @GetMapping("/unban-user/{id}")
    public String UnbanUser(@PathVariable long id){
        clearAndSetRole(id, userId);
        return "redirect:/admin/users";
    }
    @GetMapping("/user-edit/{id}")
    public String EditUser(Model model, @PathVariable long id){
        model.addAttribute("user", userRepository.findById(id).get());
        return "admin/user_edit";
    }
    @PostMapping("/user-edit-result")
    public String EditUserResult(@Valid User user, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("user", userRepository.findById(user.getId()).get());
        }
        userService.editUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/user-del/{id}")
    public String UserDel(Model model, @PathVariable long id){
        model.addAttribute("user", userRepository.findById(id).get());
        return "admin/user_del_confirm";
    }
    @GetMapping("/user-del-result/{id}")
    public String UserDelResult(@PathVariable long id){
        userRepository.delete(userRepository.findById(id).get());
        return "redirect:/admin/users";
    }
    // User management section - END

    @GetMapping("/category-list")
    public String CategoryList(Model model){
        model.addAttribute("category", categoryRepository.findAll());
        return "admin/category_list";
    }

    @GetMapping("/profile")
    public String UserProfile(Model model,@AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        model.addAttribute("User", userRepository.findById(user.getId()).get());
        return "admin/user_profile";
    }

    // Support methods section - START
    public Role setAdminRole(long idForRole){
        Role roleAdmin = new Role();
        roleAdmin.setId(idForRole);
        return roleAdmin;
    }
    public Role setUserRole(long idForRole){
        Role roleUser = new Role();
        roleUser.setId(idForRole);
        return roleUser;
    }
    public Role setBannedRole(long idForRole){
        Role roleBanned = new Role();
        roleBanned.setId(idForRole);
        return roleBanned;
    }
    public void clearAndSetRole(long idForUser, long idForRole){
        User user = userRepository.findById(idForUser).get();
        Set<Role> roleList = user.getRoles().stream().collect(Collectors.toSet());
        roleList.clear();
        roleList.add(roleRepository.findById(idForRole).get());
        user.setRoles(roleList);
        userService.softEditUser(user);
    }
}
