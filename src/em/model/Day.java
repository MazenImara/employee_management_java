package em.model;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//MOHAMAD Code

@Entity
@Table(name="day")
public class Day implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
    @Id
    @GeneratedValue
    @Column(name="id")
    public int id;
    
    @Column(name="employee_id")
    public int employeeId;
    
    @Column(name="date")
    public long date;
    
    @Column(name="start_time")
    public long start;
    
    @Column(name="end_time")
    public long endTime;
    
    @Column(name="time_spend")
    public long timeSpend;


    @Column(name="temp")
	public long temp;
    
   
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public long getEndTime() {
		return endTime;
	}

	public long getTimeSpend() {
		return timeSpend;
	}

	public void setTimeSpend(long timeSpend) {
		this.timeSpend = timeSpend;
	}
	
}	
     
  

   
    
    
    
    
      
    


