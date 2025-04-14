package com.example;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;

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
    
    // Getter for list of universities (used in list page)
    public List<University> getUniversities() {
        if (universities == null) {
            universities = universityService.findAll();
        }
        return universities;
    }
    
    // Getter/Setter for view param id
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    // Getter for single University (used in detail page)
    public University getUniversity() {
        if (university == null) {
            if (id != null && id != 0) {
                // Editing existing University
                university = universityService.findById(id);
            } else {
                // Creating new University
                university = new University();
            }
        }
        return university;
    }
    
    // Action to save University (create or update)
    public String saveUniversity() {
        if (university.getId() == 0) {
            universityService.create(university);
        } else {
            universityService.update(university);
        }
        return "universities?faces-redirect=true";
    }
    
    // Action to delete the current University
    public String deleteUniversity() {
        if (university != null) {
            try {
                universityService.delete(university.getId());
            } catch (Exception e) {
                // Handle constraint issues (e.g., if not all cascade)
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Could not delete University: " + e.getMessage()));
                return null;
            }
        }
        return "universities?faces-redirect=true";
    }
}
