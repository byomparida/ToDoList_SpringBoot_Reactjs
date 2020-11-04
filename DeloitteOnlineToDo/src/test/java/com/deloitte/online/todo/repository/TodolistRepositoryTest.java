package com.deloitte.online.todo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deloitte.online.todo.entity.TodolistEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodolistRepositoryTest {

	@Autowired
	private TodolistRepository repository;

	private TodolistEntity todolist;

	@Before
	public void setUp() {
		todolist = new TodolistEntity();
		todolist.setTodolistId(1);
		todolist.setUserId(1);
		todolist.setTodolistName("School");
		todolist.setTodolistType("Collect Kids");
	}

	@Test
	public void test_should_expect_no_errors_on_adding_todo_list() {
		repository.save(todolist);
		assertNotNull(todolist);
		Optional<TodolistEntity> entity = repository.findByTodolistId(1);
		assertEquals(entity, Optional.empty());
	}

	@Test
	public void test_should_expect_no_errors_on_finding_todo_list() {
		repository.save(todolist);
		assertNotNull(todolist);
		Optional<TodolistEntity> entity = repository.findByTodolistId(1);
		assertEquals(entity, Optional.empty());
	}

	@Test
	public void test_should_expect_no_errors_on_finding_todo_list_by_user_id() {
		repository.save(todolist);
		assertNotNull(todolist);
		List<TodolistEntity> entity = repository.findTodolistDetailsByUserId(1);
		assertEquals(todolist.getTodolistId(), entity.get(0).getTodolistId());
		assertEquals(todolist.getUserId(), entity.get(0).getUserId());
		assertEquals(todolist.getTodolistName(), entity.get(0).getTodolistName());
		assertEquals(todolist.getTodolistType(), entity.get(0).getTodolistType());
	}
}
