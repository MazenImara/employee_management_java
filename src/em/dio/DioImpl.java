package em.dio;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import em.model.Task;
import em.model.Project;
import em.model.Suggestion;
import em.model.User;
import em.model.Employee;
import em.dio.Dio;


public class DioImpl implements Dio  {
    private SessionFactory sessionFactory;
    
    public DioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }	
    
	@Override
	public User getUser(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    User user=null;
	    try {
	        System.out.println("IN GetUser");
	        session.beginTransaction();
	        user = (User) session.get(User.class, id);
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return user;
	}
	
	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        session.save(user);
	      } catch (HibernateException e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	    }
	        session.getTransaction().commit();	
	}
	
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUser(User User) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}
    
//for project
//ikram
	@Override
	public Project getProject(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    Project project=null;
	    try {
	        System.out.println("IN GetIteam");
	        session.beginTransaction();
	        project = (Project) session.get(Project.class, id);
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    project.tasks = getTasksByProjectId(project.id);
	    return project;
	}

	@Override
	public void addProject(Project project) {
		Session session = sessionFactory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        session.save(project);
	      } catch (HibernateException e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	    }
	        session.getTransaction().commit();
	}

	@Override
	public void deleteProject(int id) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    Project project = (Project) session.get(Project.class, id);
	    if(null != project) {
	        session.delete(project);
	    }
	    session.getTransaction().commit();
	}

	@Override
	public void updateProject(Project project) {
	
	    Session session = sessionFactory.getCurrentSession();
	    try {
	        System.out.println("IN Update");
	        session.beginTransaction();
	        session.saveOrUpdate(project);
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	    session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjects() {
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<Project> projects = null;
	    try {
	        System.out.println("IN LIST");
	        projects = (List<Project>)session.createQuery("from Project").list();
	
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    for(Project project: projects) {
	    	project.tasks = getTasksByProjectId(project.id);
	   }
	    return projects;
	}
		//end ikram
	

	
	//MOHAMAD Code
	
	//Employee Process
	
	
	@Override
	public Employee getEmployee(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    Employee emp=null;
	    try {
	        System.out.println("IN GetIteam");
	        session.beginTransaction();
	        emp = (Employee) session.get(Employee.class, id);
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return emp;
	}

	   
	
	@Override
	public void addEmployee(Employee employee) {
	    Session session = sessionFactory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        session.save(employee);
	    }
	    catch (HibernateException e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	}

	@Override
	public void updateEmployee(Employee employee) {
		 Session session = sessionFactory.getCurrentSession();
		    try {
		        System.out.println("IN Update");
		        session.beginTransaction();
		        session.saveOrUpdate(employee);
		        } catch (HibernateException e) {
		            e.printStackTrace();
		            session.getTransaction().rollback();
		        }
		    session.getTransaction().commit();
		}

	@Override
	public void deleteEmployee(int employeeId) {
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    Employee emp = (Employee) session.get(Employee.class, employeeId);
	    if(null != emp) {
	        session.delete(emp);
	    }
	    session.getTransaction().commit();
	  
	}

		@SuppressWarnings("unchecked")
		@Override
		public List<Employee> listEmployees() {
		    Session session = sessionFactory.getCurrentSession();
		    session.beginTransaction();
		    List<Employee> employees = null;
		    try {
		        System.out.println("IN LIST");
		        employees = (List<Employee>)session.createQuery("from Employee ").list();
		    }  catch (HibernateException e) {
		        e.printStackTrace();
		        session.getTransaction().rollback();
		    }
		     session.getTransaction().commit();

			    return employees;
			}
		

		 // end MOHAMAD

	
		//Gab Starting'

@Override
public Task getTask(int id) {
	Session session = sessionFactory.getCurrentSession();
    Task task=null;
    try {
        System.out.println("IN GetIteam");
        session.beginTransaction();
        task = (Task) session.get(Task.class, id);
    } catch (HibernateException e) {
        e.printStackTrace();
        session.getTransaction().rollback();
    }
    session.getTransaction().commit();
	    return task;
	}

	@Override
	public void addTask(Task task) {

		Session session = sessionFactory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        session.save(task);
	      } catch (HibernateException e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	    }
	        session.getTransaction().commit();
	}

	@Override
	public void deleteTask(int id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Task task = (Task) session.get(Task.class, id);
    if(null != task) {
        session.delete(task);
    }
    session.getTransaction().commit();
		
	}

	@Override
	public void updateTask(Task task) {
		Session session = sessionFactory.getCurrentSession();
	    try {
	        System.out.println("IN Update");
	        session.beginTransaction();
	        session.saveOrUpdate(task);
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            session.getTransaction().rollback();
	        }
	    session.getTransaction().commit();
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTasks() {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<Task> tasks = null;
	    try {
	        System.out.println("IN LIST");
	        tasks = (List<Task>)session.createQuery("from Task").list();
	
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return tasks;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTasksByProjectId(int project_id) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<Task> tasks = null;
	    try {
	        System.out.println("IN LIST");
	        tasks = (List<Task>)session.createQuery("from Task where project_id="+project_id).list();
	
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return tasks;
	}
	

	@Override
	public Suggestion getSuggestion(int id) {
		Session session = sessionFactory.getCurrentSession();
	    Suggestion suggestion=null;
	    try {
	        System.out.println("IN GetIteam");
	        session.beginTransaction();
	        suggestion = (Suggestion) session.get(Suggestion.class, id);
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return suggestion;
	}

	@Override
	public void addSuggestion(Suggestion suggestion) {
		Session session = sessionFactory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        session.save(suggestion);
	      } catch (HibernateException e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	    }
	        session.getTransaction().commit();
	}

	@Override
	public void deleteSuggestion(int id) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    Suggestion suggestion = (Suggestion) session.get(Suggestion.class, id);
	    if(null != suggestion) {
	        session.delete(suggestion);
	    }
	    session.getTransaction().commit();	
	}

	@Override
	public void updateSuggestion(Suggestion suggestion) {
	    Session session = sessionFactory.getCurrentSession();
	    try {
	        System.out.println("IN Update");
	        session.beginTransaction();
	        session.saveOrUpdate(suggestion);
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    session.getTransaction().commit();	
    }
	        
	        
	
	
	        
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Suggestion> getSuggestions() {
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<Suggestion> suggestions = null;
	    try {
	        System.out.println("IN LIST");
	        suggestions = (List<Suggestion>)session.createQuery("from Suggestion").list();
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return suggestions;
	}

	@Override
	public List<Project> getProjects2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProject2(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

//Gab Endline

