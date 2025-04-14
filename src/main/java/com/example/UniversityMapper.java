package com.example;

import java.util.List;

public interface UniversityMapper {
    University selectById(int id);
    List<University> selectAll();
    void insert(University university);
    void update(University university);
    void deleteById(int id);

    // Nested selects used in resultMap
    List<Student> selectStudentsByUniversityId(int universityId);
    List<Course> selectCoursesByUniversityId(int universityId);
}
