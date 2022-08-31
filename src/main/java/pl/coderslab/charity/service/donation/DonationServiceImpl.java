package pl.coderslab.charity.service.donation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.Donation;
import pl.coderslab.charity.repository.DonationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
@Transactional
public class DonationServiceImpl implements DonationService{
    @PersistenceContext
    private EntityManager entityManager;
    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }
}
