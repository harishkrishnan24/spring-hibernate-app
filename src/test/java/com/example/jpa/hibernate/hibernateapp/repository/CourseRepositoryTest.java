package com.example.jpa.hibernate.hibernateapp.repository;

import com.example.jpa.hibernate.hibernateapp.entity.Course;
import com.example.jpa.hibernate.hibernateapp.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findCourseById() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());
    }

    @Test
    @DirtiesContext
    void saveCourse() {
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA in 50 Steps", course.getName());

        course.setName("JPA in 50 Steps - Updated");
        courseRepository.save(course);
        Course course1 = courseRepository.findById(10001L);
        assertEquals("JPA in 50 Steps - Updated", course1.getName());
    }

    @Test
    @DirtiesContext
    void deleteCourseById() {
        courseRepository.deleteById(10002L);

        assertNull(courseRepository.findById(10002L));
    }

    @Test
    @DirtiesContext
    void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        System.out.println(course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForAReview() {
        Review review = entityManager.find(Review.class, 50001L);
        System.out.println(review.getCourse());
    }
}