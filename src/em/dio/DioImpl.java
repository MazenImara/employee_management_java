package em.dio;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import em.model.Task;
import em.model.TimeOff;
import em.model.User;
import em.model.Employee;
import em.model.Project;
import em.model.Suggestion;
import em.model.Day;
import em.model.Admin;
import em.dio.Dio;



public class DioImpl implements Dio  {
    private SessionFactory sessionFactory;
    
    public DioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }	
    
	@Override
	public void addAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        session.save(admin);
	      } catch (HibernateException e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	    }
	        session.getTransaction().commit();	
	}
	
	

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
	@SuppressWarnings("unchecked")
	@Override
	
	public List<Project> getProjectsByEmployeeId(int employeeId) {
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
	    	project.tasks = getTasksByProjectIdEmployeeId(project.id, employeeId);
	   }
	    return projects;
	}
		//end ikram
	

	
	//MOHAMAD Code
	
	//Employee Process
	
	
	@Override
	public Employee getEmployee(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    Employee employee=null;
	    try {
	        System.out.println("IN GetIteam");
	        session.beginTransaction();
	        employee = (Employee) session.get(Employee.class, id);
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return employee;
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
		public List<Employee> getEmployees() {
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
		
		@SuppressWarnings("unchecked")
		@Override
		public List<Admin> getAdminsByEmployeeId(int employeeId) {
				Session session = sessionFactory.getCurrentSession();
			    session.beginTransaction();
			    List<Admin> admins = null;
			    try {
			        System.out.println("IN LIST");
			        admins = (List<Admin>)session.createQuery("from Admin where employee_id="+employeeId).list();
			
			    } catch (HibernateException e) {
			        e.printStackTrace();
			        session.getTransaction().rollback();
			    }
			    session.getTransaction().commit();
			    return admins;
			}
		
		@SuppressWarnings("unchecked")
		@Override
			public Employee checkLogin(String email, String password) {

			    Session session = sessionFactory.getCurrentSession();
			    session.beginTransaction();
			    Employee employee=new Employee();
			    List<Employee> employees = null;
			    try {
			        System.out.println("IN LIST");
			        employees = (List<Employee>)session.createQuery("from Employee ").list();
			        outer:
			        for(Employee emp:employees) {
				    	if(emp.email.equals(email) && emp.password.equals(password) ) {
				    		employee=emp;
				    		break outer;
				        }
				    	else {
				    		employee=null;
				    	}
			        }
			    } catch (HibernateException e) {
			        e.printStackTrace();
			        session.getTransaction().rollback();
			    }
			    session.getTransaction().commit();
			    return employee;
	}	
		@Override
		public TimeOff getTimeOff(int timeOffId) {
		    Session session = sessionFactory.getCurrentSession();
		    TimeOff timeOff=null;
		    try {
		        System.out.println("IN GetIteam");
		        session.beginTransaction();
		        timeOff = (TimeOff) session.get(TimeOff.class, timeOffId);
		    } catch (HibernateException e) {
		        e.printStackTrace();
		        session.getTransaction().rollback();
		    }
		    session.getTransaction().commit();
		    return timeOff;
		}

		   
		
		@Override
		public void addTimeOff(TimeOff timeOff) {
		    Session session = sessionFactory.getCurrentSession();
		    try {
		        session.beginTransaction();
		        session.save(timeOff);
		    }
		    catch (HibernateException e) {
		          e.printStackTrace();
		          session.getTransaction().rollback();
		    }
		    session.getTransaction().commit();
		}

		@Override
		public void updateTimeOff(TimeOff timeOff) {
			 Session session = sessionFactory.getCurrentSession();
			    try {
			        System.out.println("IN Update");
			        session.beginTransaction();
			        session.saveOrUpdate(timeOff);
			        } catch (HibernateException e) {
			            e.printStackTrace();
			            session.getTransaction().rollback();
			        }
			    session.getTransaction().commit();
			}

		@Override
		public void deleteTimeOff(TimeOff timeOffId) {
		    Session session = sessionFactory.getCurrentSession();
		    session.beginTransaction();
		    TimeOff timeOff = (TimeOff) session.get(TimeOff.class, timeOffId);
		    if(null != timeOff) {
		        session.delete(timeOffId);
		    }
		    session.getTransaction().commit();
		}

			@SuppressWarnings("unchecked")
			@Override
			public List<TimeOff> getTimesOff() {
			    Session session = sessionFactory.getCurrentSession();
			    session.beginTransaction();
			    List<TimeOff> timesOff = null;
			    try {
			        System.out.println("IN LIST");
			        timesOff = (List<TimeOff>)session.createQuery("from TimeOff ").list();
			    }  catch (HibernateException e) {
			        e.printStackTrace();
			        session.getTransaction().rollback();
			    }
			     session.getTransaction().commit();

				    return timesOff;
			}
			@SuppressWarnings("unchecked")
			@Override
			public List<TimeOff> getTimesOffByEmployeeId(int employeeId) {
					Session session = sessionFactory.getCurrentSession();
				    session.beginTransaction();
				    List<TimeOff> timesOff = null;
				    try {
				        System.out.println("IN LIST");
				        timesOff = (List<TimeOff>)session.createQuery("from TimeOff where employee_id="+employeeId).list();
				
				    } catch (HibernateException e) {
				        e.printStackTrace();
				        session.getTransaction().rollback();
				    }
				    session.getTransaction().commit();
				    return timesOff;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTasksByProjectIdEmployeeId(int project_id, int employeeId) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<Task> tasks = null;
	    try {
	        System.out.println("IN LIST");
	        tasks = (List<Task>)session.createQuery("from Task WHERE (project_id ="+ project_id + " and status = 'New') or (project_id ="+ project_id + " and employee_id = "+employeeId+")").list();
	
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return tasks;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> checkStatus(String status) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<Task> tasks = null;
	    try {
	        System.out.println("IN LIST FOR STATUS");
	        tasks = (List<Task>)session.createQuery("from Task where status="+status).list();
	
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
	   // suggestion.project = getProjectById(suggestion.project.id);
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

	// Nidal
	@Override
	public Day getDay(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    Day day=null;
	    try {
	        System.out.println("IN Get Day");
	        session.beginTransaction();
	        day = (Day) session.get(Day.class, id);
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return day;
	}

	
	@Override
	public void addDay(Day day) {
	    Session session = sessionFactory.getCurrentSession();
	    try {
	        session.beginTransaction();
	        session.save(day);
	      } catch (HibernateException e) {
	          e.printStackTrace();
	          session.getTransaction().rollback();
	    }
	        session.getTransaction().commit();
	}

	@Override
	public void deleteDay(int id) {
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    Day day = (Day) session.get(Day.class, id);
	    if(null != day) {
	        session.delete(day);
	    }
	    session.getTransaction().commit();
	}
	
	@Override
	public void updateDay(Day day) {
		Session session = sessionFactory.getCurrentSession();
		try {
			System.out.println("IN update");
			session.beginTransaction();
			session.saveOrUpdate(day);
		}catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		session.getTransaction().commit();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Day> getDays() {
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<Day>days = null;
	    try {
	        System.out.println("IN LIST");
	        days = (List<Day>)session.createQuery("from day").list();
	
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();

	    return days;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Day> getDayByEmployeeId(int employeeId) {
		Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<Day> days = null;
	    try {
	        System.out.println("IN LIST");
	        days = (List<Day>)session.createQuery("from Day where employee_id="+employeeId).list();
	
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return days;
	}

}

	
	


// end Nidal







