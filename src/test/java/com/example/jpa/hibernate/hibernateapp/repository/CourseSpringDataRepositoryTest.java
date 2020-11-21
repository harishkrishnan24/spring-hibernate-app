package com.example.jpa.hibernate.hibernateapp.repository;

import com.example.jpa.hibernate.hibernateapp.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CourseSpringDataRepositoryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CourseSpringDataRepository courseSpringDataRepository;

    @Test
    void findCourseByIdPresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(10001L);
        assertTrue(courseOptional.isPresent());
    }

    @Test
    void findCourseByIdNotPresent() {
        Optional<Course> courseOptional = courseSpringDataRepository.findById(20001L);
        assertFalse(courseOptional.isPresent());
    }

    @Test
    void playingAround() {

    }
}