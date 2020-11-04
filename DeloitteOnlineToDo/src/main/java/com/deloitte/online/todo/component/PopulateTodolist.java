package com.deloitte.online.todo.component;

import org.springframework.stereotype.Component;

import com.deloitte.online.todo.entity.TodolistEntity;
import com.deloitte.online.todo.model.Todolist;

@Component
public class PopulateTodolist {

	public TodolistEntity populateToDoListEntity(final Todolist toDoList) {
    	TodolistEntity tde = new TodolistEntity();
    	tde.setUserId(toDoList.getUserId());
    	tde.setTodolistId(toDoList.getTodolistId());
    	tde.setTodolistName(toDoList.getTodolistName());
    	tde.setTodolistType(toDoList.getTodolistType());
    	tde.setCreatedTime(toDoList.getCreatedTime());
    	tde.setUpdatedTime(toDoList.getUpdatedTime());
        return  tde;
    }
    
    public Todolist populateToDoList(final TodolistEntity tde){
    	Todolist toDoList = new Todolist();
    	toDoList.setUserId(tde.getUserId());
    	toDoList.setTodolistId(tde.getTodolistId());
    	toDoList.setTodolistName(tde.getTodolistName());
    	toDoList.setTodolistType(tde.getTodolistType());
    	toDoList.setCreatedTime(tde.getCreatedTime());
    	toDoList.setUpdatedTime(tde.getUpdatedTime());
    	return  toDoList;
    }
}
