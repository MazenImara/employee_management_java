//for project
//ikram
package em.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 453693552059515150L;
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public int id;
	
	@Column(name="title")
	public String title;
	
	@Column(name="description")
	public String description;
	
	@Column(name="status")
	public String status;
	
	@Column(name="timeSpend")
	public int timeSpend;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
	public List<Task> tasks;
	
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTimeSpend() {
		return timeSpend;
	}
	public void setTimeSpend(int timeSpend) {
		this.timeSpend = timeSpend;
	}
}
//end ikram