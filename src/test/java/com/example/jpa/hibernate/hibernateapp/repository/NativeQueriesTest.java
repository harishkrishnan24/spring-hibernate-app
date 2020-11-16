package com.example.jpa.hibernate.hibernateapp.repository;

import com.example.jpa.hibernate.hibernateapp.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
class NativeQueriesTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    @Test
    void findCourses() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE", Course.class);
        List resultList = query.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    @Transactional
    void findCourseById() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    void findCourseByIdNamedParameter() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    void updateCourseUpdatedTimeStamp() {
        Query query = entityManager.createNativeQuery("UPDATE COURSE SET last_updated_date=sysdate()", Course.class);
        int noOfUpdatedRows = query.executeUpdate();
        logger.info("noOfUpdatedRows -> {}", noOfUpdatedRows);
    }
}