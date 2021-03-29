package com.deloitte.Test_Deloitte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.Test_Deloitte.models.User;

/*
 * User Repository which has queries to get a user record from h2 database based on specific conditions
 * */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "Select * from user u where u.username=?1", nativeQuery = true)
	User findByUserName(String username);

	@Query(value = "Select * from user u where u.username=?1 AND password=?2", nativeQuery = true)
	User getUserFromUsernameAndPassword(String username, String password);

	@Query(value = "Select * from user u where u.session_id=?1", nativeQuery = true)
	User getUserFromSessionID(String sessionId);

	@Query(value = "Select * from user u where u.email_address=?1 and u.username=?2", nativeQuery = true)
	User getUserFromEmailIdAndUsername(String emailAddress, String username);

}
