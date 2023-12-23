package com.example.rateUniversityApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rateUniversityApplication.entity.Course;
import com.example.rateUniversityApplication.entity.CourseFeedback;
import com.example.rateUniversityApplication.entity.Feedback;

@Repository
public interface CourseFeedbackRepository extends JpaRepository<CourseFeedback, Integer>{
 public List<CourseFeedback> findByCourseAndFeedback(Course course,Feedback feedback);
 public List<CourseFeedback> findByCourse(Course course);
 public List<CourseFeedback> findByFeedback(Feedback feedback);
}
