package com.example;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UniversityDaoJpa implements UniversityDao {

    @PersistenceContext(unitName = "UniversityPU")
    private EntityManager em;

    @Override
    public void add(University university) {
        em.persist(university);
    }

    @Override
    public List<University> findAll() {
        // JPQL query to retrieve all University entities
        return em.createQuery("SELECT u FROM University u", University.class)
                 .getResultList();
    }
}
