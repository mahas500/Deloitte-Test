package com.deloitte.Test_Deloitte.service;

import javax.mail.MessagingException;

import com.deloitte.Test_Deloitte.exceptions.InvalidCredentials;
import com.deloitte.Test_Deloitte.exceptions.UsernameAlreadyTaken;
import com.deloitte.Test_Deloitte.exceptions.UsernameDoesNotExist;
import com.deloitte.Test_Deloitte.models.User;

/*
 * UserService interface containing abstract methods which are implemented in UserServiceImplementation class
 * */

public interface UserService {

	Object createUser(User user) throws UsernameAlreadyTaken, MessagingException;

	Object loginUser(User user) throws InvalidCredentials;

	void deleteUserData(String sessionId) throws UsernameDoesNotExist;

	void forgotPassword(User user) throws InvalidCredentials;

}
