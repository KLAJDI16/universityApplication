import { Component, Input } from '@angular/core';
import { ApiService } from '../api.service';
import { CommonModule } from '@angular/common';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { AllCoursesComponent } from '../all-courses/all-courses.component';
import { FeedbackComponent } from '../feedback/feedback.component';

@Component({
  selector: 'app-list-of-feedbacks',
  standalone: true,
  imports: [CommonModule, NgbRatingModule, AllCoursesComponent, FeedbackComponent],
  templateUrl: './list-of-feedbacks.component.html',
  styleUrl: './list-of-feedbacks.component.css'
})
export class ListOfFeedbacksComponent {
  @Input() courseID: any;
  userHasCommentedBefore: boolean = false;
  feedbackList:any=[];

 
  constructor(private apiService: ApiService) {}

  ngOnInit() {
    this.showListOfFeedbacks();
  }

  showListOfFeedbacks() { 
    this.apiService.viewFeedbacks(this.courseID).subscribe((response: any) => {
      console.log(response);
    });
  }
}
