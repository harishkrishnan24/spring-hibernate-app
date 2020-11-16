package com.example.jpa.hibernate.hibernateapp.repository;

import com.example.jpa.hibernate.hibernateapp.entity.Passport;
import com.example.jpa.hibernate.hibernateapp.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }

        return student;
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        entityManager.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);

        entityManager.persist(student);
    }
}
