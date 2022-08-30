package pl.coderslab.charity.service.donation;

import pl.coderslab.charity.repository.DonationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DonationServiceImpl implements DonationService{
    @PersistenceContext
    private EntityManager entityManager;
    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

}
