package com.example.rateUniversityApplication.dao;

import com.example.rateUniversityApplication.entity.Course;

public class CourseFeedbackModel {
public String course;
public FeedbackModel feedbackModel;

    public CourseFeedbackModel(String course, FeedbackModel feedbackModel) {
        this.course = course;
        this.feedbackModel = feedbackModel;
    }
}
