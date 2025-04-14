package com.example;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StudentBean implements Serializable  {
    @Inject
    private StudentService studentService;
    @Inject
    private UniversityService universityService;
    
    private List<Student> students;
    private Student student;
    private Integer id;           // student id for view param
    private Integer selectedUniversityId;  // for <selectOneMenu>
    
    public List<Student> getStudents() {
        if (students == null) {
            students = studentService.findAll();
        }
        return students;
    }
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Student getStudent() {
        if (student == null) {
            if (id != null && id != 0) {
                student = studentService.findById(id);
            } else {
                student = new Student();
            }
        }
        return student;
    }
    
    public Integer getSelectedUniversityId() {
        // If editing, initialize selectedUniversityId with current value
        if (selectedUniversityId == null && getStudent().getUniversity() != null) {
            selectedUniversityId = student.getUniversity().getId();
        }
        return selectedUniversityId;
    }
    public void setSelectedUniversityId(Integer selectedUniversityId) {
        this.selectedUniversityId = selectedUniversityId;
    }
    
    public List<University> getUniversities() {
        // Provide list of all universities for the dropdown
        return universityService.findAll();
    }
    
    public String saveStudent() {
        // Set the selected University object before saving
        if (selectedUniversityId != null) {
            University uni = universityService.findById(selectedUniversityId);
            student.setUniversity(uni);
        }
        if (student.getId() == 0) {
            studentService.create(student);
        } else {
            studentService.update(student);
        }
        return "students?faces-redirect=true";
    }
    
    public String deleteStudent() {
        if (student != null) {
            studentService.delete(student.getId());
        }
        return "students?faces-redirect=true";
    }
    
    public String reloadStudent() {
        if (id != null && id != 0) {
            student = studentService.findById(id); // re-fetch fresh copy
        }
        return null; // stay on same page
    }

}
