import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, MatCardModule, ReactiveFormsModule,
    MatFormFieldModule, MatInputModule, MatButtonModule, RouterModule,],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent  {
  signupForm: FormGroup;

  constructor(private fb: FormBuilder, private snackBar: MatSnackBar,
     public apiService: ApiService, private router: Router ) {

    this.signupForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required,],
      confirmPassword: ['', Validators.required],
    });
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  onSignup() {
    if (!this.signupForm.valid){
      this.openSnackBar('Please fill in all required fields.', 'Close');
    }
    else this.confirmPassword();

    console.log(this.signupForm.value);
  }

  register() {
   
    const { username, password } = this.signupForm.value;
    const data = { username,password };

    this.apiService.register(data).subscribe((response:any)  => {
      console.log('Registration response:', response);

      sessionStorage.setItem('username', username);

      this.router.navigate(['/all-courses']);
    });
  }

  confirmPassword() {
    const password = this.signupForm.get('password')?.value;
    const confirmPassword = this.signupForm.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      this.openSnackBar('You did not confirm your password correctly!', 'Close');
    } else {
      this.register();
    }
  }
}
