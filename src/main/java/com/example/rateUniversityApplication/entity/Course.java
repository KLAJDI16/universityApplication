package com.example.rateUniversityApplication.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.rateUniversityApplication.dao.CourseModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="course_table")
@NoArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private String lecturer;
	private String hallLocation;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
    private int registeredStudents;

	
    @ManyToMany(mappedBy = "courses")
	private List<User> users;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Feedback> listFeedbacks;

	public void addUser(User user) {
		users.add(user);
		this.registeredStudents++;

	}
	public void removeUser(User user) {
		users.remove(user);
		this.registeredStudents--;

	}
	public void addFeedback(Feedback feedback) {
		listFeedbacks.add(feedback);
	}
	public void removeFeedback(Feedback feedback) {
		listFeedbacks.remove(feedback);
	}
	
	public Course(String name,String description, String lecturer, String hallLocation, int registeredStudents) {
		super();
		this.name = name;
		this.description = description;
		this.lecturer = lecturer;
		this.hallLocation = hallLocation;
		this.registeredStudents = 0;
		this.listFeedbacks = new ArrayList<Feedback>();
	}
	public Course(String name,String description, String lecturer, String hallLocation, LocalDateTime startDate, LocalDateTime endDate,
			int registeredStudents) {
		super();
		this.name = name;

		this.description = description;
		this.lecturer = lecturer;
		this.hallLocation = hallLocation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.registeredStudents = 0;
		this.listFeedbacks = new ArrayList<Feedback>();

	}
	public Course(String name,String description, String lecturer, String hallLocation, LocalDateTime startDate,
			LocalDateTime endDate, List<Feedback> listFeedbacks, int registeredStudents) {
		super();
		this.name = name;
		this.description = description;
		this.lecturer = lecturer;
		this.hallLocation = hallLocation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.listFeedbacks = listFeedbacks;
		this.registeredStudents = 0;
	}
	
	public CourseModel getCourseModel() {
		return new CourseModel(name, description, lecturer, hallLocation, startDate, endDate, registeredStudents);
	}
}
