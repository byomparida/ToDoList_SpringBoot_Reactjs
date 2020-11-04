package com.deloitte.online.todo.component;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.deloitte.online.todo.entity.TodolistEntity;
import com.deloitte.online.todo.model.Todolist;

@RunWith(SpringRunner.class)
public class PopulateTodolistTest {

	@InjectMocks
	private PopulateTodolist populateTodolist;

	@Test
	public void test_populate_todolist() {
		TodolistEntity entity = populateTodolist.populateToDoListEntity(populateToDoList());
		assertThat(entity.getUserId()).isEqualTo(1);
		assertThat(entity.getTodolistId()).isEqualTo(1);
		assertThat(entity.getTodolistName()).isEqualTo("Any Todo List Name");
		assertThat(entity.getTodolistType()).isEqualTo("Any Todo Type");
	}
	
	@Test
	public void test_populate_todoentitylist() {
		Todolist todolist = populateTodolist.populateToDoList(toDoListEntity());
		assertThat(todolist.getUserId()).isEqualTo(1);
		assertThat(todolist.getTodolistId()).isEqualTo(1);
		assertThat(todolist.getTodolistName()).isEqualTo("Any Todo List Name");
		assertThat(todolist.getTodolistType()).isEqualTo("Any Todo Type");
	}

	private TodolistEntity toDoListEntity() {
		TodolistEntity tde = new TodolistEntity();
		tde.setUserId(1);
		tde.setTodolistId(1);
		tde.setTodolistName("Any Todo List Name");
		tde.setTodolistType("Any Todo Type");
		return tde;
	}

	private Todolist populateToDoList() {
		Todolist td = new Todolist();
		td.setUserId(1);
		td.setTodolistId(1);
		td.setTodolistName("Any Todo List Name");
		td.setTodolistType("Any Todo Type");
		return td;
	}
}
