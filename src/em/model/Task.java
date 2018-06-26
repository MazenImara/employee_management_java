package em.model;



import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import em.dio.Dio;




@Entity
@Table(name="task")
public class Task implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
	public int id;

	@Column(name="title")
	public String title;

	@Column (name="status")
	public String status;

	@Column (name="timespend")
	public long timespend;


	@Column (name="started")
	public long started;

	@Column (name="finish")
	public long finish;

	@Column (name="timetemp")
	public long timetemp;


	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = true)
	public Employee employee;

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = true)
	public Project project;

	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTimespend() {
		return timespend;
	}


	public void setTimespend(long timespend) {
		this.timespend = timespend;
	}



	public long getStarted() {
		return started;
	}



	public void setStarted(long started) {
		this.started = started;
	}



	public long getFinish() {
		return finish;
	}



	public void setFinish(long finish) {
		this.finish = finish;
	}


	public long getTimetemp() {
		return timetemp;
	}


	public void setTimetemp(long timetemp) {
		this.timetemp = timetemp;
	}
	 public void validate(Object target, Errors errors) {
	       ValidationUtils.rejectIfEmptyOrWhitespace(errors,"title", "field.required");
	 }

}