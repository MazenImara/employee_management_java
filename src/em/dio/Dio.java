package em.dio;

import java.util.List;

import em.model.Task;
import em.model.User;

public interface Dio {
	public User getUser(int id);
	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	public List<User> getUsers();
	
	public Task getTask(int id);
	public void addTask(Task task);
	public void deleteTask(int id);
	public void updatetask(Task task);
	public List<Task> getTasks();
	
}
