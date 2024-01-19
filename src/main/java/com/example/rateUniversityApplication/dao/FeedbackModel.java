package com.example.rateUniversityApplication.dao;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FeedbackModel {
	public String feedbackDescription;
	public int rating;
	public LocalDateTime date;

	public FeedbackModel(String feedbackDescription, int rating) {
		super();
		this.feedbackDescription = feedbackDescription;
		this.rating = rating;
		date = LocalDateTime.now();
	}

	public FeedbackModel(String feedbackDescription, int rating, LocalDateTime date) {
		super();
		this.feedbackDescription = feedbackDescription;
		this.rating = rating;
		this.date = date;
	}

	public String getDescription() {
		return feedbackDescription;
	}

	public int getRating() {
		return rating;
	}
	
	
}
