package com.example.rateUniversityApplication.dao;

import java.util.List;

public class CourseFeedbackRating {
	public List<FeedbackModel> feedbacks;
	public double averageRating;
	public CourseFeedbackRating(List<FeedbackModel> feedbackModel, double averageRating) {
		super();
		this.feedbacks = feedbackModel;
		this.averageRating = averageRating;
	}

	public List<FeedbackModel> getFeedbacks() {
		return feedbacks;
	}

	public double getAverageRating() {
		return averageRating;
	}
	
}
