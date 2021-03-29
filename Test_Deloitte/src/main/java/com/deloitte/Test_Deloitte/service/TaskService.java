package com.deloitte.Test_Deloitte.service;

import java.util.ArrayList;

import com.deloitte.Test_Deloitte.exceptions.UserNotLoggedIn;
import com.deloitte.Test_Deloitte.models.Task;
import com.deloitte.Test_Deloitte.models.User;

/*
 * TaskService interface containing abstract methods which are implemented in TaskServiceImplementation class
 * */
public interface TaskService {

	Object getUserAllTasks(String sessionId) throws UserNotLoggedIn;

	Object createTask(String sessionId, Task task) throws UserNotLoggedIn;

	Object editTask(String sessionId, Task task) throws UserNotLoggedIn;

	Task deleteTask(String sessionId, Task task) throws UserNotLoggedIn;

}
