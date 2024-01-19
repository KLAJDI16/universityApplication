import { Component, Input, OnInit} from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder, FormsModule } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiService } from '../api.service';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSliderModule } from '@angular/material/slider';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css'],
  imports: [NgbRatingModule, MatSliderModule, ReactiveFormsModule,
    FormsModule, MatFormFieldModule, MatInputModule],
  standalone: true,
})

export class FeedbackComponent implements OnInit {
  @Input() courseID: any;

  public feedbackForm: FormGroup = this.formBuilder.group({
    rating: new FormControl(0, Validators.min(1)),
    description: new FormControl('', Validators.required),
  });

  constructor(private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private apiService: ApiService) {

      this.feedbackForm.valueChanges.subscribe(next => {
        console.log(next);
        
      })
     }

  ngOnInit(): void {}

  submitFeedback(): void {
    if (this.feedbackForm.valid) {

      const feedbackData = {
        courseId: this.courseID,
        rating: this.feedbackForm.get('rating')?.value,
        description: this.feedbackForm.get('description')?.value,
      };


      this.apiService.leaveFeedback(feedbackData).subscribe((response: any) => {

        console.log('Feedback submitted successfully:', response);
      });

    } else {
      this.snackBar.open('Please fill in all required fields!', 'Close', {
        duration: 3000,
      });
    }
  }
  

}
