package com.deloitte.Test_Deloitte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.Test_Deloitte.Utils.StatusCodesEnum;
import com.deloitte.Test_Deloitte.Utils.WebServiceResponse;
import com.deloitte.Test_Deloitte.exceptions.UserNotLoggedIn;
import com.deloitte.Test_Deloitte.models.Task;
import com.deloitte.Test_Deloitte.service.TaskService;

/*
 * Class for task controller contains the following 4 methods. Once the user is registered and logged in. 
 * Following 4 methods can be used.
 * 
 * 1) Create Task
 * 2) Edit Task
 * 3) Get All tasks(once the users login)
 * 4) Delete a task
 * All the appropriate exceptions are present in com.deloitte.Test_Deloitte.exceptions package
 * and web service response along with status code is present in com.deloitte.Test_Deloitte.utils
 * */

@RestController
public class TaskController {

	@Autowired
	TaskService taskService;
	
	@CrossOrigin
	@GetMapping("/getUserAllTasks")
	public Object getUserAllTasks(@RequestHeader String sessionId) {

		WebServiceResponse webServiceResponse = new WebServiceResponse();

		try {
			Object taskResponse = taskService.getUserAllTasks(sessionId);
			webServiceResponse.setResultSet(taskResponse);
			webServiceResponse.setStatusCode(StatusCodesEnum.OKAY);
		} catch (UserNotLoggedIn e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.USER_NOT_LOGGEDIN);
		} catch (Exception e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.UNKNOWN_ERROR);

		}

		return webServiceResponse;
	}

	@CrossOrigin
	@PostMapping("/createTask")
	public Object createTask(@RequestHeader String sessionId, @RequestBody Task task) {

		WebServiceResponse webServiceResponse = new WebServiceResponse();

		try {
			Object taskResponse = taskService.createTask(sessionId, task);
			webServiceResponse.setResultSet(taskResponse);
			webServiceResponse.setStatusCode(StatusCodesEnum.OKAY);
		} catch (UserNotLoggedIn e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.USER_NOT_LOGGEDIN);
		} catch (Exception e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.UNKNOWN_ERROR);

		}
		return webServiceResponse;
	}
	
	@CrossOrigin
	@PostMapping("/editTask")
	public Object editTask(@RequestHeader String sessionId, @RequestBody Task task) {

		WebServiceResponse webServiceResponse = new WebServiceResponse();

		try {
			Object taskResponse = taskService.editTask(sessionId, task);
			webServiceResponse.setResultSet(taskResponse);
			webServiceResponse.setStatusCode(StatusCodesEnum.OKAY);
		} catch (UserNotLoggedIn e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.USER_NOT_LOGGEDIN);
		} catch (Exception e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.UNKNOWN_ERROR);

		}
		return webServiceResponse;
	}

	@CrossOrigin
	@PostMapping("/deleteTask")
	public Object deleteTask(@RequestHeader String sessionId, @RequestBody Task task) {

		WebServiceResponse webServiceResponse = new WebServiceResponse();

		try {
			taskService.deleteTask(sessionId, task);
			webServiceResponse.setStatusCode(StatusCodesEnum.OKAY);
		} catch (UserNotLoggedIn e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.USER_NOT_LOGGEDIN);
		} catch (Exception e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.UNKNOWN_ERROR);

		}
		return webServiceResponse;
	}
}
