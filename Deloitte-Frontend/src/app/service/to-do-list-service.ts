import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ToDoListService {

  constructor(private httpClient: HttpClient) { }
  private BASE_URL = "http://localhost:8080/Deloitte/";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unknown error!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }


  public isServiceSuccesfull(data) {
    if(data.statusCode=="OKAY"){
      return true;
    }else{
      return false;
    }
  }

  public createUser(postData) {
    return this.httpClient.post(this.BASE_URL + "createUser", JSON.stringify(postData), this.httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }

  public loginUser(postData) {
    return this.httpClient.post(this.BASE_URL + "loginUser", JSON.stringify(postData), this.httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }

  public passwordReset(postData) {
    return this.httpClient.post(this.BASE_URL + "forgotPassword", JSON.stringify(postData), this.httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }
  public getUserAllTasks(sessionId) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'sessionId':sessionId
      })
    }

    return this.httpClient.get(this.BASE_URL + "getUserAllTasks", httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }

  public createTask(postData,sessionId, APIUrl) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'sessionId':sessionId
      })
    }
    return this.httpClient.post(this.BASE_URL + APIUrl, JSON.stringify(postData), httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }

  public deleteTask(postData,sessionId) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'sessionId':sessionId
      })
    }
    return this.httpClient.post(this.BASE_URL + "deleteTask", JSON.stringify(postData), httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }

  public deleteAccount(sessionId) {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'sessionId':sessionId
      })
    }
    return this.httpClient.delete(this.BASE_URL + "deleteUserData", httpOptions)
      .pipe(
        catchError(this.handleError)
      )
  }
}
