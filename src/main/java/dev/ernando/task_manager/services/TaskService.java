package dev.ernando.task_manager.services;

import dev.ernando.task_manager.TaskRepository;
import dev.ernando.task_manager.exceptions.TaskNotFoundException;
import dev.ernando.task_manager.model.Task;
import dev.ernando.task_manager.requests.TaskRequest;
import dev.ernando.task_manager.responses.StatusTaskEnum;
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

    public TaskResponse createTask(TaskRequest request) {
        Task  task  = toEntity(request);
        task = taskRepository.save(task);
        return toResponse(task);
    }

    public void updateTask(TaskRequest request, Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
        task.setName(request.name());
        task.setDescription(request.description());
        taskRepository.save(task);
    }

    public void deleteTask(Integer taskId) {
        taskRepository.deleteById(taskId);
    }

    public TaskResponse findById(Integer id) {
        return taskRepository.findById(id).map(this::toResponse).orElseThrow(TaskNotFoundException::new);
    }

    private Task toEntity(TaskRequest request) {
        Task task = new Task();
        task.setDescription(request.description());
        task.setName(request.name());
        task.setStatus(StatusTaskEnum.PENDING);
        return task;
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId(), task.getName(), task.getDescription(), task.getStatus());
    }
}
