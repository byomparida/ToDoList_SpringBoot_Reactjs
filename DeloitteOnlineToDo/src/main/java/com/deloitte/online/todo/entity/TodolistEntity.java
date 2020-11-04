package com.deloitte.online.todo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_todolist")
public class TodolistEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer todolistId;
	private Integer userId;
	private String todolistType;
	private String todolistName;
	
	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	private LocalDateTime createdTime;

	@Column(updatable = false, nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedTime;

}
