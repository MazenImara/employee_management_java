package em.viewmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import em.model.Day;

public class _DayVM {

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
		String  currentTimeReadable=toDate(currentTimeInMillis);
		char[] c = currentTimeReadable.toCharArray();
	    String d=String.valueOf(c, 0, 8)+00+" "+0+":"+0;
		long   date= toMillisecond(d);
		System.out.println(d);
		return date; 
    }
	public long  lastDayInCurrentMonth() throws ParseException {
		Day day= new Day();
		long currentTime=System.currentTimeMillis();
		long currentTimeInMillis=TimeUnit.DAYS.toDays(currentTime);
		String  currentTimeReadable=toDate(currentTimeInMillis);
		char[] c = currentTimeReadable.toCharArray();
	    String d=String.valueOf(c, 0, 8)+31+" "+0+":"+0;
		long   date= toMillisecond(d); 
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
