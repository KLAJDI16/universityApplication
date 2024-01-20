package com.example.rateUniversityApplication.service;

import com.example.rateUniversityApplication.dao.*;
import com.example.rateUniversityApplication.entity.Course;
import com.example.rateUniversityApplication.entity.CourseFeedback;
import com.example.rateUniversityApplication.entity.Feedback;
import com.example.rateUniversityApplication.entity.User;
import com.example.rateUniversityApplication.repository.CourseFeedbackRepository;
import com.example.rateUniversityApplication.repository.CourseRepository;
import com.example.rateUniversityApplication.repository.FeedbackRepository;
import com.example.rateUniversityApplication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private CourseFeedbackRepository courseFeedbackRepository;

    @InjectMocks
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_Success() {
        // Arrange
        UserModel userModel = new UserModel("newUser", "password");

        when(userRepository.findByUsername("newUser")).thenReturn(Collections.emptyList());

        // Act
        UserModel result = userService.register(userModel);

        // Assert
        assertNotNull(result);
        assertEquals(userModel, result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_UserAlreadyExists() {
        // Arrange
        UserModel userModel = new UserModel("existingUser", "password");

        when(userRepository.findByUsername("existingUser")).thenReturn(Arrays.asList(new User()));

        // Act/Assert
        assertThrows(ResponseStatusException.class, () -> userService.register(userModel));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void loginUser_Success() {
        // Arrange
        UserModel userModel = new UserModel("existingUser", "password");

        when(userRepository.findByUsernameAndPassword("existingUser", "password"))
                .thenReturn(Arrays.asList(new User("existingUser", "password")));

        // Act
        boolean result = userService.login(userModel);

        // Assert
        assertTrue(result);
    }

    @Test
    void loginUser_UserNotRegistered() {
        // Arrange
        UserModel userModel = new UserModel("nonExistingUser", "password");

        when(userRepository.findByUsername("nonExistingUser")).thenReturn(Collections.emptyList());

        // Act
        assertThrows(ResponseStatusException.class, () -> userService.login(userModel));
    }

    @Test
    void loginUser_WrongPassword() {
        // Arrange
        UserModel userModel = new UserModel("existingUser", "wrongPassword");

        when(userRepository.findByUsernameAndPassword("existingUser", "wrongPassword")).thenReturn(Collections.emptyList());

        // Act
        assertThrows(ResponseStatusException.class, () -> userService.login(userModel));
    }

    @Test
    void joinCourse_Success() {
        // Arrange
        RequestModel<String> requestModel = new RequestModel("user", "CourseName");
        User user = new User("user", "password");
        Course course = new Course("CourseName");

        when(userRepository.findByUsernameAndPassword("user", "password")).thenReturn(Arrays.asList(user));
        when(courseRepository.findByName("CourseName")).thenReturn(Arrays.asList(course));

        // Act
        RequestModel<String> result = userService.joinCourse(requestModel);

        // Assert
        assertEquals(requestModel, result);
        assertTrue(user.getCourses().contains(course));
        assertTrue(course.getUsers().contains(user));
        verify(courseRepository, times(1)).save(course);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void joinCourse_UserNotLoggedIn() {
        // Arrange
        RequestModel<String> requestModel = new RequestModel("user", "CourseName");

        when(userRepository.findByUsernameAndPassword("user", "password")).thenReturn(Collections.emptyList());

        // Act/Assert
        Throwable exception = assertThrows(ResponseStatusException.class, () -> {
            userService.joinCourse(null);  // Passing null to simulate not logged in
        });

        // Assert: Check that the expected exception is thrown
        assertEquals("404 NOT_FOUND \"User not logged in\"", exception.getMessage());
        verify(courseRepository, never()).save(any(Course.class));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void joinCourse_UserAlreadyRegisteredInCourse() {
        // Arrange
        RequestModel<String> requestModel = new RequestModel("user", "CourseName");
        User user = new User("user","test2321");
        Course course = new Course("CourseName");
        user.addCourse(course);

        when(userRepository.findByUsername("user")).thenReturn(Arrays.asList(user));
        when(courseRepository.findByName("CourseName")).thenReturn(Arrays.asList(course));

        // Act/Assert
        assertThrows(ResponseStatusException.class, () -> userService.joinCourse(requestModel));
        verify(courseRepository, never()).save(any(Course.class));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void dropCourse_Success() {

    }

    @Test
    void retrieveCourses_Success() {
        // Arrange
        Course course1 = new Course("Course1");
        Course course2 = new Course("Course2");

        when(courseRepository.findAll()).thenReturn(Arrays.asList(course1, course2));

        // Act
        List<CourseModel> result = userService.retrieveCourses();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(cm -> "Course1".equals(cm.getName())));
        assertTrue(result.stream().anyMatch(cm -> "Course2".equals(cm.getName())));
    }

    @Test
    void leaveFeedback_Success() {
        // ... (same as before)
    }

    @Test
    void leaveFeedback_UserNotLoggedIn() {
        // Arrange
        RequestModel<CourseFeedbackModel> requestModel = new RequestModel<>(
                "user",
                new CourseFeedbackModel("CourseName", new FeedbackModel("Description", 5))
        );

        when(userRepository.findByUsername("user")).thenReturn(Collections.emptyList());

        // Act/Assert
        assertThrows(ResponseStatusException.class, () -> userService.leaveFeedback(requestModel));
        verify(courseRepository, never()).save(any(Course.class));
        verify(feedbackRepository, never()).save(any(Feedback.class));
        verify(courseFeedbackRepository, never()).save(any(CourseFeedback.class));
    }

    @Test
    void leaveFeedback_UserNotRegisteredInCourse() {
        // ... (same as before)
    }

    @Test
    void viewFeedbacks_Success() {
        // Arrange
        Course course = new Course("CourseName");
        Feedback feedback1 = new Feedback("Description1", 4);
        Feedback feedback2 = new Feedback("Description2", 5);
        course.addFeedback(feedback1);
        course.addFeedback(feedback2);

        when(courseRepository.findByName("CourseName")).thenReturn(Arrays.asList(course));

        // Act
        CourseFeedbackRating result = userService.viewFeedbacks("CourseName");

        // Assert
        assertEquals(2, result.getFeedbacks().size());
        assertEquals(4.5, result.getAverageRating());
        assertEquals("Description1", result.getFeedbacks().get(0).getDescription());
        assertEquals(5, result.getFeedbacks().get(1).getRating());
    }

    @Test
    void topCourses_Success() {
        // Arrange
        CourseFeedback courseFeedback1 = new CourseFeedback(new Course("Course1"), new Feedback("Desc1", 4));
        CourseFeedback courseFeedback2 = new CourseFeedback(new Course("Course2"), new Feedback("Desc2", 5));

        when(courseFeedbackRepository.findAll()).thenReturn(Arrays.asList(courseFeedback1, courseFeedback2));

        // Act
        List<CourseModel> result = userService.topCourses(2);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(cm -> "Course1".equals(cm.getName())));
        assertTrue(result.stream().anyMatch(cm -> "Course2".equals(cm.getName())));
    }

    @Test
    void listAllCourses_Empty() {
        // Arrange
        when(courseFeedbackRepository.findAll()).thenReturn(Collections.emptyList());

        // Act/Assert
        assertThrows(ResponseStatusException.class, () -> userService.listAllCourses());
    }

    @Test
    void listAllCourses_Success() {
        // Mock data
        Course course1 = new Course("Course1");
        Course course2 = new Course("Course2");
        List<Course> expectedCourses = Arrays.asList(course1, course2);

        // Mock the repository to return the expected data
        when(courseRepository.findAll()).thenReturn(expectedCourses);

        // Call the service method
        List<CourseModel> actualCourses = userService.listAllCourses();

        // Verify the result
        assertThat(actualCourses, hasSize(2)); // Adjust the expected size accordingly
        assertThat(actualCourses.get(0).getName(), is("Course1"));
    }

    @Test
    void printError_Success() {
        // Arrange
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String errorMessage = "Error Message";

        // Act/Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> userService.printError(httpStatus, errorMessage));
        assertEquals(httpStatus, exception.getStatusCode());
        assertEquals(errorMessage, exception.getReason());
    }


    @Test
    void validateUser_InvalidUsernameLength() {
        // Arrange
        UserModel userModel = new UserModel("user", "password");

        // Act/Assert
        assertThrows(ResponseStatusException.class, () -> userService.validateUser(userModel));
    }

    @Test
    void validateUser_InvalidPasswordLength() {
        // Arrange
        UserModel userModel = new UserModel("validUsername", "pass");

        // Act/Assert
        assertThrows(ResponseStatusException.class, () -> userService.validateUser(userModel));
    }

    @Test
    void validateUser_ValidUsernameAndPassword() {
        // Arrange
        UserModel userModel = new UserModel("validUsername", "validPassword");

        // Act/Assert
        assertDoesNotThrow(() -> userService.validateUser(userModel));
    }
    @Test
    void getUserCourses_Success() {
        // Arrange
        User user = new User("username", "password");
        Course course1 = new Course("Course1");
        Course course2 = new Course("Course2");
        user.addCourse(course1);
        user.addCourse(course2);

        when(userRepository.findByUsername("username")).thenReturn(Arrays.asList(user));

        // Act
        List<CourseModel> result = userService.getUserCourses("username");

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(cm -> "Course1".equals(cm.getName())));
        assertTrue(result.stream().anyMatch(cm -> "Course2".equals(cm.getName())));
    }
}
