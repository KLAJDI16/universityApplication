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

 @Autowired
 public UniversityApplication(CourseRepository courseRepository, UserRepository userRepository) {
  this.courseRepository = courseRepository;
  this.userRepository = userRepository;
 }
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
   int n=10;
   Course[] courses = new Course[n];
   Course course = new Course();
   course.setId(1);
   course.setName("Software Engineering" );
   course.setDescription("This course includes information on UML diagrams, software strategies, code refactoring, unit tests etc." );
   course.setLecturer("Ina Papadhopuli");
   course.setHallLocation("209");
   course.setStartDate(LocalDateTime.of(2023, 10, 5, 9, 0, 0));
   course.setEndDate(LocalDateTime.now().plusMonths(4));
   course.setRegisteredStudents(0);
   courses[0] = course;


   Course computerNetworks = new Course();
   computerNetworks.setId(2);
   computerNetworks.setName("Computer Networks");
   computerNetworks.setDescription("Learn about computer networks, protocols, routing, and more.");
   computerNetworks.setLecturer("Indrit Enesi");
   computerNetworks.setHallLocation("210");
   computerNetworks.setStartDate(LocalDateTime.of(2023, 10, 5, 9, 0, 0));
   computerNetworks.setEndDate(LocalDateTime.now().plusMonths(4));
   computerNetworks.setRegisteredStudents(0);
   courses[1] = computerNetworks;

   Course webProgramming = new Course();
   webProgramming.setId(3);
   webProgramming.setName("Web Programming");
   webProgramming.setDescription("Explore web development, HTML, CSS, JavaScript, and PHP.");
   webProgramming.setLecturer("Nelda Kote");
   webProgramming.setHallLocation("211");
   webProgramming.setStartDate(LocalDateTime.of(2023, 10, 5, 9, 0, 0));
   webProgramming.setEndDate(LocalDateTime.now().plusMonths(4));
   webProgramming.setRegisteredStudents(0);
   courses[2] = webProgramming;

   Course operativeSystems = new Course();
   operativeSystems.setId(4);
   operativeSystems.setName("Operative Systems");
   operativeSystems.setDescription("Study the principles of operative systems and their functionalities.");
   operativeSystems.setLecturer("Dorian Minarolli");
   operativeSystems.setHallLocation("212");
   operativeSystems.setStartDate(LocalDateTime.of(2023, 10, 4, 8, 0, 0));
   operativeSystems.setEndDate(LocalDateTime.now().plusMonths(4));
   operativeSystems.setRegisteredStudents(0);
   courses[3] = operativeSystems;

   Course algorithmsAndProgramming = new Course();
   algorithmsAndProgramming.setId(5);
   algorithmsAndProgramming.setName("Algorithms and Advanced Programming");
   algorithmsAndProgramming.setDescription("Explore advanced algorithms and programming techniques.");
   algorithmsAndProgramming.setLecturer("Ina Papadhopuli");
   algorithmsAndProgramming.setHallLocation("213");
   algorithmsAndProgramming.setStartDate(LocalDateTime.of(2023, 10, 5, 9, 0, 0));
   algorithmsAndProgramming.setEndDate(LocalDateTime.now().plusMonths(4));
   algorithmsAndProgramming.setRegisteredStudents(0);
   courses[4] = algorithmsAndProgramming;

   Course electronicSystems = new Course();
   electronicSystems.setId(6);
   electronicSystems.setName("Electronic Systems");
   electronicSystems.setDescription("Learn about electronic systems, circuits, and components.");
   electronicSystems.setLecturer("Alban Rakipi");
   electronicSystems.setHallLocation("214");
   electronicSystems.setStartDate(LocalDateTime.of(2024, 3, 5, 11, 0, 0));
   electronicSystems.setEndDate(LocalDateTime.now().plusMonths(4));
   electronicSystems.setRegisteredStudents(0);
   courses[5] = electronicSystems;

   Course objectOrientedProgramming = new Course();
   objectOrientedProgramming.setId(7);
   objectOrientedProgramming.setName("Object Oriented Programming");
   objectOrientedProgramming.setDescription("Understand object-oriented programming principles and design.");
   objectOrientedProgramming.setLecturer("Elinda Meçe");
   objectOrientedProgramming.setHallLocation("303");
   objectOrientedProgramming.setStartDate(LocalDateTime.of(2023, 10, 6, 9, 0, 0));
   objectOrientedProgramming.setEndDate(LocalDateTime.now().plusMonths(4));
   objectOrientedProgramming.setRegisteredStudents(0);
   courses[6] = objectOrientedProgramming;

   Course calculusI = new Course();
   calculusI.setId(8);
   calculusI.setName("Calculus I");
   calculusI.setDescription("Study the fundamentals of calculus, derivatives, and integrals.");
   calculusI.setLecturer("Akli Fundo");
   calculusI.setHallLocation("216");
   calculusI.setStartDate(LocalDateTime.of(2023, 10, 3, 10, 0, 0));
   calculusI.setEndDate(LocalDateTime.now().plusMonths(4));
   calculusI.setRegisteredStudents(0);
   courses[7] = calculusI;

   Course algebraAndGeometry = new Course();
   algebraAndGeometry.setId(9);
   algebraAndGeometry.setName("Linear Algebra and Geometry");
   algebraAndGeometry.setDescription("Explore algebraic and geometric concepts and applications.");
   algebraAndGeometry.setLecturer("Adrian Naço");
   algebraAndGeometry.setHallLocation("217");
   algebraAndGeometry.setStartDate(LocalDateTime.of(2023, 10, 6, 8, 0, 0));
   algebraAndGeometry.setEndDate(LocalDateTime.now().plusMonths(4));
   algebraAndGeometry.setRegisteredStudents(0);
   courses[8] = algebraAndGeometry;

   Course electronicFundamentals = new Course();
   electronicFundamentals.setId(10);
   electronicFundamentals.setName("Electronic Fundamentals");
   electronicFundamentals.setDescription("Learn the fundementals of of electronics like diodes and transistors.");
   electronicFundamentals.setLecturer("Ezmerina Kotobelli");
   electronicFundamentals.setHallLocation("218");
   electronicFundamentals.setStartDate(LocalDateTime.of(2023, 10, 5, 9, 0, 0));
   electronicFundamentals.setEndDate(LocalDateTime.now().plusMonths(4));
   electronicFundamentals.setRegisteredStudents(0);
   courses[9] = electronicFundamentals;

// for (int i = 1; i <= 6; i++) {
// Course course = new Course();
// course.setId(i);
// course.setName("Course " + i);
// course.setDescription("Description for Course " + i);
// course.setLecturer("Lecturer " + i);
// course.setHallLocation("Hall " + i);
// course.setStartDate(LocalDateTime.now()); // Set the start date to the current date and time
// course.setEndDate(LocalDateTime.now().plusMonths(3)); // Set the end date to 3 months from now
// course.setRegisteredStudents(0); // Initially, no students are registered
//
// courses[i-1]=course;
// }

   courseRepository.saveAll(List.of(courses));
  }
 }
}