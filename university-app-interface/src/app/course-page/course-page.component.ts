import { Component } from '@angular/core';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-page',
  standalone: true,
  imports: [],
  templateUrl: './course-page.component.html',
  styleUrl: './course-page.component.css'
})
export class CoursePageComponent {

  course!:any;
  constructor(private apiService: ApiService, private router: Router,) {}

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


