import { Injectable } from '@angular/core'
import { Task } from './tasks/task';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import {  } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class TasksService {
  private taskListUrl = "http://localhost:8080/tasklist";
  private taskUrl = "http://localhost:8080/task";

  constructor(private http: HttpClient) {}

  getTasks(): Observable<Task[]> {
    return this.http.get<Task[]>(this.taskListUrl);
  }

  addTask(task: Task): Observable<Task> {
    return this.http.post<Task>(this.taskUrl, task, httpOptions);
  }

  deleteTask(id: number): Observable<string> {
    const url = `${this.taskUrl}?id=${id}`;
    return this.http.delete<string>(url, httpOptions);
  }
}
