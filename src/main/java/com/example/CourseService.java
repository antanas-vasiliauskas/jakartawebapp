package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CourseService {
    @Inject
    private CourseDao courseDao;
    
    public List<Course> findAll() {
        return courseDao.findAll();
    }
    
    public Course findById(int id) {
        return courseDao.find(id);
    }
    
    public void create(Course course) {
        courseDao.create(course);
    }
    
    public Course update(Course course) {
        return courseDao.update(course);
    }
    
    public void delete(int id) {
        courseDao.delete(id);
    }
}
