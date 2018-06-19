package em.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//<!--MOHAMAD Code  -->
@Entity
@Table(name="time_off")
public class TimeOff implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="id")
	public int id;

    @Column(name="from_date")
	public long from;

    
    @Column(name="to_date")
	public long to;


    @Column(name="employee_id")
	public int employee_id;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public long getFrom() {
		return from;
	}


	public void setFrom(long from) {
		this.from = from;
	}


	public long getTo() {
		return to;
	}


	public void setTo(long to) {
		this.to = to;
	}


	public int getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}


}
