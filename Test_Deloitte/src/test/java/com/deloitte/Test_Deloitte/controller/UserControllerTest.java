package com.deloitte.Test_Deloitte.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.aspectj.lang.annotation.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deloitte.Test_Deloitte.BaseToDoListTest;
import com.deloitte.Test_Deloitte.Utils.WebServiceResponse;
import com.deloitte.Test_Deloitte.exceptions.UsernameAlreadyTaken;
import com.deloitte.Test_Deloitte.models.Task;
import com.deloitte.Test_Deloitte.models.User;
import com.deloitte.Test_Deloitte.service.UserServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest extends BaseToDoListTest{

	
	User userCreateRequest = new User();
	User userCreateResponse = new User();
	
	private UserServiceImplementation userServiceImplementation = mock(UserServiceImplementation.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    
	@Autowired
	private UserController userController;
	
	@Before(value = "")
	public void setUp() throws UsernameAlreadyTaken, MessagingException {
		
		userCreateRequest.setId("1");
		userCreateRequest.setFirstName("Manik");
		userCreateRequest.setLastName("Mahashabde");
		userCreateRequest.setEmailAddress("mahamanik@gmail.com");
		userCreateRequest.setPassword("qwerty");
		userCreateRequest.setUsername("mahas500");
		
		
		String str = "2021-03-26 15:18:41.971";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		
		userCreateRequest.setCreatedOn(dateTime);
		userCreateRequest.setUpdatedOn(dateTime);
		userCreateRequest.setSessionId("2");
		userCreateRequest.setTasks(new ArrayList<Task>());
		
		System.out.println(userCreateRequest);
		
		userCreateResponse.setId("1");
		userCreateResponse.setFirstName("Manik");
		userCreateResponse.setLastName("Mahashabde");
		userCreateResponse.setEmailAddress("mahamanik@gmail.com");
		userCreateResponse.setPassword("qwerty");
		userCreateResponse.setUsername("mahas500");
		
		
		String str1 = "2021-03-26 15:18:41.971";
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter1);
		
		userCreateResponse.setCreatedOn(dateTime1);
		userCreateResponse.setUpdatedOn(dateTime1);
		userCreateResponse.setSessionId("2");
		userCreateResponse.setTasks(new ArrayList<Task>());
		
		
		//when(userServiceImplementation.createUser(userCreateRequest)).thenReturn(userCreateResponse);
        //ReflectionTestUtils.setField(userController, "UserServiceImplementation", userServiceImplementation);
	}
	
	@Ignore
	@Test
	public void createUserTest() throws Exception
	{
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
    			.post("/createUser")
    			.accept(MediaType.APPLICATION_JSON).
    			content(objectMapper.writeValueAsString(userCreateRequest)).contentType(MediaType.APPLICATION_JSON);
    	
    	MockHttpServletResponse response = getMockMvc().perform(builder).andReturn().getResponse();
    	
    	
      assertNull(objectMapper.readValue(response.getContentAsString(), User.class));
       
       
		
	}
}
