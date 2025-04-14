package com.example;

import java.util.List;

public interface CourseMapper {
    Course selectById(int id);
    List<Course> selectAll();
    void insert(Course course);
    void update(Course course);
    void deleteById(int id);
}
