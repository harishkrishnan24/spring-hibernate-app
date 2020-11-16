package com.example.jpa.hibernate.hibernateapp;

import com.example.jpa.hibernate.hibernateapp.entity.Course;
import com.example.jpa.hibernate.hibernateapp.repository.CourseRepository;
import com.example.jpa.hibernate.hibernateapp.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateAppApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(HibernateAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        studentRepository.saveStudentWithPassport();
//        courseRepository.playWithEntityManager();
    }
}
