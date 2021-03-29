package com.deloitte.Test_Deloitte.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;

/*
 * Task Model class contains parameters such as id, task name, user Id,
 * createdOn and UpdatedOn details along with getters and setters methods
 * */
@Entity
public class Task {

	@Id
	private String id;

	@NotNull
	private String taskName;

	@Column(columnDefinition = "TEXT")
	private String details;

	@NotNull
	private String userId;

	private boolean complete;

	@Column(name = "createdOn", updatable = false)
	@CreationTimestamp
	private LocalDateTime createdOn;

	@Column
	@UpdateTimestamp
	private LocalDateTime updatedOn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", details=" + details + ", userId=" + userId
				+ ", complete=" + complete + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}

	
}