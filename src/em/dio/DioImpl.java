package em.dio;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import em.model.Day;
import em.model.Employee;
import em.model.Login;
import em.model.User;

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
	        System.out.println("IN GetIteam");
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
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    User user = (User) session.get(User.class, id);
	    if(null != user) {
	        session.delete(user);
	    }
	    session.getTransaction().commit();
	}

	@Override
	public void updateUser(User user) {
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
	
	    Session session = sessionFactory.getCurrentSession();
	    session.beginTransaction();
	    List<User> users = null;
	    try {
	        System.out.println("IN LIST");
	        users = (List<User>)session.createQuery("from User").list();
	
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();

	    return users;
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
	    List<Day> Days = null;
	    try {
	        System.out.println("IN LIST");
	        Days = (List<Day>)session.createQuery("from day").list();
	
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();

	    return Days;
	}

	@Override
	public void updateDay(List<Day> day) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Day> listDay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
	
	
	}
	
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


		@Override
		public Login checkLogin() {
			// TODO Auto-generated method stub
			return checkLogin();
		}


}

		/*
	@Override
	public Login checkLogin(Login login) {
		Session session = sessionFactory.getCurrentSession();
	    Login login=null;
	    try {
	        session.beginTransaction();
	        login = (Login) session.get(email= login.email, password=login.password);
	    } catch (HibernateException e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    session.getTransaction().commit();
	    return login;
		
		
	}
*/
	


// end Nidal