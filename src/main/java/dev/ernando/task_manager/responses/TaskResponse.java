package dev.ernando.task_manager.responses;

public record TaskResponse(Integer id, String name, String description, StatusTaskEnum status) {
}
