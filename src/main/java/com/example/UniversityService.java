package com.example;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;

@Stateless
public class UniversityService {

    @Inject
    private UniversityDao universityDao;
    
    public void addUniversity(String name) {
        // Create a new University entity and delegate to DAO for persistence
        University uni = new University(name);
        universityDao.add(uni);
    }
    
    public List<University> getAllUniversities() {
        return universityDao.findAll();
    }
}
