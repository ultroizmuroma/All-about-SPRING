import { Component, OnInit } from '@angular/core';
import { Task } from './task';
import { TasksService } from '../tasks.service';
import { DatepickerService } from '../utils/datepicker/datepicker.service';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})

export class TasksComponent implements OnInit {
  taskList: Task[];
  task: Task;
  endDate: Date;

  constructor(private tasksService: TasksService, private service: DatepickerService) {}
  ngOnInit() {
    this.getTasks();
    this.service.getDate().subscribe(result => this.endDate = result,
      error => console.log("error" + error));
  }
  getTasks(): void {
    this.tasksService.getTasks()
      .subscribe(taskList => this.taskList = taskList);
  }

  addTask(taskName: string, description: string): void {
    this.task = {
      id: 0,
      customerId: 10,
      taskName: taskName,
      description: description,
      endDate: this.endDate.getTime(),
      active: true
    };
    this.tasksService.addTask(this.task)
      .subscribe(result => {
          this.taskList.push(result);
        },
        error => console.log(error));
  }

  deleteTask(task: Task): void {
    this.tasksService.deleteTask(task.id)
      .subscribe(result => this.taskList = this.taskList.filter(t => t !== task),
        error => console.log(error))
  }
}
