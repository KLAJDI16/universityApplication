package com.example.rateUniversityApplication.dao;

import java.time.LocalDateTime;
import java.util.List;

public class CourseModel {
	public String name;
	public String description;
	public String lecturer;
	public String hallLocation;
	public LocalDateTime startDate;
	public LocalDateTime endDate;
	public int registeredStudents;

	public CourseModel(String name, String description, String lecturer, String hallLocation, LocalDateTime startDate,
			LocalDateTime endDate,int registeredStudents) {
		super();
		this.name = name;
		this.description = description;
		this.lecturer = lecturer;
		this.hallLocation = hallLocation;
		this.startDate = startDate;
		this.endDate = endDate;
		this.registeredStudents = registeredStudents;
	}

	
	
	
	
}
