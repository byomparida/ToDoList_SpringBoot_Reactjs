package com.deloitte.online.todo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.online.todo.component.PopulateTodolist;
import com.deloitte.online.todo.entity.TodolistEntity;
import com.deloitte.online.todo.model.Todolist;
import com.deloitte.online.todo.repository.TodolistRepository;

@Service
public class TodolistService {

	private static final Logger LOG = LoggerFactory.getLogger(TodolistService.class);
	
	@Autowired
	private PopulateTodolist populateToDoList;
	
	@Autowired
	private TodolistRepository toDoListRepository;
	
	public Todolist addToDoList(Todolist toDoList) {
		LOG.debug("addToDoList method called.");
		TodolistEntity toDoListModel = populateToDoList.populateToDoListEntity(toDoList);
		return populateToDoList.populateToDoList(toDoListRepository.save(toDoListModel));
	}
	
	public List<Todolist> getAllToDoLists() {
		List<Todolist> toDoLists = new ArrayList<>();
		toDoListRepository.findAll().forEach(toDo -> toDoLists.add(populateToDoList.populateToDoList(toDo)));
		return toDoLists;
	}
	
	public List<Todolist> getToDoListByUserId(Integer userId) {
		List<Todolist> toDoLists = new ArrayList<>();
		toDoListRepository.findTodolistDetailsByUserId(userId).forEach(toDo -> toDoLists.add(populateToDoList.populateToDoList(toDo)));
		return toDoLists;
	}

	
	public Optional<Todolist> getToDoListByToDoListId(Integer todoListId) {
		return Optional.of(populateToDoList.populateToDoList(toDoListRepository.findById(todoListId)
				.orElseThrow(() -> new EntityNotFoundException("TodoList not found"))));
	}
	
	public Map<String, Boolean> deleteTodoList(Integer todoListId) {
		Optional<Todolist> todolist = getToDoListByToDoListId(todoListId);
		TodolistEntity toDoListModel = populateToDoList.populateToDoListEntity(todolist.get());
		toDoListRepository.delete(toDoListModel);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	public Todolist updateToDoList(Todolist toDoList, Integer todolistId) {
		LOG.debug("updateToDoList method called.");
		Todolist tmpToDoList  = getToDoListByToDoListId(todolistId).get();
		tmpToDoList.setTodolistId(todolistId);
		tmpToDoList.setTodolistName(toDoList.getTodolistName());
		tmpToDoList.setTodolistType(toDoList.getTodolistType());
		TodolistEntity toDoListModel = populateToDoList.populateToDoListEntity(tmpToDoList);
		return populateToDoList.populateToDoList(toDoListRepository.save(toDoListModel));
	}

}
