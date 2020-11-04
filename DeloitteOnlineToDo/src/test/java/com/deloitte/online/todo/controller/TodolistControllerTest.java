package com.deloitte.online.todo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StreamUtils.copyToString;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.online.todo.model.Todolist;
import com.deloitte.online.todo.service.TodolistService;

@RunWith(SpringRunner.class)
@EnableSpringDataWebSupport
@WebMvcTest(value = TodolistController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { TodolistController.class })
public class TodolistControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodolistService todolistService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Ignore
	public void test_create_todo_list() throws Exception {

		Todolist todolist = getToDoListDetails();
		when(todolistService.addToDoList(todolist)).thenReturn(todolist);
		String todoListJson = copyToString(this.getClass().getClassLoader().getResourceAsStream("todolist.json"),
				Charset.defaultCharset());
		mockMvc.perform(post("/{version}/todolist", "v1").contentType("application/json").content(todoListJson))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void test_should_return_empty_todo_list() throws Exception {
		when(todolistService.getAllToDoLists()).thenReturn(new ArrayList<>());
		mockMvc.perform(get("/{version}/todolist", "v1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void test_should_return_todo_list() throws Exception {

		Todolist todolist = getToDoListDetails();
		List<Todolist> lst = new ArrayList<>();
		lst.add(todolist);

		when(todolistService.getAllToDoLists()).thenReturn(lst);
		mockMvc.perform(get("/{version}/todolist", "v1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void test_should_return_todo_list_by_userid() throws Exception {

		Todolist todolist = getToDoListDetails();
		List<Todolist> lst = new ArrayList<>();
		lst.add(todolist);

		when(todolistService.getToDoListByUserId(1)).thenReturn(lst);
		mockMvc.perform(get("/{version}/todolist", "v1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	@WithMockUser
	public void test_should_expect_400_errors_on_post_request_todolist() throws Exception {
		String todoListJson = copyToString(this.getClass().getClassLoader().getResourceAsStream("todolist.json"),
				Charset.defaultCharset());
		mockMvc.perform(post("/todolist").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(todoListJson)).andExpect(status().is4xxClientError());
	}

	@Test
	@Ignore
	public void test_delete_to_do_list() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/todolist").param("todolistId", "11")
				.header("X-Frame-Options", "SAMEORIGIN").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void test_delete_to_do_list_400_error() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders.delete("/todolist").param("todolistId", "11")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	private Todolist getToDoListDetails() {
		Todolist todolist = new Todolist();
		todolist.setUserId(1);
		todolist.setTodolistId(1);
		todolist.setTodolistName("School");
		todolist.setTodolistType("Collect Kids");
		return todolist;
	}
}
