package em.dio;

import java.util.List;

import em.model.Day;
import em.model.User;

public interface Dio {

	public User getUser(int id);
	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	public List<User> getUsers();

	
	public Day getDay(int id);
	public void addDay(Day day);
	List<Day> deleteDay(int id);
	public void updateDay(List<Day> day);
	public List<Day> getDays();
	void updateDay(Day day);
	public List<Day> listDay();
	
}

	
	
	
	

