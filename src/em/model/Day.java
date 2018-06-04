package em.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    public int employee_id;
    
    @Column(name="date")
    public long date;
    
    @Column(name="start")
    public long start;
    
    @Column(name="endtime")
    public long endtime;
    
    @Column(name="temp")
    public long temp;
    
    @Column(name="timespend")
    public int timespend;
    
    @Column(name="check")
    public int check;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employee_id;
	}

	public void setEmployeeId(int employee_id) {
		this.employee_id = employee_id;
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

	public long getEndtime() {
		return endtime;
	}

	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}

	public long getTemp() {
		return temp;
	}

	public void setTemp(long temp) {
		this.temp = temp;
	}

	public int getTimeSpend() {
		return timespend;
	}

	public void setTimeSpend(int timespend) {
		this.timespend = timespend;
	}

	public int Check() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public long getPeriod1() {
		return period1;
	}

	public void setPeriod1(long period1) {
		this.period1 = period1;
	}

	public long getPeriod2() {
		return period2;
	}

	public void setPeriod2(long period2) {
		this.period2 = period2;
	}

	@Column(name="period1")
    public long period1;
    
    @Column(name="period2")
    public long period2;
  }  

    
    
    
   
    
    
    
    
      
    


