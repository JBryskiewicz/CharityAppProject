package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.domain.Category;
import pl.coderslab.charity.domain.Donation;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.donation.DonationService;

import javax.validation.Valid;
import java.util.List;


@Controller
public class DonationController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final DonationService donationService;

    public DonationController(InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository, DonationService donationService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.donationService = donationService;
    }

    @GetMapping("/form-donation")
    public String DonationForm(Model model){
        model.addAttribute("Institution", institutionRepository.findAll());
        model.addAttribute("Category", categoryRepository.findAll());
        return "/donation";
    }

    @PostMapping("/form-donation-result")
    public String DonationFormResult(@Valid Donation donation, BindingResult result, Model model,
                                     @RequestParam List<Long> category){
        if(result.hasErrors()){
            model.addAttribute("Institution", institutionRepository.findAll());
            model.addAttribute("Category", categoryRepository.findAll());
            return "/form-donation";
        }
        List<Category> categoryList = categoryRepository.findAllById(category);
        donation.setCategories(categoryList);
        donationService.saveDonation(donation);
        return "/donation-result";
    }
}
