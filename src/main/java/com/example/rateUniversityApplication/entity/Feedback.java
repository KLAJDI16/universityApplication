package com.example.rateUniversityApplication.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.example.rateUniversityApplication.dao.FeedbackModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="feedback_table")
@NoArgsConstructor
public class Feedback implements Comparable<Feedback>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
//@Length(max = 1000,
//message = "The length of the feedback should include not more than 1000 characters ")
	private String feedbackDescription;

//@Size(min = 1,max = 5)
   private int rating;

    private LocalDateTime date;
	public Feedback(String feedbackDescription, int rating) {
		super();
		this.feedbackDescription = feedbackDescription;
		this.rating = rating;
		this.date = LocalDateTime.now();
	}
	
	public FeedbackModel getFeedbackModel() {
		return new FeedbackModel(feedbackDescription,rating,date);
	}

	@Override
	public int compareTo(Feedback o) {
		// TODO Auto-generated method stub
		int x = date.compareTo(date);
	  return x;
	}
	
	
}
