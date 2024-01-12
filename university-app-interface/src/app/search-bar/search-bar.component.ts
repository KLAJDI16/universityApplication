import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ApiService } from '../api.service';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { AllCoursesComponent } from '../all-courses/all-courses.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, FormsModule,
     MatButtonModule, MatIconModule, CommonModule],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})
export class SearchBarComponent {
  constructor(public apiService: ApiService){}

  @Input() allCourses: any[] = []; 
  @Output() filteredCourses: EventEmitter<any[]> = new EventEmitter<any[]>();
  searchTerm!: string;
  
  onSearch(): void {
    const filteredCourses = this.allCourses.filter(course =>
      course.courseName.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
    this.filteredCourses.emit(filteredCourses);
  }

}
