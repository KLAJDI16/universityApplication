import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css'],
})
export class FeedbackComponent {
  
  public feedbackForm: FormGroup = this.formBuilder.group({
    rating: new FormControl(0, Validators.required),
    description: new FormControl('', Validators.required),
  });

  constructor(private formBuilder: FormBuilder,
    private snackBar: MatSnackBar,) {}

  submitFeedback(): void {
    if (this.feedbackForm.valid) {
      const currentDate = new Date();
      const timestamp = currentDate.getTime(); 
      const isoStringDate = currentDate.toISOString(); 

      const feedbackData = {
        rating: this.feedbackForm.get('rating')?.value,
        description: this.feedbackForm.get('description')?.value,
        timestamp: timestamp,
        isoStringDate: isoStringDate,
      };

      console.log('Feedback Submitted:', feedbackData);
      // Add logic to send data to the backend (e.g., using a service)
    } else {
      this.snackBar.open('Please fill in all required fields!', 'Close', {
        duration: 3000,
     });
    }
  }
}
