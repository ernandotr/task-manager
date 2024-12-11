package dev.ernando.task_manager.controllers;

import dev.ernando.task_manager.requests.TaskRequest;
import dev.ernando.task_manager.responses.StatusTaskEnum;
import dev.ernando.task_manager.responses.TaskResponse;
import dev.ernando.task_manager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        var tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        TaskResponse response = taskService.createTask(taskRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
