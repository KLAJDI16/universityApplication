import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpClientModule } from '@angular/common/http';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [CommonModule, MatCardModule, ReactiveFormsModule,
    MatFormFieldModule, MatInputModule, MatButtonModule, RouterModule, HttpClientModule],
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent {
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private snackBar: MatSnackBar, public apiService: ApiService,
    private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onLogin() {
    this.router.navigate(['/all-courses']);

    // if (!this.loginForm.valid){
    //   this.openSnackBar();
    // }
    // else {
    //   this.login();
    // }
    // console.log(this.loginForm.value);
  }
  login() {
    
    const { username, password } = this.loginForm.value;
    const data = { username, password };

    this.apiService.login(data).subscribe(
      (response:any)  => {
      console.log('Login response:', response);
    });
  }


  openSnackBar() {
      this.snackBar.open('Please fill in all required fields.', 'Close', {
        duration: 2000,
      });
  }
  
}