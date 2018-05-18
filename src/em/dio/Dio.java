package em.dio;

import java.util.List;

import em.model.Task;
import em.model.Project;
import em.model.Suggestion;
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
	public void addProject(Project project);
	public void deleteProject(int id);
	public void updateProject(Project project);
	public List<Project> getProjects();
	
	public Suggestion getSuggestion(int id);
	public void addSuggestion(Suggestion suggestion);
	public void deleteSuggestion(int id);
	public void updateSuggestion(Suggestion suggestion);
	public List<Suggestion> getSuggestions();
	
//end ikram	
}


