package com.vermouth.service.repository;

import com.vermouth.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {
}