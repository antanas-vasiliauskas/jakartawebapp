package com.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


@Alternative
@ApplicationScoped
public class CourseDaoMyBatis implements CourseDao {

    @Inject
    private SqlSession sqlSession;

    @Override
    public List<Course> findAll() {
        return sqlSession.getMapper(CourseMapper.class).selectAll();
    }

    @Override
    public Course find(int id) {
        return sqlSession.getMapper(CourseMapper.class).selectById(id);
    }

    @Override
    public void create(Course course) {
        sqlSession.getMapper(CourseMapper.class).insert(course);
    }

    @Override
    public Course update(Course course) {
        sqlSession.getMapper(CourseMapper.class).update(course);
        return course;
    }

    @Override
    public void delete(int id) {
        sqlSession.getMapper(CourseMapper.class).deleteById(id);
    }
}
