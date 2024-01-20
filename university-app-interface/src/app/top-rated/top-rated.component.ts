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
