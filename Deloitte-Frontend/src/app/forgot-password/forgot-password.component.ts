import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToDoListService } from '../service/to-do-list-service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  model: any = {};
  constructor(private toDoListService: ToDoListService,
  private router: Router) { }

  ngOnInit() {
  }

  passwordReset(form) {
   
    const postdata = {
      "username": this.model.username,
      "emailAddress": this.model.emailAddress,
      "password": this.model.password
    }

    this.toDoListService.passwordReset(postdata).subscribe(

      (data:any) => {
        if (this.toDoListService.isServiceSuccesfull(data)) {
          form.reset();

          this.router.navigate(['login']);
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
  navigateToLogin(){
    this.router.navigate(['login']);
  }
 

}
