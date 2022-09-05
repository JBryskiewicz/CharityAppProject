package pl.coderslab.charity.service.institution;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
@Transactional
public class InstitutionServiceImpl implements InstitutionService {
    @PersistenceContext
    private EntityManager entityManager;
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public void editInstitution(Institution institution) {
        entityManager.merge(institution);
    }
}
