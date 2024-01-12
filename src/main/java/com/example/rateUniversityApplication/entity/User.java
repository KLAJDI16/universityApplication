package com.example.rateUniversityApplication.entity;

import java.util.List;

import com.example.rateUniversityApplication.dao.UserModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="user_table")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
	
private String username;
private String password;

@ManyToMany
@JoinTable(name = "student_course_table"
,joinColumns = {@JoinColumn(name  = "user")}
,inverseJoinColumns  = {@JoinColumn(name  = "course")}
)
private List<Course> courses;

@OneToOne
private CourseFeedback courseFeedback;

public void addCourse(Course course) {
	courses.add(course);
}
public void removeCourse(Course course) {
	courses.remove(course);
}

public User(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}

public User(String username, String password, List<Course> courses, CourseFeedback courseFeedback) {
	super();
	this.username = username;
	this.password = password;
	this.courses = courses;
	this.courseFeedback = courseFeedback;
}

public User(String username, String password, List<Course> courses) {
	super();
	this.username = username;
	this.password = password;
	this.courses = courses;
}

public UserModel userModel() {
	return new UserModel(username,  password);
}

}
