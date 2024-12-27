import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-forgotpassword',
  standalone: true,
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './forgotpassword.component.html',
  styleUrl: './forgotpassword.component.css'
})
export class ForgotpasswordComponent {

  forgotPasswordForm!: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(private fb: FormBuilder, private authService: AuthenticationService) {}

  ngOnInit(): void {
    this.forgotPasswordForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]]  // Validates email input
    });
  }

  onSubmit(): void {
    if (this.forgotPasswordForm.valid) {
      const email = this.forgotPasswordForm.value.email;

      // Call backend to check if email exists and send reset link
      this.authService.sendPasswordResetLink(email).subscribe(
        (response) => {

          console.log("forgot password status:"+ response);
          if (response === true) {
            // If the email exists (response is true), show success message
            this.successMessage = 'Password reset link has been sent to your email.';
            this.errorMessage = null;
          } else {
            // If the email does not exist (response is false), show error message
            this.errorMessage = 'Email not found. Please try again.';
            this.successMessage = null;
          }
        },
        (error) => {
          // Handle server errors or other issues
          this.errorMessage = 'An error occurred. Please try again later.';
          this.successMessage = null;
          console.error("Error during password reset request", error);
        }
      );
    }
  }




}
