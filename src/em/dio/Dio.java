package em.dio;

import java.util.List;


import em.model.Day;

import em.model.Task;
import em.model.TimeOff;
import em.model.Employee;
import em.model.Project;
import em.model.Suggestion;
import em.model.User;
import em.model.Admin;

public interface Dio {

	public User getUser(int id);
	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User User);
	public List<User> getUsers();

	
	//Nidal
	public Day getDay(int id);
	public void addDay(Day day);
	void deleteDay(int id);
	public void updateDay(List<Day> day);
	public List<Day> getDays();
	void updateDay(Day day);
	public List<Day> listDay();
	public void logout() ;

// end Nidal



//MOHAMAD 
	
	void addEmployee(Employee employee);
	Employee getEmployee(int id);
	void updateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	public List<Employee> getEmployees();
	public Employee checkLogin(String email, String password);
	List<Admin> getAdminsByEmployeeId(int employeeId);
	public void addAdmin(Admin admin);
	public void addTimeOff(TimeOff timeOff);
	public void updateTimeOff(TimeOff timeOff);
	public void deleteTimeOff(TimeOff timeOffId);
	public List<TimeOff> getTimeOff();
	public TimeOff getTimeOff(int timeOffId);
	
	



// for Task 
// Gab	
	public Task getTask(int id);
	public void addTask(Task task);
	public void deleteTask(int id);
	public void updateTask(Task task);
	public List<Task> getTasks();
//Gab Endline	

	
// for project 
// ikram
	public Project getProject(int id);
	public void addProject(Project project);
	public void deleteProject(int id);
	public void updateProject(Project project);
	public List<Project> getProjects();
	public Suggestion getSuggestion(int id);
	public void addSuggestion(Suggestion suggestion);
	public void deleteSuggestion(int id);
	public void updateSuggestion(Suggestion suggestion);
	public List<Suggestion> getSuggestions();
	public List<Task> getTasksByProjectId(int project_id);
	public List<Task> checkStatus(String status);
	
	
	

	
//end ikram	

}
