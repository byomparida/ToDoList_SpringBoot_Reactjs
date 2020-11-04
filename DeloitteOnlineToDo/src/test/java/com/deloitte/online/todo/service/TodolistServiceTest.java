package com.deloitte.online.todo.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.deloitte.online.todo.component.PopulateTodolist;
import com.deloitte.online.todo.repository.TodolistRepository;


@DataJpaTest
@RunWith(SpringRunner.class)
public class TodolistServiceTest {
	
	@InjectMocks
	private TodolistService todolistService;
	
	@Mock
	private TodolistRepository todolistRepository;
	
	@InjectMocks
	private PopulateTodolist populateToDoList;
	
	@Before
	public void setup() {
		todolistService = new TodolistService();
		populateToDoList = new PopulateTodolist();
//		ReflectionTestUtils.setField(todolistService, "todolistRepository", todolistRepository);
		ReflectionTestUtils.setField(todolistService, "populateToDoList", populateToDoList);
	}
}
