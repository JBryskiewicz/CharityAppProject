package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;


@Controller
@RequestMapping("/donation")
public class DonationController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;

    public DonationController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/form")
    public String DonationForm(Model model){
        model.addAttribute("Institution", institutionRepository.findAll());
        model.addAttribute("Category", categoryRepository.findAll());
        institutionRepository.findAll().stream().forEach(e -> System.out.println(e.getName()));
        categoryRepository.findAll().stream().forEach(e -> System.out.println(e.getName()));
        return "/donation";
    }

    @PostMapping("/form-result")
    public String DonationFormResult(){

        return "/donation-result";
    }
}
