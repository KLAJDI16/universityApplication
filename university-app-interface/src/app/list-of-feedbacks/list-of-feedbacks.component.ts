import { Component, Input } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-list-of-feedbacks',
  standalone: true,
  imports: [],
  templateUrl: './list-of-feedbacks.component.html',
  styleUrl: './list-of-feedbacks.component.css'
})
export class ListOfFeedbacksComponent {
  @Input() courseID: any;
  userHasCommentedBefore: boolean = false;

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
