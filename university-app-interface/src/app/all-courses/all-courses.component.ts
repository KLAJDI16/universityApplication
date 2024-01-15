import { Component, Input, Output, ViewChild } from '@angular/core';
import { NavBarComponent } from '../nav-bar/nav-bar.component';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';
import { MatAccordion, MatExpansionModule} from '@angular/material/expansion';
import { CommonModule } from '@angular/common';
import { SearchBarComponent } from '../search-bar/search-bar.component';

@Component({
  selector: 'app-all-courses',
  standalone: true,
  imports: [NavBarComponent, MatExpansionModule, CommonModule, SearchBarComponent,],
  templateUrl: './all-courses.component.html',
  styleUrl: './all-courses.component.css'
})
export class AllCoursesComponent {
  @ViewChild(MatAccordion) accordion!: MatAccordion;

  courses: any [] = [];
  panelOpenState = false;

  constructor(private apiService: ApiService,  private router: Router) {}

  @Output() allCourses = [
    { id: 1, courseName: 'Analize', description: 'Description 1' },
    { id: 2, courseName: 'Algjeber', description: 'Description 2' },
    { id: 3, courseName: 'Course 3', description: 'Description 3' },
  ];

  @Input() filteredCourses:any = [];

  ngOnInit(): void {
    this.getCourses();
  }

  getCourses(): void {
    
    this.apiService.listAllCourses().subscribe((courses) => {
      this.courses = courses;
    });
  }
 
  openCourseDetails(course: any) {
    this.router.navigate(['/course-page', course.id]); 
  }

  onFilteredCourses(filteredCourses: any[]): void {
    this.filteredCourses = filteredCourses;
  }
}
