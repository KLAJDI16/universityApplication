package com.example.rateUniversityApplication.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FeedbackModel {
	public String feedbackDescription;
	public int rating;
	public LocalDateTime date;
	public String formattedDate;

	public FeedbackModel(String feedbackDescription, int rating) {
		super();
		this.feedbackDescription = feedbackDescription;
		this.rating = rating;
		date = LocalDateTime.now();
		 formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public FeedbackModel(String feedbackDescription, int rating, LocalDateTime date) {
		super();
		this.feedbackDescription = feedbackDescription;
		this.rating = rating;
		this.date = date;
		 formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	}
	

	public String getDescription() {
		return feedbackDescription;
	}

	public int getRating() {
		return rating;
	}
	
	
}
