package com.example.vaadindemo.repository;

import com.example.vaadindemo.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Course course) {
        em.persist(course);
    }

    public void delete(Course course) {
        em.remove(em.find(Course.class,course.getId()));
    }

    public void update(Course course) {
        em.merge(course);

    }

    public List<Course> findAll() {
        return em.createQuery("select n from Course n").getResultList();
    }
}
