import { Component, } from '@angular/core';
import { ApiService } from '../api.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FeedbackComponent } from '../feedback/feedback.component';
import { ListOfFeedbacksComponent } from '../list-of-feedbacks/list-of-feedbacks.component';
import { NavBarComponent } from '../nav-bar/nav-bar.component';
import { MatDividerModule } from '@angular/material/divider';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-course-page',
  standalone: true,
  imports: [FeedbackComponent, MatDividerModule, ListOfFeedbacksComponent,
    CommonModule, NavBarComponent],
  templateUrl: './course-page.component.html',
  styleUrl: './course-page.component.css'
})

export class CoursePageComponent {

  course!: any;

  constructor(private apiService: ApiService, private router: Router,
    private route: ActivatedRoute) { }


  ngOnInit() {
    this.fetchCourse();
  }

  fetchCourse() {
    this.route.queryParams.subscribe(params => {

      this.course = JSON.parse(params['course']);
    });
  }

  enroll() {
    const courseName = this.course.name; 
    const user= this.apiService.getUser();

    this.apiService.joinCourse(user!,courseName).subscribe(response => {
      console.log(response);
    });
  }
  
  unenroll() {
    const courseName = this.course.name; 
    const user= this.apiService.getUser();

    this.apiService.dropCourse(user!,courseName).subscribe(response => {
       console.log(response);
    });
  }
  
 
  isRegistered(course: any): boolean {
    const username= this.apiService.getUser();

    let registered=false;
    this.apiService.userCourses(username).subscribe((userCourses) => {
      if(userCourses === course){
        registered=true;
      }
    });
    console.log(registered);
    return registered;
  }

}


