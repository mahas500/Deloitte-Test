# Deloitte-Test

•	Spring Boot framework is used for backend as it helps in developing the web applications in the fast and easy manner. The advantage is the auto-configuration which is really helpful in developing application. It also provides inbuilt tomcat for running the application. 
•	Angular framework is used for frontend development as it helps in creating light-weight single page applications. 
•	H2 database is used as the in-memory database.
•	Session based authentication is used for security. In this approach user credentials (username and password) are authenticated during login and a UUID session is generated on the server side and stored in the database. Every time, user creates a new task, edit an existing task or delete a task, the session is checked for authorization. 
•	Cascade delete is implemented so that if a user decides to delete his account. Then his relevant tasks will also be deleted from the task table. 
•	Spring data JPA is used for CRUD operation with h2 database. 
•	Custom Exceptions are implemented in exceptions package and status code is implemented using Enum class in utils package. 
•	The packages created in the spring boot are main, controller, models, exceptions, services, utils, repository, mail, test. 
•	PostMan used for testing the endpoints locally.
•	Login component, Register, to-do-task-list, forgot passwords are the components created in Angular. The version used is Angular 11. 
•	Maven used for managing dependencies.

Below are the endpoints 
User component endpoints:-
http://localhost:8080/Deloitte/createUser (creating a new user)
http://localhost:8080/Deloitte/loginUser (login endpoint for existing user)
http://localhost:8080/Deloitte/forgotPassword (new password setting for existing user)
http://localhost:8080/Deloitte/deleteUserData (user can delete his/her account)

Task component endpoints:- 
http://localhost:8080/Deloitte/createTask (user can create a new task after login)
http://localhost:8080/Deloitte/getUserAllTasks (user sees all tasks on dashboard after login)
http://localhost:8080/Deloitte/editTask (user can change details of an existing task)
http://localhost:8080/Deloitte/deleteTask (user can delete a task)

Deploy the war file in any application server.

For frontned please install node_modules using below command
npm install
