package com.example;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named("universityBean")
@RequestScoped
public class UniversityBean {

    @Inject
    private UniversityService universityService;
    
    private String name;  // bound to input for new University name

    // Getter and setter for the name field (for form binding)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Action method to add a new University.
     */
    public String addUniversity() {
        if (name != null && !name.trim().isEmpty()) {
            universityService.addUniversity(name.trim());
            // Reset the name field after adding
            name = null;
        }
        // Return null to stay on the same page and refresh list
        return null;
    }

    /**
     * Returns the list of all universities for display.
     */
    public List<University> getUniversities() {
        return universityService.getAllUniversities();
    }
}
