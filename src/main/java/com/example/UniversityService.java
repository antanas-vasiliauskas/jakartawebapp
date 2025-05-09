package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UniversityService {
    @Inject
    private UniversityDao universityDao;
    
    public List<University> findAll() {
        return universityDao.findAll();
    }
    
    public University findById(int id) {
        return universityDao.find(id);
    }
    
    public void create(University university) {
        universityDao.create(university);
    }
    
    public University update(University university) {
        return universityDao.update(university);
    }
    
    public void delete(int id) {
        universityDao.delete(id);
    }
}
