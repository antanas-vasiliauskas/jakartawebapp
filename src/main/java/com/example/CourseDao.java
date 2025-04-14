package com.example;

import java.util.List;

public interface CourseDao {
    List<Course> findAll();
    Course find(int id);
    void create(Course course);
    Course update(Course course);
    void delete(int id);
}
