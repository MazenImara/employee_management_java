package em.dio;

import java.util.List;

import em.model.Employee;
import em.model.User;

public interface Dio {
	public User getUser(int id);
	public void addUser(User user);
	public void deleteUser(int id);
	public void updateUser(User user);
	public List<User> getUsers();
	//MOHAMAD 
	
	void addEmployee(Employee emp);
	Employee getEmployee(int id);
	void updateEmployee(Employee employee);
	void deleteEmployee(int employeeId);
	public List<Employee> listEmployees();
	List<Employee> listEmployeeByEmployeeId(int employeeId);
	
	

}
