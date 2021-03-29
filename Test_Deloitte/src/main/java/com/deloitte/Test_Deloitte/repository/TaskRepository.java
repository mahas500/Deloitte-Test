package com.deloitte.Test_Deloitte.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.deloitte.Test_Deloitte.models.Task;

/*
 * Task Repository which has a query to get all the tasks of a user using User ID from h2 database
 * */
@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

	ArrayList<Task> findAllByUserId(String id);
	
}
