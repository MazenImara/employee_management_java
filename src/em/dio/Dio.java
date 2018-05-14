package em.dio;

import java.util.List;

import em.model.User;

public interface Dio {
	public User getUser(int id);
	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	public List<User> getUsers();
}
