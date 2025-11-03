import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  imports: [],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit{


  constructor(private router:Router ,public userService: UserService){
  }

  ngOnInit(): void {
    if(!this.userService.token)
      this.router.navigate(["/login"])
    this.userService.getUsername()
  }
  logout(){
    this.userService.logout()
    this.router.navigate(["/login"])
  }


}
