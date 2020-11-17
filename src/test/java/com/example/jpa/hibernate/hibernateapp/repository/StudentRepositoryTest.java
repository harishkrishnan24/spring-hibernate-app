package com.example.jpa.hibernate.hibernateapp.repository;

import com.example.jpa.hibernate.hibernateapp.entity.Passport;
import com.example.jpa.hibernate.hibernateapp.entity.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    public void someTest() {
        Student student = entityManager.find(Student.class, 20001L);
        Passport passport = student.getPassport();
        passport.setNumber("E123457");
        student.setName("Ranga - Updated");
    }

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails() {
        Student student = entityManager.find(Student.class, 20001L);

        logger.info("Student => {}", student);
        logger.info("Passport => {}", student.getPassport());
    }

    @Test
    @Transactional
    public void retrievePassportAndStudentDetails() {
        Passport passport = entityManager.find(Passport.class, 40001L);

        logger.info("Passport => {}", passport);
        logger.info("Student => {}", passport.getStudent());
    }

}