package com.deloitte.online.todo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.online.todo.model.Todolist;
import com.deloitte.online.todo.service.TodolistService;

@RestController
@RequestMapping("/{version:v[1-9]|v[0-9]{2,}}/todolist")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TodolistController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodolistController.class);

	@Autowired
	private TodolistService toDoListService;

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> createToDoList(@PathVariable(name = "version", required = true) String version,
			@ModelAttribute Todolist todolist) {
		LOGGER.debug("Received POST Request with version {} Body {}", version, todolist);
		toDoListService.addToDoList(todolist);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<Todolist> getToDoLists() {
		return toDoListService.getAllToDoLists();
	}

	
	@GetMapping("/user/{userId}")
	public List<Todolist> getToDoListByUserId(@PathVariable(value = "userId") Integer userId) {
		return toDoListService.getToDoListByUserId(userId);
	}
	
	
	@DeleteMapping("/{todolistId}")
	public Map<String, Boolean> deleteToDoList(@PathVariable(value = "todolistId") Integer todolistId) {
		return toDoListService.deleteTodoList(todolistId);
	}

	@PutMapping("/{todolistId}")
	public ResponseEntity<Todolist> updateToDoList(@PathVariable(value = "todolistId") Integer todolistId,
			@RequestBody Todolist todolist) {
		toDoListService.updateToDoList(todolist, todolistId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
