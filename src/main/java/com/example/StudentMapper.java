package com.example;

import java.util.List;

public interface StudentMapper {
    Student selectById(int id);
    List<Student> selectAll();
    void insert(Student student);
    void update(Student student);
    void deleteById(int id);
    List<Student> selectByUniversityId(int universityId); // Newly added
    University selectUniversityById(int id); // Used for nested association
    List<Course> selectCoursesByStudentId(int studentId);
}
