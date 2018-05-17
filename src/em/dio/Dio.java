package em.dio;

import java.util.List;

import em.model.Task;
import em.model.Project;
import em.model.User;

public interface Dio {
	public User getUser(int id);
	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User User);
	public List<User> getUsers();

	
// for Task 
// Gab	
	public Task getTask(int id);
	public void addTask(Task task);
	public void deleteTask(int id);
	public void updatetask(Task task);
	public List<Task> getTasks();
//Gab Endline	

// for project 
// ikram
	public Project getProject(int id);
	public void addProject(Project Project);
	public void deleteProject(int id);
	public void updateProject(Project Project);
	public List<Project> getProjects();
//end ikram	
}


