package com.enocdelgado.dev.todoapp.controller;

import com.enocdelgado.dev.todoapp.persistence.entity.Task;
import com.enocdelgado.dev.todoapp.persistence.entity.TaskStatus;
import com.enocdelgado.dev.todoapp.service.TaskService;
import com.enocdelgado.dev.todoapp.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Method to create a task
    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO) {
        return this.taskService.createTask(taskInDTO);
    }

    @GetMapping
    public List<Task> findAll() {
        return this.taskService.findAll();
    }

    // Update task
    @GetMapping("/status/{status}")
    public List<Task> findAllbyStatus(@PathVariable("status")TaskStatus status) {
        return this.taskService.findAllByTaskStatus(status);
    }

    // HTTP verb to update the params
    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id) {
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    // Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
