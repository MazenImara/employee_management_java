package em.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import em.dio.Dio;
import em.model.Employee;
import em.model.Project;
import em.model.Suggestion;
import em.model.Task;
import em.model.User;

@Controller
public class MainController {
	@Autowired
	private Dio dio;


//for project
//ikram
	/*
	@RequestMapping(value="/")
	public ModelAndView getP() {
		ModelAndView model = new ModelAndView("index");
		Project project = dio.getProject(2);
		model.addObject("project", project);
		return model;			
	}
*/
	@RequestMapping(value="/login")
	public String login(HttpSession session) {
		
		User employee= new User();
		employee.name = "Harry";
		employee.email = "muu";
		
		if(employee != null) {
			session.setAttribute("logedEmployee", employee);
		}
		
		
		return "redirect:/";			
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("logedEmployee");
		
		return "redirect:/";			
	}
	
	
	//MOHAMAD CODE
	
	@RequestMapping(value="/admin")
 	public ModelAndView project() {
 		ModelAndView model = new ModelAndView("admin");
 		
 	     List<Project>projects = dio.getProjects();
 		 List<Employee>employees = dio.listEmployees();
 		    
 	    model.addObject("projects", projects);	
 	    model.addObject("employees", employees);
 		return model;	 	
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

	@RequestMapping(value = "/addtask1" ,method = RequestMethod.POST)
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
	
	// End 	MOHAMAD
	
	// Ikram Code
	
	 
		@RequestMapping(value="/getproject")
		public ModelAndView project(@RequestParam(value="id", required=true) int  id) {
			ModelAndView model = new ModelAndView("project");
			Project project = dio.getProject(id);
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
		
		//end Ikram
	
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
		Project project = dio.getProject(suggestion.project_id);
		Task task = dio.getTask(suggestion.task_id);
		model.addObject("suggestion", suggestion);
		model.addObject("project", project);
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
    @RequestMapping(value="/muu")
	public ModelAndView getTask() {
		ModelAndView model = new ModelAndView("index");
		Task task = new Task();
		model.addObject("task", task);
		return model;			
	}
		
	@RequestMapping(value="/getTask")
	public ModelAndView getTask(@RequestParam(value="id", required=true) int id) {
		System.out.println(id);
		ModelAndView model = new ModelAndView("admin");
		Task task = dio.getTask(id);
		
		model.addObject("task", task);
		return model;			
	}

	@RequestMapping(value = "/addTask", method = RequestMethod.POST)
	public ModelAndView addTask(@ModelAttribute("task") Task task) {
		System.out.println(task.getTitle());
		dio.addTask(task);
		ModelAndView model = new ModelAndView("index");
		task=new Task();
		model.addObject("task", task);
		List<Task> getTasks = dio.getTasks();
		model.addObject("getTasks", getTasks);
		return model;			
	}
	
    @RequestMapping(value="/deleteTask")
    public String  deleteTask(@RequestParam(value="id", required=true) int id) {
        dio.deleteTask(id);
        return "redirect:projectList";
    }
    
    @RequestMapping(value = "/updateTask", method = RequestMethod.POST)
    public ModelAndView updateTask(@ModelAttribute("task") Task task) {
        System.out.println(task.getTitle());
        if(null != task )
        dio.updateTask(task);
        ModelAndView model = new ModelAndView("index");
        task=new Task();
        model.addObject("task", task);
        List<Task> getTasks = dio.getTasks();
        model.addObject("getTasks", getTasks);
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
    
   
    
//Gab Endline

	    @RequestMapping(value="/SuggestionsList")
	    public ModelAndView SuggestionsList() {	
	    	List<Suggestion> getSuggestions = dio.getSuggestions();
	    	ModelAndView model = new ModelAndView("suggestionsList");
	        model.addObject("getSuggestions", getSuggestions );
	        return model;
	    }
   //gab+ ikram endline

    
}