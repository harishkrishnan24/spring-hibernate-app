package com.example.jpa.hibernate.hibernateapp.repository;

import com.example.jpa.hibernate.hibernateapp.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class JPQLTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    void findCourses() {
        List resultList = entityManager.createNamedQuery("query_get_all_courses").getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    void findCoursesTyped() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    void findCoursesTypedWhere() {
        TypedQuery<Course> query = entityManager.createNamedQuery("query_get_100_Step_courses", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }
}