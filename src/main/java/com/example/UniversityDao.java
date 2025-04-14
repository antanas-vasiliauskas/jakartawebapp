package com.example;

import java.util.List;

public interface UniversityDao {
    List<University> findAll();
    University find(int id);
    void create(University university);
    University update(University university);
    void delete(int id);
}
