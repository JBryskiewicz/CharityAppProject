package pl.coderslab.charity.service.institution;

import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class InstitutionServiceImpl implements InstitutionService {
    @PersistenceContext
    private EntityManager entityManager;
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

}
