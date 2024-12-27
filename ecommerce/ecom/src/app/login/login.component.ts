import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule,ReactiveFormsModule,RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthenticationService,  // Inject the authentication service
    private router: Router  // Inject the Router service
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],  // Email validation
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const email = this.loginForm.value.email;
      const password = this.loginForm.value.password;
      if (email === 'admin@gmail.com' && password === 'Admin@123') {
        console.log('Admin logged in successfully');
        
        localStorage.setItem('userRole', 'admin');
        // Navigate to the admin dashboard
       // this.router.navigate(['/admin-dashboard']);
      } else {
  
      this.authService.login(email, password).subscribe(
        (response) => {
          if(response != null){
            localStorage.setItem('userRole', 'user');
            this.router.navigate(['/dashboard'], {
              queryParams: {
                orders: JSON.stringify(response) // Send the entire orders array as a query param
              }
            });
          } else{
            console.log("Invalid Credentials");
          }
          },
            
        (error) => {
          console.error('Login failed', error);
        }
      );
    }
    }
  }
  


}
