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
  feedbackList1:any=[];

  feedbackList = [
    { userName: 'User1', rating: 4, description: 'Great course!', stringDate: '2023-01-01' },
    { userName: 'User2', rating: 5, description: 'Excellent content.', stringDate: '2023-01-02' },
    { userName: 'User3', rating: 3, description: 'Needs improvement.', stringDate: '2023-01-03' },
  ];

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
