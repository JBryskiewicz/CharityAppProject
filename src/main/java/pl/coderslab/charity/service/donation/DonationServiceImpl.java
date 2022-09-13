package pl.coderslab.charity.service.donation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
@Transactional
@AllArgsConstructor
public class DonationServiceImpl implements DonationService{
    private final DonationRepository donationRepository;

    @Override
    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }
}
