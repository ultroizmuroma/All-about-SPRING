package ru.barinov.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.barinov.server.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
  Iterable<Task> findAllByActiveTrueOrderByIdDesc();
}
