package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.stream.Collectors;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("bags", donationRepository.findAll().stream().map(e -> e.getQuantity().intValue())
                .collect(Collectors.summingInt(Integer::intValue)));
        model.addAttribute("donations", donationRepository.findAll().stream().count());
        model.addAttribute("institution",institutionRepository.findAll());
        return "index";
    }
}
