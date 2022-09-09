package pl.coderslab.charity.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.email_sender.EmailSenderService;

import java.util.stream.Collectors;


@Controller
public class HomeController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final EmailSenderService senderService;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, EmailSenderService senderService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.senderService = senderService;
    }

    private final String emailReceiver = "[email_placeholder]";

    @RequestMapping("/")
    public String homeAction(Model model){
        passAttributesToHomepage(model);
        return "index";
    }

    @GetMapping("/contact-us")
    public String emailMessage(@RequestParam String name, @RequestParam String surname, @RequestParam String message){
        senderService.sendEmail(emailReceiver, "Wiadomość od"+name+" "+surname, message);
        return "redirect:/";
    }

    //Support Method section - START
    public void passAttributesToHomepage(Model model){
        model.addAttribute("bags", donationRepository.findAll().stream().map(e -> e.getQuantity().intValue())
                .collect(Collectors.summingInt(Integer::intValue)));
        model.addAttribute("donations", donationRepository.findAll().stream().count());
        model.addAttribute("institution",institutionRepository.findAll());
    }
}
