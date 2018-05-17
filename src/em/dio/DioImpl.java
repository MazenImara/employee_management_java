package em.dio;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import em.model.Task;
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
		// TODO Auto-generated method stub
		
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
	public void updatetask(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return null;
	}

}
