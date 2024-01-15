import { Component, Input, } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiService } from '../api.service';
import { NgbRatingModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSliderModule} from '@angular/material/slider';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css'],
  imports: [NgbRatingModule,MatSliderModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule],
  standalone: true,
})

export class FeedbackComponent {
  @Input() courseID:any;
  
  public feedbackForm: FormGroup = this.formBuilder.group({
    rating: new FormControl(0, Validators.required),
    description: new FormControl('', Validators.required),
  });

  constructor(private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,
    private apiService: ApiService) {}

  submitFeedback(): void {
    if (this.feedbackForm.valid) {

      const currentDate = new Date();
      const timestamp = currentDate.getTime(); 
      const isoStringDate = currentDate.toISOString(); 

      const feedbackData = {
        courseId: this.courseID,
        rating: this.feedbackForm.get('rating')?.value,
        description: this.feedbackForm.get('description')?.value,
        timestamp: timestamp,
        isoStringDate: isoStringDate,
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
 GetRating(){
  console.log(this.feedbackForm.value.rating);
 }
  
}
