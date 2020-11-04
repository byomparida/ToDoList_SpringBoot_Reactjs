package com.deloitte.online.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deloitte.online.todo.entity.TodolistEntity;

public interface TodolistRepository extends JpaRepository<TodolistEntity, Integer> {

	Optional<TodolistEntity> findByTodolistId(Integer toDoListId);
	
	@Query(value="SELECT * FROM tbl_todolist WHERE user_id = ?1 ORDER BY todolist_id", nativeQuery = true)
	List<TodolistEntity> findTodolistDetailsByUserId(Integer userId); 

}
