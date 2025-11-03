import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { UserService } from '../user.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  imports: [FormsModule],
  templateUrl: './register-form.component.html',
  styleUrl: './register-form.component.css'
})
export class RegisterFormComponent implements OnInit{

  username:string = ""
  email:string = ""
  password:string = ""

  constructor(private router:Router,private authService: AuthService, private userService: UserService){}

  ngOnInit(): void {
      if(this.userService.token)
        this.router.navigate(["/profile"])
  }

  registerForm() {
    this.authService.register(this.username, this.email, this.password).subscribe({
      next: res => {
        this.userService.saveToken(res.token)
        this.router.navigate(["/profile"])
      },
      error: err => console.error('Register error:', err)
    });
  }


}
