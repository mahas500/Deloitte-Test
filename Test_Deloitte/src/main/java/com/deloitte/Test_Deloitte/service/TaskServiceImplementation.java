package com.deloitte.Test_Deloitte.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.Test_Deloitte.exceptions.UserNotLoggedIn;
import com.deloitte.Test_Deloitte.models.Task;
import com.deloitte.Test_Deloitte.models.User;
import com.deloitte.Test_Deloitte.repository.TaskRepository;
import com.deloitte.Test_Deloitte.repository.UserRepository;

@Service
public class TaskServiceImplementation implements TaskService {

	@Autowired
	private UserServiceImplementation userServiceImplementation;

	@Autowired
	private TaskRepository taskRepository;

	/*
	 * 1) getUserAllTasks method will first check if user session is active or not.
	 * 2) If not it will throw UserNotLoggedIn exception. 3) It it is present it
	 * will get the user object 3) Then it will access all tasks associated with the
	 * User ID from userObject.
	 */
	public Object getUserAllTasks(String sessionId) throws UserNotLoggedIn {
		Optional<User> userObject = userServiceImplementation.getUserFromSessionID(sessionId);
		ArrayList<Task> allTasks;

		if (userObject.get().getSessionId().equals(sessionId)) {
			allTasks = taskRepository.findAllByUserId(userObject.get().getId());
		} else {
			throw new UserNotLoggedIn();
		}
		return allTasks;
	}

	/*
	 * 1) createTask method will first check if user session is active or not. 2) If
	 * not it will throw UserNotLoggedIn exception. 3) It it is present it will get
	 * the user object 4) Then it create a new task ID of type UUID and store task
	 * object in h2 database.
	 */
	@Override
	public Object createTask(String sessionId, Task task) throws UserNotLoggedIn {
		
		Optional<User> userObject = userServiceImplementation.getUserFromSessionID(sessionId);
		
		if (userObject.get().getId().equals(task.getUserId())) {
			task.setId(UUID.randomUUID().toString());
			task = taskRepository.save(task);
			return task;
		} else {
			throw new UserNotLoggedIn();
		}
	}

	/*
	 * 1) editTask method will first check if user session is active or not. 2) If
	 * not it will throw UserNotLoggedIn exception. 3) It it is present it will get
	 * the user object 4) Then it save the task in h2 database.
	 */
	@Override
	public Object editTask(String sessionId, Task task) throws UserNotLoggedIn {

		Optional<User> userObject = userServiceImplementation.getUserFromSessionID(sessionId);

		if (userObject.get().getId().equals(task.getUserId())) {

			task = taskRepository.save(task);
			return task;

		} else {
			throw new UserNotLoggedIn();
		}

	}

	/*
	 * 1) deleteTask method will first check if user session is active or not. 2) If
	 * not it will throw UserNotLoggedIn exception. 3) It it is present it will get
	 * the user object 4) Then it will delete the task from h2 database.
	 */
	@Override
	public Task deleteTask(String sessionId, Task task) throws UserNotLoggedIn {

		Optional<User> userObject = userServiceImplementation.getUserFromSessionID(sessionId);
		if (userObject.get().getId().equals(task.getUserId())) {
			taskRepository.deleteById(task.getId());
			return null;
		} else {
			throw new UserNotLoggedIn();
		}
	}

}
