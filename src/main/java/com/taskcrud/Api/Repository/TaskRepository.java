package com.taskcrud.Api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskcrud.Api.Model.Task;

public interface TaskRepository extends JpaRepository <Task, Long>{
    List<Task> findByCategoria(String categoria);
} 
