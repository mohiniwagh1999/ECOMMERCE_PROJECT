import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-reset-password',
  standalone: true,
  imports: [FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators],
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {

  resetPasswordForm!: FormGroup;
  token!: string;
  errorMessage: string | null = null;
  successMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private authService: AuthenticationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Capture the token from the URL
    this.token = this.route.snapshot.queryParams['token'];

    // Initialize the form
    this.resetPasswordForm = this.fb.group({
      newPassword: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }

  // Custom validator to check if the passwords match
  passwordMatchValidator(form: FormGroup) {
    return form.get('newPassword')?.value === form.get('confirmPassword')?.value 
      ? null : { mismatch: true };
  }

  // Handle form submission
  onSubmit() {
    if (this.resetPasswordForm.valid) {
      const newPassword = this.resetPasswordForm.get('newPassword')?.value;

      // Send new password and token to the backend
      this.authService.resetPassword(newPassword, this.token).subscribe(
        (response) => {
          this.successMessage = 'Your password has been reset successfully.';
          this.errorMessage = null;
          //this.router.navigate(['/login']);  // Optionally, redirect to login page
        },
        (error) => {
          this.errorMessage = 'Something went wrong. Please try again.';
          this.successMessage = null;
        }
      );
    }
  }

}
