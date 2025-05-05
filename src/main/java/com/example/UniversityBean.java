package com.example;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UniversityBean implements Serializable {
    @Inject
    private UniversityService universityService;

    private List<University> universities;
    private University university;
    private Integer id;  // for JSF view param (university id)

    public List<University> getUniversities() {
        if (universities == null) {
            universities = universityService.findAll();
        }
        return universities;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }


    @Transactional
    public University getUniversity() {
        if (university == null) {
            if (id != null && id != 0) {
                university = universityService.findById(id);
            } else {
                university = new University();
            }
        }
        return university;
    }

    public List<Student> getStudents() {
        return getUniversity().getStudents();
    }

    public List<Course> getCourses() {
        return getUniversity().getCourses();
    }

    @Transactional
    public String saveUniversity() {
        if (university.getId() == 0) {
            universityService.create(university);
        } else {
            universityService.update(university);
        }
        return "universities?faces-redirect=true";
    }

    @Transactional
    public String deleteUniversity() {
        if (university != null) {
            try {
                universityService.delete(university.getId());
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Could not delete University: " + e.getMessage()));
                return null;
            }
        }
        return "universities?faces-redirect=true";
    }
}

