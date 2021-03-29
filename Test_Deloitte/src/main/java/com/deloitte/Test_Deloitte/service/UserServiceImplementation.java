package com.deloitte.Test_Deloitte.service;

import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.Test_Deloitte.exceptions.InvalidCredentials;
import com.deloitte.Test_Deloitte.exceptions.UserNotLoggedIn;
import com.deloitte.Test_Deloitte.exceptions.UsernameAlreadyTaken;
import com.deloitte.Test_Deloitte.exceptions.UsernameDoesNotExist;
import com.deloitte.Test_Deloitte.mail.EmailServiceImpl;

import com.deloitte.Test_Deloitte.models.User;
import com.deloitte.Test_Deloitte.repository.UserRepository;

/*
 * UserServiceImplementation class containing business logics for implementing
 * 1) login User
 * 2) Create User
 * 3) Delete User
 * 4) Change Password
 * */
@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailServiceImpl emailServiceImpl;

	/*
	 * 1) loginUser method first checks the credentials are correct or not 2) If Not
	 * it will throw Invalid credentials exception. 3) If it is it will create a
	 * UUID sessionId and store it in h2 database.
	 */

	@Override
	public Object loginUser(User user) throws InvalidCredentials {

		Optional<User> userFromDB = Optional
				.ofNullable(userRepository.getUserFromUsernameAndPassword(user.getUsername(), user.getPassword()));

		if (userFromDB.isPresent()) {
			userFromDB.get().setSessionId(UUID.randomUUID().toString());
			user = userRepository.save(userFromDB.get());

		} else {
			throw new InvalidCredentials();
		}

		return user;
	}

	/*
	 * 1) createUser method first checks the username is used 2) If is used will
	 * throw Username already taken exception. 3) If it is it will create a user ID
	 * using UUID and store the user object in database.
	 */

	public Object createUser(User user) throws UsernameAlreadyTaken, MessagingException {

		if (getUserFromUsername(user.getUsername())) {
			throw new UsernameAlreadyTaken();
		} else {
			user.setId(UUID.randomUUID().toString());
			user = userRepository.save(user);
			
		}

		return user;

	}

	/*
	 * 1) deleteUserData method first gets the user object from session ID 2) it
	 * will delete the user from h2 database and all the corresponding tasks
	 * associated with the user will also be deleted
	 */
	@Override
	public void deleteUserData(String sessionId) throws UsernameDoesNotExist {

		User userObject = userRepository.getUserFromSessionID(sessionId);

		userRepository.deleteById(userObject.getId());

	}

	private boolean getUserFromUsername(String username) {

		Optional<User> userFromDB = Optional.ofNullable(userRepository.findByUserName(username));

		return (true ? userFromDB.isPresent() : false);
	}

	public Optional<User> getUserFromSessionID(String sessionId) throws UserNotLoggedIn {

		Optional<User> userObject = Optional.ofNullable(userRepository.getUserFromSessionID(sessionId));

		if (userObject.isPresent()) {
			return userObject;
		} else {
			throw new UserNotLoggedIn();
		}
	}

	/*
	 * 1) forgotPassword method first checks the credentials are correct or not 2)
	 * If Not it will throw Invalid credentials exception. 3) If it is it will
	 * save the new password in H2 database.
	 */

	@Override
	public void forgotPassword(User user) throws InvalidCredentials {

		Optional<User> userObject = Optional
				.ofNullable(userRepository.getUserFromEmailIdAndUsername(user.getEmailAddress(), user.getUsername()));
		if (userObject.isPresent()) {
			userObject.get().setPassword(user.getPassword());
			userRepository.save(userObject.get());
		} else {
			throw new InvalidCredentials();
		}

	}
}
