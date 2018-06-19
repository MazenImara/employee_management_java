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
	
   
    
	
	

	public String toDate(long millisecond) {
	    Date date = new Date(millisecond);
	    return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	
	public long toMillisecond(String myDate) throws ParseException {
		Calendar c1 = Calendar.getInstance(TimeZone.getTimeZone("UTC")); 
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC")); 
        c1.setTime(sdf.parse(myDate));
        long millieSeconde=c1.getTimeInMillis();
	    System.out.println("c1:"+c1.getTimeInMillis());
	    return millieSeconde-7200000;
	}
	public long  firstDayInCurrentMonth() throws ParseException {
		Day day= new Day();
		long currentTime=System.currentTimeMillis();
		long currentTimeInMillis=TimeUnit.DAYS.toDays(currentTime);
		String  currentTimeReadable=day.toDate(currentTimeInMillis);
		char[] c = currentTimeReadable.toCharArray();
	    String d=String.valueOf(c, 0, 8)+00+" "+0+":"+0;
		long   date= day.toMillisecond(d);
		System.out.println(d);
		return date; 
    }
	public long  lastDayInCurrentMonth() throws ParseException {
		Day day= new Day();
		long currentTime=System.currentTimeMillis();
		long currentTimeInMillis=TimeUnit.DAYS.toDays(currentTime);
		String  currentTimeReadable=day.toDate(currentTimeInMillis);
		char[] c = currentTimeReadable.toCharArray();
	    String d=String.valueOf(c, 0, 8)+31+" "+0+":"+0;
		long   date= day.toMillisecond(d); 
		System.out.println(d);
		return date ;
    }
	
	
		public  String getDurationString(long millisecondtTime){
                long  seconds=millisecondtTime /1000;
			    int hours = (int) (seconds / 3600);
			    int minutes = (int) ((seconds % 3600) / 60);
			     seconds = seconds % 60;
			    return (hours) + " : " + (minutes) + " : " + (seconds);
		
		}
	

	
}	
     
  

   
    
    
    
    
      
    


