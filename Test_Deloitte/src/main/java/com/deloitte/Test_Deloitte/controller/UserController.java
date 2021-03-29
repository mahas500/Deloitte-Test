package com.deloitte.Test_Deloitte.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.Test_Deloitte.Utils.StatusCodesEnum;
import com.deloitte.Test_Deloitte.Utils.WebServiceResponse;
import com.deloitte.Test_Deloitte.exceptions.InvalidCredentials;
import com.deloitte.Test_Deloitte.exceptions.UsernameAlreadyTaken;
import com.deloitte.Test_Deloitte.exceptions.UsernameDoesNotExist;
import com.deloitte.Test_Deloitte.models.User;
import com.deloitte.Test_Deloitte.service.UserService;

/*
 * Class for user controller contains the following 4 methods:
 * 1) Create User
 * 2) Login User
 * 3) Delete User(along with cascade delete on tasks)
 * 4) forgot password
 * All the appropriate exceptions are present in com.deloitte.Test_Deloitte.exceptions package
 * and web service response along with status code is present in com.deloitte.Test_Deloitte.utils
 * */

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@CrossOrigin
	@PostMapping("/loginUser")
	public WebServiceResponse loginUser(@RequestBody User user) {

		WebServiceResponse webServiceResponse = new WebServiceResponse();

		try {
			Object userResponse = userService.loginUser(user);
			webServiceResponse.setResultSet(userResponse);
			webServiceResponse.setStatusCode(StatusCodesEnum.OKAY);

		} catch (InvalidCredentials e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.INVALID_CREDENTIAL);
		} catch (Exception e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.UNKNOWN_ERROR);
		}

		return webServiceResponse;

	}

	@CrossOrigin
	@PostMapping("/createUser")
	public WebServiceResponse createUser(@RequestBody User user) throws MessagingException {

		WebServiceResponse webServiceResponse = new WebServiceResponse();

		try {
			Object userResponse = userService.createUser(user);
			webServiceResponse.setResultSet(userResponse);
			webServiceResponse.setStatusCode(StatusCodesEnum.OKAY);
		} catch (UsernameAlreadyTaken e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.USERNAME_ALREADY_TAKEN);
		} catch (Exception e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.UNKNOWN_ERROR);
		}

		return webServiceResponse;

	}

	@CrossOrigin
	@DeleteMapping("/deleteUserData")
	public WebServiceResponse deleteUserData(@RequestHeader String sessionId) {

		WebServiceResponse webServiceResponse = new WebServiceResponse();

		try {
			userService.deleteUserData(sessionId);
			webServiceResponse.setStatusCode(StatusCodesEnum.OKAY);
		} catch (UsernameDoesNotExist e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.USERNAME_DOES_NOT_EXIST);
		} catch (Exception e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.UNKNOWN_ERROR);
		}

		return webServiceResponse;

	}

	@CrossOrigin
	@PostMapping("/forgotPassword")
	public WebServiceResponse forgotPassword(@RequestBody User user) {

		WebServiceResponse webServiceResponse = new WebServiceResponse();

		try {
			userService.forgotPassword(user);
			webServiceResponse.setStatusCode(StatusCodesEnum.OKAY);
		} catch (InvalidCredentials e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.INVALID_CREDENTIAL);
		} catch (Exception e) {
			e.printStackTrace();
			webServiceResponse.setStatusCode(StatusCodesEnum.UNKNOWN_ERROR);
		}

		return webServiceResponse;

	}

}
