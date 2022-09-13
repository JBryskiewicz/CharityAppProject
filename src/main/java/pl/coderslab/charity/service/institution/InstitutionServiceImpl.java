package pl.coderslab.charity.service.institution;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.domain.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Service
@Transactional
@AllArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void editInstitution(Institution institution) {
        entityManager.merge(institution);
    }
}
