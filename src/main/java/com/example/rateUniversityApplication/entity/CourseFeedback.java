package com.example.rateUniversityApplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class CourseFeedback implements Comparable<CourseFeedback>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	@ManyToOne
	@JoinColumn(name = "feedback_id")
	private Feedback feedback;
	public CourseFeedback(Course course, Feedback feedback) {
		super();
		this.course = course;
		this.feedback = feedback;
	}
	@Override
	public int compareTo(CourseFeedback o) {
		// TODO Auto-generated method stub
		int x = feedback.getRating() - o.getFeedback().getRating();
	if(x==0) {
		return course.getStartDate().compareTo(o.getCourse().getStartDate());
	}
	return x;
	}
	
	
	 
}
