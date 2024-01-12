package com.example.rateUniversityApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.rateUniversityApplication.entity.Course;
import com.example.rateUniversityApplication.entity.User;
import com.example.rateUniversityApplication.repository.CourseRepository;
import com.example.rateUniversityApplication.repository.UserRepository;

@SpringBootApplication
@EnableScheduling
public class UniversityApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Autowired
	public CourseRepository courseRepository;
	@Autowired
	public UserRepository userRepository;

	
	@Override
	public void run(String... args) throws Exception {
		if(userRepository.count()==0) {
			User[] users = new User[] {
					new User("klajdi@gmail.com","klajdi1234"),
					new User("armando@gmail.com","armando1234"),
					new User("erta@gmail.com","erta1234"),
					new User("eridona@gmail.com","eridona1234"),
					} ;
			userRepository.saveAll(List.of(users));		
		}
		if(courseRepository.count()==0) {
			Course[] courses = new Course[6]; 
		for (int i = 1; i <= 6; i++) {
            Course course = new Course();
            course.setId(i);
            course.setName("Course " + i);
            course.setDescription("Description for Course " + i);
            course.setLecturer("Lecturer " + i);
            course.setHallLocation("Hall " + i);
            course.setStartDate(LocalDateTime.now());  // Set the start date to the current date and time
            course.setEndDate(LocalDateTime.now().plusMonths(3));  // Set the end date to 3 months from now
            course.setRegisteredStudents(0);  // Initially, no students are registered

            courses[i-1]=course;
        }
		courseRepository.saveAll(List.of(courses));			
	}
}
}
