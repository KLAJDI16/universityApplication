import { Component } from '@angular/core';
import { NavBarComponent } from '../nav-bar/nav-bar.component';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-courses',
  standalone: true,
  imports: [NavBarComponent],
  templateUrl: './all-courses.component.html',
  styleUrl: './all-courses.component.css'
})
export class AllCoursesComponent {
  courses: any [] = [];

  constructor(private apiService: ApiService,  private router: Router) {}

  ngOnInit(): void {
    this.getCourses();
  }

  getCourses(): void {
    
    this.apiService.listAllCourses().subscribe((courses) => {
      this.courses = courses;
    });
  }
 
  openCourseDetails(course: any) {
    this.router.navigate(['/course', course.id]); 
  }

}
