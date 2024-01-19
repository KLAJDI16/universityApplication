package com.example.rateUniversityApplication;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.rateUniversityApplication.entity.Course;
import com.example.rateUniversityApplication.entity.User;
import com.example.rateUniversityApplication.repository.CourseRepository;
import com.example.rateUniversityApplication.repository.UserRepository;

@SpringBootTest
class UniversityApplicationTests {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		// Clear existing data before each test
		courseRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void testRun() throws Exception {
		// Run the method
		UniversityApplication universityApplication = new UniversityApplication(courseRepository, userRepository);
		universityApplication.run();

		// Check if users are saved
		List<User> users = userRepository.findAll();
		assertEquals(4, users.size());

		// Check if courses are saved
		List<Course> courses = courseRepository.findAll();
		assertEquals(10, courses.size());
	}

	@Test
	void testRunWithExistingData() throws Exception {
		// Save some users and courses
		User user = new User("testuser@gmail.com", "password");
		userRepository.save(user);

		Course course = new Course();
		course.setId(11);
		course.setName("Test Course");
		course.setDescription("Test Description");
		course.setLecturer("Test Lecturer");
		course.setHallLocation("Test Hall");
		course.setStartDate(LocalDateTime.now());
		course.setEndDate(LocalDateTime.now().plusMonths(3));
		course.setRegisteredStudents(0);
		courseRepository.save(course);

		// Run the method
		UniversityApplication universityApplication = new UniversityApplication(courseRepository, userRepository);
		universityApplication.run();

		// Check if existing data is not overwritten
		List<User> users = userRepository.findAll();
		assertEquals(1, users.size());

		List<Course> updatedCourses = courseRepository.findAll();
		assertEquals(11, updatedCourses.size());
	}
}



