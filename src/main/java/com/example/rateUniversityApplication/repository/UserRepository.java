package com.example.rateUniversityApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rateUniversityApplication.entity.Course;
import com.example.rateUniversityApplication.entity.CourseFeedback;
import com.example.rateUniversityApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	public List<User> findByUsername(String username);
	public List<User> findByPassword(String password);
	public List<User> findByUsernameAndPassword(String username, String password);
//	public Optional<User> findByCourse(Course course);
	public Optional<User> findByCourseFeedback(CourseFeedback courseFeedback);

}
