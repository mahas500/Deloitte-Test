import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToDoListService } from '../service/to-do-list-service';

@Component({
  selector: 'app-to-do-task-list',
  templateUrl: './to-do-task-list.component.html',
  styleUrls: ['./to-do-task-list.component.css']
})
export class ToDoTaskListComponent implements OnInit {

  model: any = {};
  todoTasks;
  showform=false;
  formLabel="";
  id=null;
  firstname=localStorage.getItem("firstname");
  lastname=localStorage.getItem("lastname");
  constructor(private toDoListService: ToDoListService,
    private router: Router) { }

  ngOnInit() {
    this.getUserAllTasks();
  }

  getUserAllTasks() {

    let sessionId = localStorage.getItem("sessionId");
    this.toDoListService.getUserAllTasks(sessionId).subscribe(

      (data: any) => {
        if (this.toDoListService.isServiceSuccesfull(data)) {
          this.todoTasks = data.resultSet;

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
  element: HTMLElement;
  currentElement;
  showHideDescription(id) {
    if (this.currentElement != undefined) {
      this.currentElement.style.display = 'none';
    }

    this.element = document.getElementById(id) as HTMLElement;
    this.element.style.display = 'block';
    this.currentElement = this.element;
  }


  createTask(form) {
    let APIUrl='';
    if(this.id!=null){
      APIUrl = 'editTask';
    }else{
      APIUrl = 'createTask';
      this.id=null;
    }
    const postdata = {
      "details": this.model.details,
      "userId": localStorage.getItem('userId'),
      "taskName":this.model.taskName,
      "complete":this.model.complete,
      "id":this.id
    }

    this.toDoListService.createTask(postdata,localStorage.getItem("sessionId"),APIUrl).subscribe(

      (data: any[]) => {
        if (this.toDoListService.isServiceSuccesfull(data)) {
          form.reset();
          this.showCreateForm();
          this.getUserAllTasks();

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

  editTask(itemData){
    this.formLabel="Update";
    this.model.details = itemData.details;
    this.model.taskName = itemData.taskName;
    this.model.complete = itemData.complete;
    this.id=itemData.id;
    this.showform=!this.showform;
    this.scrollToTop();
  }

  showCreateForm(){
    this.id=null;
    this.formLabel="Create";
    this.showform=!this.showform;
    this.scrollToTop();

  }

scrollToTop(){
  window.scroll({
    top: 0,
    left: 0,
    behavior: 'smooth'
  });
}
deleteTask(id) {
    const postdata = {
      "id": id,
      "userId": localStorage.getItem('userId')
    }

    this.toDoListService.deleteTask(postdata,localStorage.getItem("sessionId")).subscribe(

      (data: any[]) => {
        if (this.toDoListService.isServiceSuccesfull(data)) {

          this.getUserAllTasks();

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
  closeForm(form){
    this.showform=!this.showform;
    form.reset();
  }

  logout(){
    localStorage.clear();
    this.router.navigate(['login']);

  }

  deleteAccount(){

    this.toDoListService.deleteAccount(localStorage.getItem("sessionId")).subscribe(

      (data: any[]) => {
        if (this.toDoListService.isServiceSuccesfull(data)) {
          this.logout();
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

}
