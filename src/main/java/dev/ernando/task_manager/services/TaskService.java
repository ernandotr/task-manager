package dev.ernando.task_manager.services;

import dev.ernando.task_manager.TaskRepository;
import dev.ernando.task_manager.model.Task;
import dev.ernando.task_manager.responses.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<TaskResponse> getAllTasks(){
        return  taskRepository.findAll().stream().map(this::toResponse).toList();
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId(), task.getName(), task.getDescription(), task.getStatus());
    }
}
