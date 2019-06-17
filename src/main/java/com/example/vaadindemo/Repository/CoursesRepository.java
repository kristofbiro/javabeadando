package com.example.vaadindemo.Repository;
import java.util.List;

import com.example.vaadindemo.Entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CoursesRepository {
    @PersistenceContext
    private EntityManager em;

    public void save (Course course) {
        em.persist(course);
    }

    public void delete (Course course){
        em.remove(course);
    }
    public void update (Course course){
        em.merge(course);
    }
    public List<Course> findAll (){
      return   em.createQuery("select n from Course n").getResultList();
    }



}
