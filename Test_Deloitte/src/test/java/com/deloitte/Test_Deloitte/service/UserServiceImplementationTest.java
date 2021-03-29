package com.deloitte.Test_Deloitte.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deloitte.Test_Deloitte.exceptions.UsernameAlreadyTaken;
import com.deloitte.Test_Deloitte.models.Task;
import com.deloitte.Test_Deloitte.models.User;
import com.deloitte.Test_Deloitte.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserServiceImplementationTest {

	User userCreateRequest = new User();

	@Autowired
	private UserServiceImplementation userServiceImplementation;
	
	@Autowired
	private UserRepository userRepository;

	@Before
	public void setup() throws Exception{

		userCreateRequest.setFirstName("Manik");
		userCreateRequest.setLastName("Mahashabde");
		userCreateRequest.setEmailAddress("mahamanik@gmail.com");
		userCreateRequest.setPassword("qwerty");
		userCreateRequest.setUsername("manik325");
		
		String str = "2021-03-26 15:18:41.971";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		
		userCreateRequest.setCreatedOn(dateTime);
		userCreateRequest.setUpdatedOn(dateTime);
		userCreateRequest.setSessionId("2");
		userCreateRequest.setTasks(new ArrayList<Task>());
		
		
	}
	
	
}
