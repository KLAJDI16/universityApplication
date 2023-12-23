package com.example.rateUniversityApplication.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.rateUniversityApplication.dao.CourseFeedbackModel;
import com.example.rateUniversityApplication.dao.CourseFeedbackRating;
import com.example.rateUniversityApplication.dao.CourseModel;
import com.example.rateUniversityApplication.dao.FeedbackModel;
import com.example.rateUniversityApplication.dao.RequestModel;
import com.example.rateUniversityApplication.dao.UserModel;
import com.example.rateUniversityApplication.entity.Course;
import com.example.rateUniversityApplication.entity.CourseFeedback;
import com.example.rateUniversityApplication.entity.Feedback;
import com.example.rateUniversityApplication.entity.User;
import com.example.rateUniversityApplication.repository.CourseFeedbackRepository;
import com.example.rateUniversityApplication.repository.CourseRepository;
import com.example.rateUniversityApplication.repository.FeedbackRepository;
import com.example.rateUniversityApplication.repository.UserRepository;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository; 
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private CourseFeedbackRepository courseFeedbackRepository;

	
	
	public UserModel register(UserModel request) {
		List<User> users = userRepository.findByUsername(request.username);
		validateUser(request);
		if(!users.isEmpty()) printError(HttpStatus.BAD_REQUEST,"User already exists");
		
		userRepository.save(new User(request.username,request.password) );
	
		return request;
	}

	public boolean login(UserModel request) {
		List<User> users = userRepository.findByUsername(request.username);
		validateUser(request);
		if(users.isEmpty()) printError(HttpStatus.BAD_REQUEST,"User is not  registered  ");
		
		List<User> users2 = userRepository.findByUsernameAndPassword(request.username,request.password);
		if(users2.isEmpty()) { 
			printError(HttpStatus.BAD_REQUEST,"Wrong password");
          return false;
		}
		return true;
	}
	
	public  RequestModel joinCourse(RequestModel<String> requestModel) {
		if (!login(requestModel.userModel)){
			printError(HttpStatus.NO_CONTENT,"Validation failed");
		}
		User user = userRepository.findByUsernameAndPassword(requestModel.userModel.username, requestModel.userModel.password).get(0);
		Course course = courseRepository.findByName(requestModel.request).get(0);
		if(user.getCourses().contains(course)) {
			printError(HttpStatus.BAD_REQUEST,"User already registered in this course");
		}
		user.addCourse(course);
		course.addUser(user);
		courseRepository.save(course);
		userRepository.save(user);
		return requestModel;
	}

	public RequestModel dropCourse(RequestModel<String> requestModel) {
		if (!login(requestModel.userModel)){
			printError(HttpStatus.NO_CONTENT,"Login failed");
		}
		User user = userRepository.findByUsernameAndPassword(requestModel.userModel.username, requestModel.userModel.password).get(0);
		
		Course course = courseRepository.findByName(requestModel.request).get(0);
		
		user.removeCourse(course);
		course.removeUser(user);
		courseRepository.save(course);
		userRepository.save(user);
		return requestModel;
	}
	
	public List<CourseModel> retrieveCourses() {
		return courseRepository.findAll().stream().map(e -> e.getCourseModel()).toList();
	}
	
	public FeedbackModel leaveFeedback(RequestModel<CourseFeedbackModel> requestModel) {
		if (!login(requestModel.userModel)){
			printError(HttpStatus.NO_CONTENT,"Validation failed");
		}
		User user = userRepository.findByUsername(requestModel.userModel.username).get(0);
		Course course = courseRepository.findByName(requestModel.request.course).get(0);
		if(!course.getUsers().contains(user)) {
			printError(HttpStatus.BAD_REQUEST,"User not registered in the Course");
		}


		Feedback feedback = new Feedback(requestModel.request.feedbackModel.feedbackDescription,requestModel.request.feedbackModel.rating);
		feedbackRepository.save(feedback);
		course.addFeedback(feedback);
		courseRepository.save(course);

		CourseFeedback courseFeedback = new CourseFeedback(course,feedback);
		if(courseFeedbackRepository.findByCourseAndFeedback(course, feedback).size()>0) {
			printError(HttpStatus.BAD_REQUEST,"You can not leave more than 1 feedback in the Course");
		}
		courseFeedbackRepository.save(courseFeedback);
		
		//
		return requestModel.request.feedbackModel;
		
	}

	public CourseFeedbackRating viewFeedbacks(String courseName) {
		Course course = courseRepository.findByName(courseName).get(0);
		int count = 0;
		for(Feedback feedback: course.getListFeedbacks()) {
			count+=feedback.getRating();
		}
		double avg = (double)count / (double) course.getListFeedbacks().size();
		
	List<FeedbackModel> list =	course.getListFeedbacks().stream().sorted().map(e -> e.getFeedbackModel()).toList();
		
		 
		 
		 
		 return new CourseFeedbackRating(list,avg);
				
	}
	
	public List<CourseModel> topCourses(int rate){
		List<CourseFeedback> list = courseFeedbackRepository.findAll().stream().sorted().limit(rate).toList(); 
		
		return list.stream().map(e -> e.getCourse()).toList().stream().map(e -> e.getCourseModel()).toList();
	}

	public List<CourseModel> listAllCourses(){
		List<CourseFeedback> list = courseFeedbackRepository.findAll().stream().toList(); 
		if(list.isEmpty()) {
			printError(HttpStatus.NO_CONTENT,"List is empty");
		}
		return list.stream().map(e -> e.getCourse()).toList().stream().map(e -> e.getCourseModel()).toList();
	}
	
	@Scheduled(fixedDelay = 1000*60*5)
	public void deleteFeedback() {
		for(Feedback feedback : feedbackRepository.findAll()) {
			if(feedback.getDate().plusYears(1).isAfter(LocalDateTime.now())) {
				CourseFeedback courseFeedback = courseFeedbackRepository.findByFeedback(feedback).get(0);
				courseFeedbackRepository.delete(courseFeedback);
				feedbackRepository.delete(feedback);
			}
		}
	}
	
	
	public void printError(HttpStatus httpStatus,String str) {
		System.err.println(str);
		throw new ResponseStatusException(httpStatus, str);
	}
	
	public void validateUser(UserModel request) {
		if(request.username.length()<=5) printError(HttpStatus.BAD_REQUEST,"Username should be with more than 5 characters ");
		if(request.username.length()>=30) printError(HttpStatus.BAD_REQUEST,"Username should be with less than 30 characters ");	
		if(request.password.length()<=5) printError(HttpStatus.BAD_REQUEST,"Password should be with more than 5 characters ");
		if(request.password.length()>=30) printError(HttpStatus.BAD_REQUEST,"Password should be with less than 30 characters ");
	}
}
