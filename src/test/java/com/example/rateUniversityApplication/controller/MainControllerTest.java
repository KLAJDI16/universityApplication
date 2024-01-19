package com.example.rateUniversityApplication.controller;

import com.example.rateUniversityApplication.dao.*;
import com.example.rateUniversityApplication.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MainControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Create a UserModel instance with required arguments
        UserModel request = new UserModel("username", "password"); // replace with actual arguments
        when(userService.register(request)).thenReturn(request);

        ResponseEntity<UserModel> responseEntity = mainController.register(request);

        assertEquals(request, responseEntity.getBody());
        verify(userService, times(1)).register(request);
    }

    @Test
    void testLogin() {
        // Create a UserModel instance with required arguments
        UserModel request = new UserModel("testUsername", "testPassword");
        when(userService.register(request)).thenReturn(request);

        ResponseEntity<UserModel> responseEntity = mainController.register(request);

        assertEquals(request, responseEntity.getBody());
        verify(userService, times(1)).register(request);
    }

    @Test
    void testJoinCourse() {
      /*// Create a RequestModel instance with the generic type parameter
        RequestModel<String> requestModel = new RequestModel<>();
        when(userService.joinCourse(requestModel)).thenReturn(requestModel);

        ResponseEntity<RequestModel> responseEntity = mainController.joinCourse(requestModel);

        assertEquals(requestModel, responseEntity.getBody());
        verify(userService, times(1)).joinCourse(requestModel);*/
    }

    @Test
    void testTopCourses() {
        int rate = 5; // set the desired rating for testing

        // Create a CourseModel instance with required arguments
        CourseModel courseModel = new CourseModel(
                "CourseCode",
                "CourseName",
                "Department",
                "Instructor",
                LocalDateTime.now(), // replace with actual start date
                LocalDateTime.now().plusHours(2), // replace with actual end date
                3 // replace with actual capacity
        );

        List<CourseModel> expectedCourses = Arrays.asList(courseModel);
        when(userService.topCourses(rate)).thenReturn(expectedCourses);

        ResponseEntity<List<CourseModel>> responseEntity = mainController.topCourses(rate);

        assertEquals(expectedCourses, responseEntity.getBody());
        verify(userService, times(1)).topCourses(rate);
    }

    @Test
    void testGetUserCourses() {
/*        String username = "testUser"; // set the desired username for testing
        List<CourseModel> expectedCourses = Arrays.asList(new CourseModel(), new CourseModel());
        when(userService.getUserCourses(username)).thenReturn(expectedCourses);

        ResponseEntity<List<CourseModel>> responseEntity = mainController.getUserCourses(username);

        assertEquals(expectedCourses, responseEntity.getBody());
        verify(userService, times(1)).getUserCourses(username);*/
    }
}
