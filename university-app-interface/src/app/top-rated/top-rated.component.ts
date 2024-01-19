import { Component } from '@angular/core';
import { NavBarComponent } from '../nav-bar/nav-bar.component';
import { ApiService } from '../api.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-top-rated',
  standalone: true,
  imports: [NgbModule, MatCardModule, NavBarComponent, CommonModule],
  templateUrl: './top-rated.component.html',
  styleUrl: './top-rated.component.css'
})
export class TopRatedComponent {

  topCourses: any[] = [];
  // topCourses = [
  //   { id: 1, courseName: 'Course 1', rating: 4 },
  //   { id: 2, courseName: 'Course 2', rating: 3},
  //   { id: 3, courseName: 'Course 3', rating: 5 },
  //   { id: 4, courseName: 'Course 4', rating: 2 },
  //   { id: 5, courseName: 'Course 5', rating: 4 },
  //   { id: 6, courseName: 'Course 6', rating: 3 },
  //   { id: 7, courseName: 'Course 7', rating: 4 },
  //   { id: 8, courseName: 'Course 8', rating: 3 }
  // ];

  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.fetchTopCourses();
  }

  fetchTopCourses() {
    this.apiService.topCourses(8).subscribe((courses: any[]) => {
      this.topCourses = courses;
    });
  }
}
