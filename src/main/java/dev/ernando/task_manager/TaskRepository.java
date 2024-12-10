package dev.ernando.task_manager;

import dev.ernando.task_manager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer > {
}
