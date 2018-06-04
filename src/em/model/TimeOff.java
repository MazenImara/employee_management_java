package em.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TimeOff implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    
    @Column(name="id")
	public int id;

    @Column(name="from")
	public long from;

    
    @Column(name="to")
	public long to;


    @Column(name="employee_id")
	public String employee_id;


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


	public String getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}


}
