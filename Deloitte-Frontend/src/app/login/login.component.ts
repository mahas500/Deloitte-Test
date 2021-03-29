import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToDoListService } from '../service/to-do-list-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  model: any = {};
  constructor(private toDoListService: ToDoListService,
  private router: Router) { }

  ngOnInit() {
  }

  loginUser(form) {

    const postdata = {
      "username": this.model.username,
      "password": this.model.password
    }

    this.toDoListService.loginUser(postdata).subscribe(

      (data:any) => {
        if (this.toDoListService.isServiceSuccesfull(data)) {
          form.reset();
          localStorage.setItem("username",data.resultSet.username);
          localStorage.setItem("userId",data.resultSet.id);
          localStorage.setItem("sessionId",data.resultSet.sessionId);
          localStorage.setItem("firstname",data.resultSet.firstName);
          localStorage.setItem("lastname",data.resultSet.lastName);
          this.router.navigate(['todotasklists']);
        } else {
          alert("SOMETHING WENT WRONG");
        }


      },
      (catchError) => {
        console.log('HTTP Error', catchError);

      },
      () => {
      })
  }
  navigateToRegister(){
    this.router.navigate(['register']);
  }
  navigateToForgotPassword(){
    this.router.navigate(['forgotpassword']);
  }

}
