package com.example.rateUniversityApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.rateUniversityApplication.dao.CourseFeedbackModel;
import com.example.rateUniversityApplication.dao.CourseFeedbackRating;
import com.example.rateUniversityApplication.dao.CourseModel;
import com.example.rateUniversityApplication.dao.FeedbackModel;
import com.example.rateUniversityApplication.dao.RequestModel;
import com.example.rateUniversityApplication.dao.UserModel;
import com.example.rateUniversityApplication.service.UserService;

@RestController("/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class MainController {

@Autowired
public UserService userService;

@PostMapping("/register")
public ResponseEntity<UserModel> register(@RequestBody  UserModel request) {
return ResponseEntity.ok(userService.register(request));
}

@PostMapping("/login")
public boolean login(@RequestBody UserModel request) {
return userService.login(request);
}

@PostMapping("/join")
public  ResponseEntity<RequestModel> joinCourse(@RequestBody RequestModel<String> requestModel) {
return ResponseEntity.ok(userService.joinCourse(requestModel));
}
@PostMapping("/drop")
public ResponseEntity<RequestModel> dropCourse(@RequestBody RequestModel<String> requestModel) {
return ResponseEntity.ok(userService.dropCourse(requestModel));
}

@GetMapping("/retrieve")
public ResponseEntity<List<CourseModel>> retrieveCourses() {
return ResponseEntity.ok(userService.retrieveCourses());
}

@PostMapping("/leaveFeedback")
public ResponseEntity<FeedbackModel> leaveFeedback(@RequestBody RequestModel<CourseFeedbackModel> requestModel) {
return ResponseEntity.ok(userService.leaveFeedback(requestModel));
}

@GetMapping("/view/{courseName}")
public ResponseEntity<CourseFeedbackRating> viewFeedbacks(@PathVariable("courseName") String courseName) {
return ResponseEntity.ok(userService.viewFeedbacks(courseName));

	}
	@GetMapping("/courses/{rate}")
	public ResponseEntity<List<CourseModel>> topCourses(@PathVariable("rate") int rate){
		return 	ResponseEntity.ok(userService.topCourses(rate));
	}
//	@GetMapping("/courses")
//	public ResponseEntity<List<CourseModel>> listAllCourses(){
//		return 	ResponseEntity.ok(userService.listAllCourses());
//	}
	@GetMapping("/userCourses/{username}")

	public ResponseEntity<List<CourseModel>> getUserCourses(@PathVariable("username") String name) {
		return ResponseEntity.ok(userService.getUserCourses(name));
	}


}
