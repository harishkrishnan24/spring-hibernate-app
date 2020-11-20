package com.example.jpa.hibernate.hibernateapp.repository;

import com.example.jpa.hibernate.hibernateapp.entity.Course;
import com.example.jpa.hibernate.hibernateapp.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@SpringBootTest
class CriteriaQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    void findCoursesHaving100Steps() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);
        Predicate like = criteriaBuilder.like(courseRoot.get("name"), "%100 Steps");
        cq.where(like);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    void findCourses() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    void findCoursesWithoutStudents() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);
        Predicate isEmpty = criteriaBuilder.isEmpty(courseRoot.get("students"));
        cq.where(isEmpty);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    void join() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Join<Object, Object> join = courseRoot.join("students");

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

    @Test
    void leftJoin() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = criteriaBuilder.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);

        TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        logger.info("Typed Query -> {}", resultList);
    }

}