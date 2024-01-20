package com.example.rateUniversityApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.rateUniversityApplication.entity.Feedback;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.rateUniversityApplication.entity.Course;
import com.example.rateUniversityApplication.entity.User;
import com.example.rateUniversityApplication.repository.CourseRepository;
import com.example.rateUniversityApplication.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class UniversityApplicationTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniversityApplication universityApplication;

    @Test
    public void contextLoads() {
        assertNotNull(universityApplication);
    }

    @Test
    public void testRun() {
        try {
            universityApplication.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Add your assertions based on the expected behavior of the run method
        // For example, check if courses and users are saved in the repositories
        assertEquals(10, courseRepository.count());
        assertEquals(4, userRepository.count());

        // Add more assertions as needed
    }

    @Test
    public void testAddUserToCourse() {
        // Create a course and a user
        Course course = new Course("Test Course");
        User user = new User("testUser", "password");

        // Save entities to the database
        courseRepository.save(course);
        userRepository.save(user);

        // Add the user to the course
        course.addUser(user);
        courseRepository.save(course);

        // Retrieve the course from the database
        Course retrievedCourse = courseRepository.findById(course.getId()).orElse(null);

        // Assert that the user is added to the course
        assertNotNull(retrievedCourse);
        assertEquals(1, retrievedCourse.getUsers().size());
    }

    @Test
    public void testRemoveUserFromCourse() {
        // Create a course and a user
        Course course = new Course("Test Course");
        User user = new User("testUser", "password");

        // Save entities to the database
        courseRepository.save(course);
        userRepository.save(user);

        // Add the user to the course
        course.addUser(user);
        courseRepository.save(course);

        // Remove the user from the course
        course.removeUser(user);
        courseRepository.save(course);

        // Retrieve the course from the database
        Course retrievedCourse = courseRepository.findById(course.getId()).orElse(null);

        // Assert that the user is removed from the course
        assertNotNull(retrievedCourse);
        assertEquals(0, retrievedCourse.getUsers().size());
    }

    @Test
    public void testAddFeedbackToCourse() {
        // Create a course and a feedback
        Course course = new Course("Test Course");
        Feedback feedback = new Feedback("Good course!", 3);

        // Save entities to the database
        courseRepository.save(course);

        // Add the feedback to the course
        course.addFeedback(feedback);
        courseRepository.save(course);

        // Retrieve the course from the database
        Course retrievedCourse = courseRepository.findById(course.getId()).orElse(null);

        // Assert that the feedback is added to the course
        assertNotNull(retrievedCourse);
        assertEquals(1, retrievedCourse.getListFeedbacks().size());
    }

    @Test
    public void testRemoveFeedbackFromCourse() {
        // Create a course and a feedback
        Course course = new Course("Test Course");
        Feedback feedback = new Feedback("Good course!", 3);

        // Save entities to the database
        courseRepository.save(course);

        // Add the feedback to the course
        course.addFeedback(feedback);
        courseRepository.save(course);

        // Remove the feedback from the course
        course.removeFeedback(feedback);
        courseRepository.save(course);

        // Retrieve the course from the database
        Course retrievedCourse = courseRepository.findById(course.getId()).orElse(null);

        // Assert that the feedback is removed from the course
        assertNotNull(retrievedCourse);
        assertEquals(0, retrievedCourse.getListFeedbacks().size());
    }

    @Test
    public void testAddCourseToUser() {
        // Create a course and a user
        Course course = new Course("Test Course");
        User user = new User("testUser", "password");

        // Save entities to the database
        courseRepository.save(course);
        userRepository.save(user);

        // Add the course to the user
        user.addCourse(course);
        userRepository.save(user);

        // Retrieve the user from the database
        User retrievedUser = userRepository.findById(user.getId()).orElse(null);

        // Assert that the course is added to the user
        assertNotNull(retrievedUser);
        assertEquals(1, retrievedUser.getCourses().size());
    }

    @Test
    public void testRemoveCourseFromUser() {
        // Create a course and a user
        Course course = new Course("Test Course");
        User user = new User("testUser", "password");

        // Save entities to the database
        courseRepository.save(course);
        userRepository.save(user);

        // Add the course to the user
        user.addCourse(course);
        userRepository.save(user);

        // Remove the course from the user
        user.removeCourse(course);
        userRepository.save(user);

        // Retrieve the user from the database
        User retrievedUser = userRepository.findById(user.getId()).orElse(null);

        // Assert that the course is removed from the user
        assertNotNull(retrievedUser);
        assertEquals(0, retrievedUser.getCourses().size());
    }
}