package com.enocdelgado.dev.todoapp.persistence.reposiory;

import com.enocdelgado.dev.todoapp.persistence.entity.Task;
import com.enocdelgado.dev.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Get all by status
    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    // Native request
    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED=true WHERE ID=:id", nativeQuery = true)
    public void markTaskAsFinidshed(@Param("id") Long id);
}

