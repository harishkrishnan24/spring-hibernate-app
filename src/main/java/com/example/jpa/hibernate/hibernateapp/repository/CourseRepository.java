package com.example.jpa.hibernate.hibernateapp.repository;

import com.example.jpa.hibernate.hibernateapp.entity.Course;
import com.example.jpa.hibernate.hibernateapp.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }

        return course;
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web services in 100 steps");
        entityManager.persist(course1);

        Course course2 = findById(10001L);
        course2.setName("JPA in 50 Steps - Updated");
    }

//    public void addReviewsForCourse() {
//        Course course = findById(10003L);
//        logger.info("course.getReviews() -> {}", course.getReviews());
//
//        Review review1 = new Review("5", "Great Hands on stuff");
//        Review review2 = new Review("5", "Hats off!");
//
//        course.addReview(review1);
//        review1.setCourse(course);
//        course.addReview(review2);
//        review2.setCourse(course);
//
//        entityManager.persist(review1);
//        entityManager.persist(review2);
//    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("course.getReviews() -> {}", course.getReviews());

        for (Review review: reviews) {
            course.addReview(review);
            review.setCourse(course);

            entityManager.persist(review);
        }

    }
}
