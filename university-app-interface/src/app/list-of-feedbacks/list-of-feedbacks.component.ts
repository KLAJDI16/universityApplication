import { Component } from '@angular/core';

@Component({
  selector: 'app-list-of-feedbacks',
  standalone: true,
  imports: [],
  templateUrl: './list-of-feedbacks.component.html',
  styleUrl: './list-of-feedbacks.component.css'
})
export class ListOfFeedbacksComponent {
 // @Input() feedbackList: Feedback[] = [];
  // merri nga backu si stream te dhenash
  userHasCommentedBefore: boolean = false;
 
}
interface Feedback {
  userName: string;
  rating: number;
  description: string;
  stringDate: string;
}