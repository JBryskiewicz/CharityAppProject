package pl.coderslab.charity.service.category;

import pl.coderslab.charity.repository.CategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CategoryServiceImpl implements CategoryService{

    @PersistenceContext
    private EntityManager entityManager;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
