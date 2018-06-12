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

	

	
	//Nidal
	public Day  getDay(int id);
	public void addDay(Day day);
	public void deleteDay(int id);
	public void updateDay(Day day);
	public List<Day> getDays();

// end Nidal







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
	
	
	//MOHAMAD 
	
		public void addEmployee(Employee employee);
		public Employee getEmployee(int id);
		public void updateEmployee(Employee employee);
		public void deleteEmployee(int employeeId);
		public List<Employee> getEmployees();
		public Employee checkLogin(String email, String password);
		public List<Admin> getAdminsByEmployeeId(int employeeId);
		public void addAdmin(Admin admin);
		public void addTimeOff(TimeOff timeOff);
		public void updateTimeOff(TimeOff timeOff);
		public void deleteTimeOff(int timeOffId);
		public List<TimeOff> getTimesOff();
		public TimeOff getTimeOff(int timeOffId);
		public List<TimeOff> getTimesOffByEmployeeId(int employeeId);
		public List<Day> getDayByEmployeeId(int employeeId);
		public List<Day> selectEmployeesWorkTimeForPeriod(long date1, long date2, int employeeId);
		

}
