package com.example;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class StudentService {
    @Inject
    private StudentDao studentDao;
    
    public List<Student> findAll() {
        return studentDao.findAll();
    }
    
    public Student findById(int id) {
        Student student = studentDao.find(id);
        student.getCourses().size(); // Triggers lazy-loading
        return student;
    }
    
    public void create(Student student) {
        studentDao.create(student);
    }
    
    public Student update(Student student) {
        return studentDao.update(student);
    }
    
    public void delete(int id) {
        studentDao.delete(id);
    }
    
    public List<Student> findByUniversityId(int universityId) {
        return studentDao.findByUniversity(universityId);
    }
}
