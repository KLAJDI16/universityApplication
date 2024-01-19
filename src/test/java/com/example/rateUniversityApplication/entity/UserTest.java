package com.example.rateUniversityApplication.entity;

import static org.junit.jupiter.api.Assertions.*;
import com.example.rateUniversityApplication.dao.UserModel;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private User user;
    private Course course1;
    private Course course2;

    @BeforeEach
    void setUp() {
        user = new User("armando", "password");
        course1 = new Course("Math");
        course2 = new Course("History");
    }

    @Test
    void testAddCourse() {
        user.addCourse(course1);
        user.addCourse(course2);

        assertTrue(user.getCourses().contains(course1));
        assertTrue(user.getCourses().contains(course2));
    }

    @Test
    void testRemoveCourse() {
        user.addCourse(course1);
        user.addCourse(course2);

        user.removeCourse(course1);

        assertFalse(user.getCourses().contains(course1));
        assertTrue(user.getCourses().contains(course2));
    }

    @Test
    void testUserModel() {
        UserModel userModel = user.userModel();

        assertNotNull(userModel);
        assertEquals("armando", userModel.getUsername());
        assertEquals("password", userModel.getPassword());
    }
}
