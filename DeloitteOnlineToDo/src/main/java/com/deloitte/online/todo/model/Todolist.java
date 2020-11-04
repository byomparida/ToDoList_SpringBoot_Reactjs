package com.deloitte.online.todo.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todolist {
	private Integer todolistId;
	private Integer userId;
	private String todolistType;
	private String todolistName;
	private LocalDateTime createdTime;
	private LocalDateTime updatedTime;
}
