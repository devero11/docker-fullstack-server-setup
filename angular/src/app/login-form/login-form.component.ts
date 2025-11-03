import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  imports: [FormsModule],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent implements OnInit{

  username:string = ""
  password:string = ""


  constructor(private authService: AuthService, private userService: UserService, private router:Router){}
  ngOnInit(): void {
      if(this.userService.token)
        this.router.navigate(["/profile"])
  }
  loginForm() {
    this.authService.login(this.username, this.password).subscribe({
      next: res => {
        this.userService.saveToken(res.token)
        this.router.navigate(["/profile"])
      },
      error: err => console.error('Login error:', err)
    });
  }


}
