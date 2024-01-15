import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';
import { FeedbackComponent } from '../feedback/feedback.component';
import { ListOfFeedbacksComponent } from '../list-of-feedbacks/list-of-feedbacks.component';
import { NavBarComponent } from '../nav-bar/nav-bar.component';
import {MatDividerModule} from '@angular/material/divider';

@Component({
  selector: 'app-course-page',
  standalone: true,
  imports: [FeedbackComponent,MatDividerModule, ListOfFeedbacksComponent, NavBarComponent ],
  templateUrl: './course-page.component.html',
  styleUrl: './course-page.component.css'
})
export class CoursePageComponent {

  course1!:any;
  constructor(private apiService: ApiService, private router: Router,) {}

  course = {
    courseName: 'Introduction to Angular',
    professor: 'Dr. Smith',
    hall: 'Building A, Room 101',
    semester: 'Spring 2023',
    dayOfWeek: 'Monday',
    numStudents: 25
  };

  ngOnInit() {

    //this.fetchCourses(); 
  }

  enroll(){
    this.apiService.joinCourse(this.course);
  }

  unenroll(){
    this.apiService.dropCourse(this.course);
  }
  
  // toggleEnrollment(course: any) {
  //   if (this.isRegistered(course)) {
  //     // Unregister logic here
  //   } else {
  //   } SHFAQE NE HTML ME NGIF!
  // }

  isRegistered(course: any): boolean {
    // Implement logic to check if the user is registered in the course
    // Return true if registered, false otherwise
    return false; 
  }

  
}


