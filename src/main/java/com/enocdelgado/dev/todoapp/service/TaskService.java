package com.enocdelgado.dev.todoapp.service;


import com.enocdelgado.dev.todoapp.exceptions.ToDoExceptions;
import com.enocdelgado.dev.todoapp.mapper.TaskInDTOToTask;
import com.enocdelgado.dev.todoapp.persistence.entity.Task;
import com.enocdelgado.dev.todoapp.persistence.entity.TaskStatus;
import com.enocdelgado.dev.todoapp.persistence.reposiory.TaskRepository;
import com.enocdelgado.dev.todoapp.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Create task
    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    // Get task
    public List<Task> findAll() {
        return this.repository.findAll();
    }

    // Get all by
    public List<Task> findAllByTaskStatus(TaskStatus status) {
        return this.repository.findAllByTaskStatus(status);
    }

    // Mark as finished
    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask =  this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinidshed(id);
    }

    // Delete a task
    public void deleteById(Long id) {
        Optional<Task> optionalTask =  this.repository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);

    }
}
