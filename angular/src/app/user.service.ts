import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  public token:string|null = null
  public username:string|null = null

  http = inject(HttpClient)

  constructor(private authService: AuthService) {
    if(localStorage.getItem("token")){
      this.token = localStorage.getItem("token")
      this.getUsername()
    }
  }



  public requestUserData(): Observable<string> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${this.token}`
    });
    return this.http.get(`${this.authService.apiUrl}/user/username`, {
      headers, responseType: 'text' as const});
  }


  public getUsername() {

    this.requestUserData().subscribe({
      next: res => this.username = res,
      error: err => {console.error('Get username error:', err)
        this.logout()
      }

    });
  }



  public saveToken(token:string){
    this.token=token
    localStorage.setItem("token", token)
  }

  public logout(){
    this.username = null
    this.token = null
    localStorage.removeItem("token")
  }

}
