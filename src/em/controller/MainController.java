package em.controller;


import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import em.dio.Dio;
import em.model.Admin;
import em.model.Day;
import em.model.Employee;
import em.model.Project;
import em.model.Suggestion;
import em.model.Task;
import em.model.TimeOff;
import em.model.Log;




@Controller
public class MainController {
	@Autowired
	
	private Dio dio;
	private int offset;
	

	
	
	//MOHAMAD CODE
	
	@RequestMapping(value="/login")
 	public ModelAndView  login(){
 		ModelAndView model = new ModelAndView("login");
		return model;
 	}  
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/checklogin",method = RequestMethod.POST)
 	public String  login(HttpSession session,@RequestParam(value="email", required=true) String  email,@RequestParam(value="password", required=true) String  password)   {	
		Employee employee=dio.checkLogin(email,password);
 		if (employee != null) {
 			Admin admin=new Admin();
 			Log log = new Log();
 			log.employee = employee;
 			List<Admin> admins =  dio.getAdminsByEmployeeId(employee.id);
 			for (Admin ad :admins) {
 				admin=ad;
 			}
 			if( admin.id == 0 ) {
 				log.role = "Employee";
 			}
 			else {
 				log.role = "Admin" ;
 			}
 			Day day =new Day();
 			day.employeeId=employee.id;
 			boolean check = false; 
		    List<Day> days=dio.getDayByEmployeeId(employee.id);
		    outer:
		    for( Day day1: days) {
		       if (day1!=null) {
				       if (day.toDate(System.currentTimeMillis()).contentEquals(day.toDate(day1.date))  && day1.employeeId == employee.id) {
				    	   day1.temp=System.currentTimeMillis();
				    	   day.temp=day1.temp;
				    	   day.date=day1.date;
				    	   day.start=day1.start;
				    	   day.timeSpend=day1.timeSpend;
				    	   day.endTime=day1.endTime;
				    	   check=true;
				    	   dio.updateDay(day1);
				    	   log.setDay(day1);
				    	   break outer;
				       }
				       System.out.println("current Time is"+day.toDate(System.currentTimeMillis()));
				       System.out.println("day is"+ day.toDate(day1.date));
		       }
		    }
		    if (check !=true) {
			    day.date = System.currentTimeMillis();	
			    day.start= System.currentTimeMillis();
				dio.addDay(day);
				log.setDay(day);
            }
			session.setAttribute("log", log);
	 		session.setMaxInactiveInterval(-1);
 		}
 		return "redirect:main";
 	}
	
	@RequestMapping(value="/main")
	public String main(HttpSession session) {

		Log log = (Log)session.getAttribute("log");
		if(log != null) {
			if(log.role == "Admin") {
				
				return "redirect:admin";
			}else {
				session.getCreationTime();
				return "redirect:employee";
			}
		} 
		else {
			return "redirect:notlogin";
		}			
	}
	@RequestMapping(value="/notlogin")
	public ModelAndView notlogin() {
		ModelAndView model = new ModelAndView("notLoged");
		return model;			
	}
	

	@RequestMapping(value="/logout")
	public String logout(HttpSession session) throws ParseException {
		Log log = (Log) session.getAttribute("log");
		if(log != null ) {
			if (log.day.temp==0) {	
				log.day.endTime=System.currentTimeMillis();
				log.day.timeSpend=log.day.endTime - log.day.start ;
				dio.updateDay(log.day);
			}
			else {
				log.day.endTime=System.currentTimeMillis();	
				log.day.timeSpend=(log.day.endTime - log.day.temp)+log.day.timeSpend ;
				log.day.temp=0;
				dio.updateDay(log.day);
			}
			//MOHAMAD code  pasuse Task when logout 
			List<Task> tasks=(List<Task>) dio.getTasks();
			 
			 for (Task task : tasks ) {
				 if(task.employee != null) {
					 if (task.employee.id == log.employee.id && task.status.equals("Started"))  {
					    	task.status = "Paused";
					    	Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
					    	task.timespend = task.timespend + ( timestamp.getTime() - task.timetemp);
					    	dio.updateTask(task);
					    	//update Project 
					    	Project project=dio.getProject(task.project.id);
					        long sum=0;
					        List<Task> tasks2=dio.getTasks();
					        for(Task task1:tasks2) { 
					        	if (task1.project.id==project.id) {
					        		sum=sum + task1.timespend;
					        		System.out.println("sum"+sum);
					        	}
					        }
					        project.timeSpend=sum;
					        dio.updateProject(project);
					    	//end Update project
					 }   	
				 }
			 }
		session.removeAttribute("log");
		}
		return "redirect:login";			
	}
	
	@RequestMapping(value="/admin")
	public ModelAndView adminpage(HttpSession session) {
		Log log = (Log)session.getAttribute("log");
		if(log != null && log.role == "Admin") {	
	 		ModelAndView model = new ModelAndView("admin");
	 	     List<Project>projects = dio.getProjects();
	 		 List<Employee>employees = dio.getEmployees();
	 	    model.addObject("projects", projects);	
	 	    model.addObject("employees", employees);
	 	    model.addObject("log.role",log.role);
	 	   return model;
		}
		else {
	 		ModelAndView model2 = new ModelAndView("notLoged");
	 		return model2;
		}
 	}	   
	 @SuppressWarnings("unused")
	@RequestMapping(value="/getemployee")
	public ModelAndView emp(HttpSession session,@RequestParam(value="id", required=true) int  id) throws ParseException {
		Log log = (Log)session.getAttribute("log");
		if(log != null && log.role == "Admin") {
			ModelAndView model = new ModelAndView("mangeemployee");
			Employee employee = dio.getEmployee(id);
			List<TimeOff> timesOff=dio.getTimesOffByEmployeeId(employee.id);
		    Day day= new Day();
			long sum=0;
			List<Day> days =(List<Day>) dio.selectEmployeesWorkTimeForPeriod( day.firstDayInCurrentMonth() , day.lastDayInCurrentMonth() , employee.id); 
		    for ( Day day1:days) {
		    	sum = sum + day1.timeSpend;
		    }
			model.addObject("timesOffList",timesOff);
			model.addObject("employee", employee);
			model.addObject("days", days);
			model.addObject("sum", sum);
			return model;
		}
		else {
	 		ModelAndView model2 = new ModelAndView("notLoged");
	 		return model2;
		}
	}	
	
	@RequestMapping(value="/gettimeworkinperiode")
	public ModelAndView timesworkinperiode(HttpSession session,@RequestParam(value="id", required=true) int  id,@RequestParam(value="date1", required=true) char[]  date1,@RequestParam(value="date2", required=true)char[]  date2) throws ParseException {
		Log log = (Log)session.getAttribute("log");
		if(log != null && log.role == "Admin") {
			
			ModelAndView model = new ModelAndView("mangeemployeeinPeriode");
			Employee employee = dio.getEmployee(id);
			
			List<TimeOff> timesOff=dio.getTimesOffByEmployeeId(employee.id);
			
		    Day day=new Day();
		    String d1= String.valueOf(date1, 0, 10);
		    String d2= String.valueOf(date2, 0, 10);
		    String d3=d1+" "+0+":"+0;
		    String d4=d2+" "+0+":"+0;
		    long periodeFrom= day.toMillisecond(d3);
		    long periodeTo  = day.toMillisecond(d4);
		  
			List<Day> days =(List<Day>) dio.selectEmployeesWorkTimeForPeriod( periodeFrom , periodeTo ,employee.id);
			
		    model.addObject("timesOffList",timesOff);
			model.addObject("employee", employee);
			model.addObject("days", days);
			model.addObject("d1", d1);
			model.addObject("d2", d2);
			return model;
		}
		else {
	 		ModelAndView model2 = new ModelAndView("notLoged");
	 		return model2;
		}
	}	
	
	@RequestMapping(value = "/addemployee" ,method = RequestMethod.POST)
	 public String addEmployee(@ModelAttribute("employee") Employee employee) {
	    dio.addEmployee(employee);
	    return "redirect:admin";	
	}
	@RequestMapping(value = "/updateemployee" ,method = RequestMethod.POST)
	 public String updateEmployee(@ModelAttribute("employee") Employee employee) {
	     if(null != employee )
	        dio.updateEmployee(employee);
	     return "redirect:admin";
	}
	
	@RequestMapping(value="/deleteemployee")
    public String delete(@RequestParam(value="id", required=true) int employeeId) {    
        dio.deleteEmployee(employeeId);
        return "redirect:admin";		 
    }
	
	@RequestMapping(value="/getproject")
	public ModelAndView project(HttpSession session,@RequestParam(value="id", required=true) int  id) {
		
		Log log = (Log)session.getAttribute("log");
		if(log != null && log.role == "Admin") {
			ModelAndView model1 = new ModelAndView("project");
			Project project = dio.getProject(id);
			List<Suggestion> suggestions=dio.getSuggestions();
			List<Employee> employees=dio.getEmployees();
			model1.addObject("project", project);
			model1.addObject("suggestions", suggestions);
			model1.addObject("employees", employees);
			return model1;	
		}
		else {
	 		ModelAndView model2 = new ModelAndView("notLoged");
	 		return model2;
		}
	}	
	
	@RequestMapping(value = "/updateproject" ,method = RequestMethod.POST)	
	 public String update(@ModelAttribute("project") Project project) {	
		System.out.println(project.id);
	     if(null != project ) {	
	        dio.updateProject(project);
	     }
	     return "redirect:admin";		     	
    }
	
	@RequestMapping(value = "/addproject" ,method = RequestMethod.POST)
	 public String addProject(@ModelAttribute("project") Project project) {
		project.status="New";
        project.timeSpend=0;
	    dio.addProject(project); 
	    return "redirect:admin";
	} 

	@RequestMapping(value="/deleteproject")
    public String deleteproject(@RequestParam(value="id", required=true) int id) {
        dio.deleteProject(id);
        return "redirect:admin";	 
    }
	@RequestMapping(value="/projectsList")
    public ModelAndView projectsList() {	
    	List<Project> getProjects = dio.getProjects();
    	ModelAndView model = new ModelAndView("projectsList");
        model.addObject("getProjects", getProjects );
        return model;
    }
	

	@RequestMapping(value = "/addnewtask" ,method = RequestMethod.POST)

	 public String addtask(@ModelAttribute("task") Task task) {
		task.status="New";
	    dio.addTask(task); 
	  
	    return "redirect:getproject?id="+task.project.id;
	}
	@RequestMapping(value="/del&updatetask",method = RequestMethod.POST,params = { "delete" })
    public String  deletetask(@ModelAttribute("task") Task task) {
        dio.deleteTask(task.id);
        return "redirect:getproject?id="+task.project.id;
    } 
    
    @RequestMapping(value = "/del&updatetask",method = RequestMethod.POST,params = { "update" })
    public String updatetask(@ModelAttribute("task") Task task) {
        System.out.println(task.getTitle());
        if (task.status.equals("New")) {
        	dio.updateTask(task);
        }
        else{
	        Task task2=new Task();
	        task2=dio.getTask(task.id);
	        if (task2.employee.name != null) {
	        	task.employee=task2.employee;
	        	task.timetemp=task2.timetemp;
	        	System.out.println("task.employee.id"+task.employee.id);
	        	System.out.println("task.temp"+task.timetemp);
	        	dio.updateTask(task);
            }
        }    
        
        return "redirect:getproject?id="+task.project.id;
        }
    
    @RequestMapping(value="/makesuggestion",method = RequestMethod.GET)
    public ModelAndView  signEmployeeToTasktask(@RequestParam(value="taskId", required=true) int  taskId,@RequestParam(value="projectId", required=true) int projectId) {
    	ModelAndView model = new ModelAndView("gettask");
	    Task task1=dio.getTask(taskId);
	    Project project1=dio.getProject(projectId);
   	    List<Employee> employees = dio.getEmployees();
   	    List<Task> tasks=dio.getTasks();
   	    List<Project> projects=dio.getProjects();
   	    List<Suggestion> suggestions=dio.getSuggestions();
	    model.addObject("suggestions", suggestions);
        model.addObject("employees", employees);
        model.addObject("projects", projects);
        model.addObject("tasks", tasks);
	    model.addObject("task1", task1);
	    model.addObject("project1", project1);
        return model;
    }
    
    @RequestMapping(value="/signemployeetotask",method = RequestMethod.GET)
    public String  signemployeetotask(@RequestParam(value="taskId", required=true) int  taskId,@RequestParam(value="projectId", required=true) int projectId,@RequestParam(value="employeeId", required=true) int employeeId){    	
        Suggestion suggestion =new Suggestion();
 	    suggestion.employee_id=employeeId;
 	    suggestion.project_id=projectId;
 	    suggestion.task_id=taskId;
 	    dio.addSuggestion(suggestion);
 	    return "redirect:getproject?id="+projectId;
        //return "redirect:makesuggestion?taskId="+taskId+"&projectId="+projectId;
    } 
    @RequestMapping(value="/deletesuggestion",method = RequestMethod.GET)
	public String  deleteSuggestion(@RequestParam(value="id", required=true) int  id,@RequestParam(value="taskId", required=true) int  taskId,@RequestParam(value="projectId", required=true) int projectId) {
		System.out.print("taskId="+taskId);
		System.out.print(projectId);
    	dio.deleteSuggestion(id);
	    return "redirect:makesuggestion?taskId="+taskId+"&projectId="+projectId;
	}
    
    @RequestMapping(value = "/mangeemployee" ,method = RequestMethod.POST,params = { "update" })
	 public String updateEmployeeInMangeEmplyeePage(@ModelAttribute("employee") Employee employee) {
	   if(null != employee )
	      dio.updateEmployee(employee);
	   return "redirect:getemployee?id="+employee.id;
	}
    @SuppressWarnings("null")
	@RequestMapping(value = "/mangeemployee" ,method = RequestMethod.POST,params = { "signToAdmin" })
	 public String signToAdmin(@ModelAttribute("employee") Employee employee) {
    	Admin admin=new Admin();
    	List<Admin> admins =  dio.getAdminsByEmployeeId(employee.id);
    	for(Admin ad : admins) {
    		admin=ad;
    	}
    	if (admin.id==0) {
	    	admin.employee_id=employee.id;
			dio.addAdmin(admin);
    	}
	   return "redirect:getemployee?id="+employee.id;
    }
   @RequestMapping(value="/managetimeoff",method = RequestMethod.GET)
	public ModelAndView  manageTimeOff(@RequestParam(value="employeeId", required=true) int  employeeId ){
	   List<TimeOff> timesOff=dio.getTimesOffByEmployeeId(employeeId);
	   ModelAndView model = new ModelAndView("manageTimeOff");
	   model.addObject("timesOff", timesOff);
	    return model;
	}
   @RequestMapping(value="/deletetimeoff",method = RequestMethod.POST)
   public String deleteTimeOff(@ModelAttribute("timeOff") TimeOff timeOff) {
	   System.out.println("id"+timeOff.id+"    -employeeId"+timeOff.employee_id);
       dio.deleteTimeOff(timeOff.id);
       return "redirect:managetimeoff?employeeId="+timeOff.employee_id;	
   }
   
   @InitBinder
   public void initBinder(WebDataBinder binder) {
       SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
       sdf.setLenient(true);
       binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
   }
   @RequestMapping(value="/addtimeoff",method = RequestMethod.POST)
   public String addTimeOff(@RequestParam(value="employeeId", required=true) int  employeeId,@RequestParam(value="date1", required=true) char[]  date1,@RequestParam(value="date2", required=true) char[]  date2  ) throws ParseException {  
	   Day day=new Day();
	   TimeOff timeOff=new TimeOff();
	   
	   String d1=String.valueOf(date1, 0, 10);
	   String t1=String.copyValueOf(date1, 11, 5);
	   
	   String d2=String.valueOf(date2, 0, 10);
	   String t2=String.copyValueOf(date2, 11, 5);
	   
	   timeOff.from = day.toMillisecond(d1+" "+t1);
	   timeOff.to = day.toMillisecond(d2+" "+t2);
	   timeOff.employee_id= employeeId;
	   
       dio.addTimeOff(timeOff);
       return "redirect:managetimeoff?employeeId="+timeOff.employee_id;	
   }
   @RequestMapping(value="/allsuggestions",method = RequestMethod.GET)
   public ModelAndView  allsuggestion(){    	
	   ModelAndView model = new ModelAndView("allsuggestions");
		List<Project> projects = dio.getProjects();
		List<Suggestion> suggestions=dio.getSuggestions();
		List<Employee> employees=dio.getEmployees();
		List<Task> tasks =dio.getTasks();
		model.addObject("projects", projects);
		model.addObject("suggestions", suggestions);
		model.addObject("employees", employees);
		model.addObject("tasks", tasks);
		return model;	
	}	
   
   
		// End 	MOHAMAD

   
   @RequestMapping(value="/test")
   public ModelAndView test() {	
   	ModelAndView model = new ModelAndView("test");
   	
   	long date1=1523433777000L;
   	long date2=1533499999000L;
   	int employeeId =3;
   	List<Day> days =(List<Day>) dio.selectEmployeesWorkTimeForPeriod( date1 , date2 , employeeId);
  
		for (Day day : days) {
			
			System.out.println("from"+day.start);
			System.out.println("from"+day.endTime);
		}
   
       return model;
       
   }
		
	
	@RequestMapping(value="/")

	public ModelAndView getProject() {
		ModelAndView model = new ModelAndView("index");
		Project project = new Project();
		model.addObject("project", project);
		return model;	
	}
	
	
	  
//Gab start
   
		
	@RequestMapping(value="/getTask")
	public ModelAndView getTask(@RequestParam(value="id", required=true) int id) {
		System.out.println(id);
		ModelAndView model = new ModelAndView("admin");
		Task task = dio.getTask(id);
		
		model.addObject("task", task);
		return model;			
	}
 
    
    @RequestMapping(value="/tasksList")
    public ModelAndView tasksList() {	
    	List<Task> getTasks = dio.getTasks();
    	ModelAndView model = new ModelAndView("projectsList");
        model.addObject("getTasks", getTasks );
        return model;
    }
    
    @RequestMapping(value="/loginasemployee")
	public ModelAndView loginAsEmployee(HttpSession session) {
    	Log log = (Log)session.getAttribute("log");
    	ModelAndView model = new ModelAndView("employee");
    	log.role ="Employee";
    	return model;
    }
   
    @RequestMapping(value="/employee")
	public ModelAndView employee(HttpSession session) {
    	Log log = (Log)session.getAttribute("log");
    	if(log != null && log.role == "Employee" ) {	
			ModelAndView model = new ModelAndView("employee");
			Task task = new Task();
			List<Project> getProjects = dio.getProjectsByEmployeeId(log.employee.id);
			model.addObject("task", task);
		    model.addObject("getProjects", getProjects);
			return model;			
	    }
    	else {
	 		ModelAndView model2 = new ModelAndView("notLoged");
	 		return model2;
	    }
	}	   
   

    @RequestMapping(value="/checkStatus")
    public ModelAndView checkstatus() {
    	ModelAndView model = new ModelAndView("checkStatus");
    	List<Task> tasks = dio.checkStatus("new");
    	model.addObject("tasks", tasks);
    	
		return model;

    	
    }
    @RequestMapping(value="/start")
    public String start(@RequestParam(value="id", required=true) int id, HttpSession session) {
    	Log log = (Log)session.getAttribute("log");
    	if(log != null) {
    		
    //MOHAMAD Code (this code for pause task when another task was Started)
    		List<Task> tasks=(List<Task>) dio.getTasks();
			 for (Task task : tasks ) {
				 if(task.employee != null) {
					 if (task.employee.id == log.employee.id && task.status.equals("Started"))  {
					    	task.status = "Paused";
					    	Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
					    	task.timespend = task.timespend + ( timestamp.getTime() - task.timetemp);
					    	dio.updateTask(task);
					   //update Project 
					    	Project project=dio.getProject(task.project.id);
					        long sum=0;
					        List<Task> tasks2=dio.getTasks();
					        for(Task task1:tasks2) { 
					        	if (task1.project.id==project.id) {
					        		sum=sum + task1.timespend;
					        		System.out.println("sum"+sum);
					        	}
					        }
					        project.timeSpend=sum;
					        dio.updateProject(project);
					  	//end Update project
					 }   	
				 }
			 }
   	//end MOHAMAD Code
			 
	    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    	Task task = dio.getTask(id);
	    	task.employee = log.employee;
	    	System.out.println("the id: "+log.employee.id);
	    	if(task.status != "Started") {
	    		if(task.status.equals("New") ) {
	    			task.started = timestamp.getTime(); 
	    		}
	        	task.status = "Started";
	        	task.timetemp = timestamp.getTime();
	        	
	// MOHAMAD Code     	
            //update Project
	        	Project project=dio.getProject(task.project.id);
	            project.status="Started";
	            dio.updateProject(project);
	        //delete Suggestion when employee choice  task
	            Suggestion suggestion = dio.getSuggestionByTaskId(task.id);
	            if ( suggestion !=null) {
		            dio.deleteSuggestion(suggestion.id);
	            }
	 //end  MOHAMAD Code
	    	}
        	dio.updateTask(task);    		
	    	return "redirect:employee"; 
    	}
    	else 
        	return "redirect:/"; 
    		
    }
    @RequestMapping(value="/pause")
    public String pause(@RequestParam(value="id", required=true) int id) {
    	Task task = dio.getTask(id);
    	task.status = "Paused";
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
    	task.timespend = task.timespend + ( timestamp.getTime() - task.timetemp);
    	dio.updateTask(task);
    	
    	//update Project MOHAMAD code
    	Project project=dio.getProject(task.project.id);
        
        long sum=0;
        List<Task> tasks=dio.getTasks();
        for(Task task1:tasks) { 
        	if (task1.project.id==project.id) {
        		sum=sum + task1.timespend;
        		System.out.println("sum"+sum);
        	}
        }
        project.timeSpend=sum;
        dio.updateProject(project);
    	//end Update project MOHAMAD code
        
    	return "redirect:employee";    	
    }
    @RequestMapping(value="/finish")
    public String finish(@RequestParam(value="id", required=true) int id) {
    	Task task = dio.getTask(id);
    	task.status = "Finished";
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 

		task.finish = timestamp.getTime();  
    	task.timespend = task.timespend + ( timestamp.getTime() - task.timetemp);
    	dio.updateTask(task);
    	
    	//update Project MOHAMAD code
    	
    	Project project=dio.getProject(task.project.id);
        long sum=0;
        int j=0,i=0;
        List<Task> tasks=dio.getTasks();
        for(Task task1:tasks) { 
        	if (task1.project.id==project.id) {
        		sum=sum + task1.timespend;
        		j=j+1;
        		System.out.println("sum"+sum);
        		if (task1.status.equals("Finished")) {
        			i=i+1;
        		}
        	}
        }
        if (i==j) {
        	project.status="Finished";
        }
        project.timeSpend=sum;
        dio.updateProject(project);
    	//end Update project MOHAMAD code
        
    	return "redirect:employee";    	
    }
    
//Gab Endline
  
    //end
}

