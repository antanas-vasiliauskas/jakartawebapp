package com.example;

import java.util.List;

public interface UniversityDao {
    void add(University university);
    List<University> findAll();
}
