package ru.barinov.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.barinov.server.model.Task;
import ru.barinov.server.repository.TaskRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    return repository.findAllByActiveTrue();
  }

  @GetMapping(path = "/task")
  public @ResponseBody Task getTask(@RequestParam Long id) {
    return repository.findOne(id);
  }

  @PostMapping(path = "/task")
  public @ResponseBody String addTask(@RequestParam String name,
                                      @RequestParam String description,
                                      @RequestParam String endDate,
                                      @RequestParam Long customerId) {
    Task task = new Task();
    task.setTaskName(name);
    task.setDescription(description);
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    try {
      task.setEndDate(format.parse(endDate));
    } catch (ParseException e) {
      return e.getMessage();
    }
    task.setCustomerId(customerId);
    task.setActive(true);
    repository.save(task);
    return "SUCCESS";
  }

  @PutMapping(path = "/task")
  public @ResponseBody String updateTask(@RequestParam Long id,
                                         @RequestParam String name,
                                         @RequestParam String description,
                                         @RequestParam String endDate,
                                         @RequestParam Boolean active) {
    Task task = repository.findOne(id);
    task.setTaskName(name);
    task.setDescription(description);
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    try {
      task.setEndDate(format.parse(endDate));
    } catch (ParseException e) {
      return e.getMessage();
    }
    task.setActive(active);
    repository.save(task);
    return "SUCCESS";
  }

  @DeleteMapping(path = "/task")
  public @ResponseBody String deleteTask(@RequestParam Long id) {
    repository.delete(id);
    return "SUCCESS";
  }
}