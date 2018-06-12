package em.controller;


import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	 	   return model;
		}
		else {
	 		ModelAndView model2 = new ModelAndView("notLoged");
	 		return model2;
		}
 	}	   
	
	@RequestMapping(value="/getemployee")
	public ModelAndView emp(@RequestParam(value="id", required=true) int  id) {
		ModelAndView model = new ModelAndView("mangeemployee");
		Employee employee = dio.getEmployee(id);
		List<TimeOff> timesOff=dio.getTimesOffByEmployeeId(employee.id);
	    List<TimeOff> timesOffList= new ArrayList<TimeOff>();
	    for(TimeOff timeOff :timesOff) {
			timeOff.from=TimeUnit.SECONDS.toMillis(timeOff.from );
			timeOff.to=TimeUnit.SECONDS.toMillis(timeOff.to );
			timesOffList.add(timeOff);
		}
	    List<Day> days=dio.getDayByEmployeeId(employee.id);
	    model.addObject("timesOffList",timesOffList);
		model.addObject("employee", employee);
		model.addObject("days", days);
		return model;
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
	public ModelAndView project(@RequestParam(value="id", required=true) int  id) {
		ModelAndView model = new ModelAndView("project");
		Project project = dio.getProject(id);
		List<Suggestion> suggestions=dio.getSuggestions();
		List<Employee> employees=dio.getEmployees();
		model.addObject("project", project);
		model.addObject("suggestions", suggestions);
		model.addObject("employees", employees);
		return model;	
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
        project.timeSpend="0";
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
        if(null != task )
        dio.updateTask(task);
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
    /*
    @RequestMapping(value = "/mangeemployee" ,method = RequestMethod.POST,params = { "workTimes" })
	 public String showWorkTimes(@ModelAttribute("employee") Employee employee) {
	   //if(null != employee )
	   return "redirect:getemployee?id="+employee.id;
	}
    @RequestMapping(value = "/mangeemployee" ,method = RequestMethod.POST,params = { "timeOff" })
	 public String showTimeOff(@ModelAttribute("employee") Employee employee) {
	  // if(null != employee )
	   return "redirect:getemployee?id="+employee.id;
	}
   
     */
		// End 	MOHAMAD
		

		
	
	@RequestMapping(value="/")

	public ModelAndView getProject() {
		ModelAndView model = new ModelAndView("index");
		Project project = new Project();
		model.addObject("project", project);
		return model;	
	}
	
	
    //Ikram + gab


	@RequestMapping(value="/getSuggestion")
	public ModelAndView getSuggestion(@RequestParam(value="id", required=true) int id) {
		System.out.println(id);
		ModelAndView model = new ModelAndView("suggestion");
		Suggestion suggestion = dio.getSuggestion(id);
	//	Project project = dio.getProject(suggestion.project.id);
		Task task = dio.getTask(suggestion.task_id);
		model.addObject("suggestion", suggestion);
		//model.addObject("project", project);
		model.addObject("task", task);
		return model;			
	}
	
	@RequestMapping(value = "/addSuggestion", method = RequestMethod.POST)
	public ModelAndView addSuggestion(@ModelAttribute("suggestion") Suggestion suggestion) {
		System.out.println(suggestion);
		dio.addSuggestion(suggestion);
		ModelAndView model = new ModelAndView("index");
		suggestion=new Suggestion();
		model.addObject("suggestion", suggestion);
		List<Suggestion> getSuggestions = dio.getSuggestions();
		model.addObject("getSuggestions", getSuggestions);
		return model;			
	}
//end ikram

//gab
	@RequestMapping(value="/deleteSuggestion")
	public String  deleteSuggestion(@RequestParam(value="id", required=true) int id) {
		dio.deleteSuggestion(id);
	    return "redirect:suggestionList";
	}

	@RequestMapping(value = "/updateSuggestion", method = RequestMethod.POST)
	public ModelAndView updateSuggestion(@ModelAttribute("suggestion") Suggestion suggestion) {
		if(null != suggestion )
	    dio.updateSuggestion(suggestion);
	    ModelAndView model = new ModelAndView("index");
	    suggestion=new Suggestion();
	    model.addObject("suggestion", suggestion);
	    List<Suggestion> getSuggestions = dio.getSuggestions();
	    model.addObject("getSuggestions", getSuggestions);
	    return model;
	}
	
	@RequestMapping(value="/suggestionList")
	public ModelAndView SuggestionsList1() {	
		List<Suggestion> getSuggestions = dio.getSuggestions();
		List<Project> getProjects = dio.getProjects();
		List<Task> getTasks = dio.getTasks();
 	    ModelAndView model = new ModelAndView("suggestionList");
	    model.addObject("getProjects", getProjects);
	    model.addObject("getTasks", getTasks);
	    model.addObject("getSuggestions", getSuggestions );
	    return model;
	}
//gab endline
	  
//Gab start
   
		
	@RequestMapping(value="/getTask")
	public ModelAndView getTask(@RequestParam(value="id", required=true) int id) {
		System.out.println(id);
		ModelAndView model = new ModelAndView("admin");
		Task task = dio.getTask(id);
		
		model.addObject("task", task);
		return model;			
	}
 
	//Nidal code
		
	@RequestMapping(value="/day")
	public ModelAndView getDay() {
		ModelAndView model = new ModelAndView("day");
		Day day = new Day();
		model.addObject("day", day);
		return model;			
	}
	
	@RequestMapping(value="/getDay")
	public ModelAndView getDay(@RequestParam(value="id", required=true) int id) {
		System.out.println(id);
		ModelAndView model = new ModelAndView("day");
		Day day = dio.getDay(id);
		model.addObject("day", day);
		return model;			
	}

	@RequestMapping(value = "/addDay", method = RequestMethod.POST)
	public ModelAndView addDay(@ModelAttribute("day") Day day) {
		System.out.println(day.getDate());
		dio.addDay(day);
		ModelAndView model = new ModelAndView("day");
		day=new Day();
		model.addObject("day", day);
		List<Day> getDays = dio.getDays();
		model.addObject("getDays", getDays);
		return model;			
	}
	
	 @RequestMapping(value="/deleteDay")
	    public String  deleteProject(@RequestParam(value="id", required=true) int id) {
	        dio.deleteDay(id);
	        return "redirect:dayList";
	    }
	    
	    @RequestMapping(value = "/updateDay", method = RequestMethod.POST)
	    public ModelAndView updateProject(@ModelAttribute("day") Day day) {
	        System.out.println(day.getDate());
	        if(null != day )
	        dio.updateDay(day);
	        ModelAndView model = new ModelAndView("day");
	        day=new Day();
	        model.addObject("day", day);
	        List<Day> getDays = dio.getDays();
	        model.addObject("getDays", getDays);
	        return model;
	    }
	    
	    @RequestMapping(value="/DaysList")
	    public ModelAndView daysList() {	
	    	List<Day> getDays = dio.getDays();
	    	ModelAndView model = new ModelAndView("DaysList");
	        model.addObject("getDays", getDays );
	        return model;
	    }
	
	    
	    
	    @RequestMapping(value="/test")
	    public ModelAndView test() {	
	    	ModelAndView model = new ModelAndView("test");
	    	Admin a = new Admin();
	    	a.employee_id = 1;
	    	//dio.addAdmin(a);
	    	List<Admin> admins =  dio.getAdminsByEmployeeId(1);
 			for (Admin ad : admins) {
 				System.out.println("adminget"+ad.id);
 			}
	    	System.out.println(a.id);
	        return model;
	    }
    
    @RequestMapping(value="/tasksList")
    public ModelAndView tasksList() {	
    	List<Task> getTasks = dio.getTasks();
    	ModelAndView model = new ModelAndView("projectsList");
        model.addObject("getTasks", getTasks );
        return model;
    }
    
    @RequestMapping(value="/employee")
	public ModelAndView employee(HttpSession session) {
    	Log log = (Log)session.getAttribute("log");
    	if(log != null && log.role == "Employee") {	
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
   
	
		
    
	    @RequestMapping(value="/SuggestionsList")
	    public ModelAndView SuggestionsList() {	
	    	List<Suggestion> getSuggestions = dio.getSuggestions();
	    	ModelAndView model = new ModelAndView("suggestionsList");
	        model.addObject("getSuggestions", getSuggestions );
	        return model;
	    }
   //gab+ ikram endline


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
    	return "redirect:employee";    	
    }
    
//Gab Endline
  
    //end
}

