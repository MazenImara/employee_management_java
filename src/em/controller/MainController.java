package em.controller;


import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import java.sql.Timestamp;
import java.util.List;
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

import em.model.Day;
import em.model.Employee;
import em.model.Project;
import em.model.Suggestion;
import em.model.Task;




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
	@RequestMapping(value="/loginasadmin",method = RequestMethod.POST)
 	public String  login(HttpSession session,@RequestParam(value="email", required=true) String  email,@RequestParam(value="password", required=true) String  password) {
 		Employee employee=dio.checkLogin(email,password);
 		if (employee!=null) {
	 		session.setAttribute("logedEmployee", employee);
 		}
 		return "redirect:admin";
 	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("logedEmployee");
		
		return "redirect:login";			
	}
	
	@RequestMapping(value="/admin")
	public ModelAndView adminpage(HttpSession session) {

		
		if((Employee)session.getAttribute("logedEmployee") != null) {	
	 		ModelAndView model = new ModelAndView("admin");
	 	     List<Project>projects = dio.getProjects();
	 		 List<Employee>employees = dio.listEmployees();
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
		ModelAndView model = new ModelAndView("employee");
		Employee employee = dio.getEmployee(id);
		model.addObject("employee", employee);
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

	@RequestMapping(value = "/addnewtask" ,method = RequestMethod.POST)

	 public String addtask(@ModelAttribute("task") Task task) {
		task.status="New";
		task.employee = new Employee();
		task.employee.id =1;
		 System.out.println(task.employee.id);
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
    
    @RequestMapping(value="/gettask",method = RequestMethod.GET)
    public ModelAndView  signEmployeeToTasktask(@RequestParam(value="id", required=true) int  id,@RequestParam(value="projectId", required=true) int projectId) {
    	ModelAndView model = new ModelAndView("gettask");

	    Task task=dio.getTask(id);
   	    List<Employee>employees = dio.listEmployees();
   	    
        model.addObject("employees", employees);
	    model.addObject("task", task);
	    model.addObject("projectId", projectId);
       return model;
    }
    @RequestMapping(value="/signemployeetotask",method = RequestMethod.GET)
    public String  signemployeetotask(@RequestParam(value="id", required=true) int  id,@RequestParam(value="projectId", required=true) int projectId,@RequestParam(value="employeeId", required=true) int employeeId){
    	
    	Task task=dio.getTask(id);
    	 System.out.println(task.getTitle());
    	
    	 task.employee=dio.getEmployee(employeeId);
    	 
    	 dio.updateTask(task);
        return "redirect:getproject?id="+projectId;
    } 
    
    
	
	// End 	MOHAMAD
	
	// Ikram + MOHAMAD Code
	
	 
		@RequestMapping(value="/getproject")
		public ModelAndView project(@RequestParam(value="id", required=true) int  id) {
			ModelAndView model = new ModelAndView("project");
			Project project = dio.getProject(id);
			Task task = dio.getTask(47);
			model.addObject("task", task);
			model.addObject("project", project);
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
		
		//end Ikram+mohamad
	
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
	
	    /*
	    @RequestMapping(value="/login1")
		public String login (HttpSession session, @RequestParam(value="email") String email, @RequestParam(value="password") String password)
		{
			Login employee=dio.checkLogin();
				if(employee!=null) {
				session.setAttribute("logged", employee);
			}
	     return "redirect:/login";
		}
		
		@RequestMapping(value="/logout1")
		public String logout (HttpSession session, @RequestParam(value="email") String email, @RequestParam(value="password") String password)
		{
			
				session.removeAttribute("logged");
			
	     return "redirect:/login";
		}
	    
		*/
	    
	    @RequestMapping(value="/test")
	    public ModelAndView test() {	
	    	List<Task> getTasks = dio.getTasks();
	    	ModelAndView model = new ModelAndView("test");
	    	Task task = dio.getTask(47);
	    	System.out.println("test: "+task.employee.name);
	        model.addObject("task", task );
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
	public ModelAndView employee() {
		ModelAndView model = new ModelAndView("employee");
		Task task = new Task();
		List<Project> getProjects = dio.getProjects();
		model.addObject("task", task);
	    model.addObject("getProjects", getProjects);
		return model;			
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
    public String start(@RequestParam(value="id", required=true) int id) {
    	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	Task task = dio.getTask(id);
    	if(task.status != "Started") {
    		if(task.status.equals("New") ) {
    			task.started = timestamp.getTime();   
    			dio.updateTask(task);
    		}
        	task.status = "Started";
        	task.timetemp = timestamp.getTime();
        	dio.updateTask(task);    		
    	}
    	return "redirect:employee";    	
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

