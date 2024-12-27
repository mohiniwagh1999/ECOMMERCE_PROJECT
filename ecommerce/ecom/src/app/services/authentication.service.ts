import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private loginUrl = 'http://localhost:9090/find-customer'; // Replace with your backend login URL

  private forgotPasswordUrl='http://localhost:9090/find-customer-by-email/';

  private resetPasswordUrl='http://localhost:9091/reset-password';

  constructor(private http: HttpClient) { }


  login(email: string, password: string): Observable<any> {
    const body = { email, password };
    console.log(body.email)
    return this.http.post<any[]>(this.loginUrl, body);
  }

  sendPasswordResetLink(email: string): Observable<any> {
    console.log("forgot-password email: "+ email);
    return this.http.post(`${this.forgotPasswordUrl}${email}`, {});
  }

  resetPassword(newPassword: string, token: string): Observable<any> {
    console.log("reset password page:"+token)
    return this.http.post(this.resetPasswordUrl, { newPassword, token });
  }
}
}