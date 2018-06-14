package em.dio;

import java.io.Serializable;
import java.sql.Timestamp;
import em.model.Day;
import em.model.Task;


public class SpecialFunctions implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dio dio;
 
	
	public void pauseTask(int taskId){
		Task task = dio.getTask(taskId);
    	task.status = "Paused";
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
    	task.timespend = task.timespend + ( timestamp.getTime() - task.timetemp);
    	dio.updateTask(task);
    }

}