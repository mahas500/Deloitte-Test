import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToDoListService } from '../service/to-do-list-service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  model: any = { };
  constructor(private toDoListService: ToDoListService,
  private router: Router) { }

  ngOnInit(): void {
  }
  createUser(form){

    const postdata = {
      "emailAddress": this.model.emailAddress,
      "password": this.model.password,
      "username": this.model.username,
      "firstName": this.model.firstname,
      "lastName": this.model.lastname
    }

    this.toDoListService.createUser(postdata).subscribe(

      (data: any[]) => {
        if(this.toDoListService.isServiceSuccesfull(data)){
          form.reset();
          this.router.navigate(['login']);
        }else{
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
