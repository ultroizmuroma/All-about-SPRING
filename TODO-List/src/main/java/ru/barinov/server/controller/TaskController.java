package ru.barinov.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.barinov.server.model.DefaultResponse;
import ru.barinov.server.model.Task;
import ru.barinov.server.repository.TaskRepository;

@Controller
public class TaskController {
  private final TaskRepository repository;

  @Autowired
  public TaskController(TaskRepository repository) {
    this.repository = repository;
  }

  @GetMapping(path = "/tasklist")
  public @ResponseBody
  Iterable<Task> getTaskList() {
    return repository.findAllByActiveTrueOrderByIdDesc();
  }

  @GetMapping(path = "/task")
  public @ResponseBody Task getTask(@RequestParam Long id) {
    return repository.findOne(id);
  }

  @PostMapping(path = "/task")
  public @ResponseBody Task addTask(@RequestBody Task requestBody) {
    Task task = new Task();
    task.setTaskName(requestBody.getTaskName());
    task.setDescription(requestBody.getDescription());
    task.setEndDate(requestBody.getEndDate());
    task.setCustomerId(requestBody.getCustomerId());
    task.setActive(true);
    repository.save(task);
    return task;//  <button (click)="addTask(taskName.value, description.value, picker.value); taskName.value = ''; description.value = '';">
  }

  @PutMapping(path = "/task")
  public @ResponseBody DefaultResponse updateTask(@RequestBody Task requestBody) {
    Task task = repository.findOne(requestBody.getId());
    task.setTaskName(requestBody.getTaskName());
    task.setDescription(requestBody.getDescription());
    task.setEndDate(requestBody.getEndDate());
    task.setActive(requestBody.getActive());
    repository.save(task);
    return new DefaultResponse("SUCCESS");
  }

  @DeleteMapping(path = "/task")
  public @ResponseBody DefaultResponse deleteTask(@RequestParam Long id) {
    repository.delete(id);
    return new DefaultResponse("SUCCESS");
  }
}