import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'

})


//DARIUS SI IMO
//DACA SUNTETI AICI IMI PARE RAU, NU AVETI CE CAUTA
//
//
//ASK BEFORE MODIFYING

export class AuthService {

  public apiUrl = 'http://localhost:8080';
  private http = inject(HttpClient)

  constructor() { }


  public register(username: string, email: string, password: string): Observable<any> {
    const body = { username, email, password };
    return this.http.post(`${this.apiUrl}/auth/register`, body);
  }



  public login(username: string, password: string): Observable<any> {
    const body = { username, password };
    return this.http.post<{ token: string }>(`${this.apiUrl}/auth/login`, body);
  }




}
