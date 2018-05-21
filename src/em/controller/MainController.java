package em.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@Controller
public class MainController {
	@Autowired
	private Dio dio;
	
	@RequestMapping(value="/admin")
 	public ModelAndView project() {
 		ModelAndView model = new ModelAndView("admin");
 		
 	     List<Project>projects = dio.getProjects();
 		 List<Employee>employees = dio.listEmployees();
 		    
 	    model.addObject("projects", projects);	
 	    model.addObject("employees", employees);
 		return model;	 	
 	}	     
	
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
        project.timeSpend=0;
	    dio.addProject(project); 
	    return "redirect:admin";
	} 

	@RequestMapping(value="/deleteproject")
    public String deleteproject(@RequestParam(value="id", required=true) int id) {
        dio.deleteProject(id);
        return "redirect:admin";	 
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
	
	@RequestMapping(value = "/addtask" ,method = RequestMethod.POST)
	
	 public String addtask(@ModelAttribute("task") Task task) {
		//Task task=new Task();
		task.status="New";
	    dio.addTask(task); 
	   return "redirect:getproject?id=" + task.project_id;
	} 
	// End 	MOHAMAD
	
	@RequestMapping(value="/")

	public ModelAndView getProject() {

		ModelAndView model = new ModelAndView("index");

		Project project = new Project();

		model.addObject("project", project);

		return model;			

	}
	
	/*
	 
	
	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public ModelAndView addProject(@ModelAttribute("project") Project project) {
		System.out.println(project.getTitle());
		dio.addProject(project);
		ModelAndView model = new ModelAndView("index");
		project=new Project();
		model.addObject("project", project);
		List<Project> getProjects = dio.getProjects();
		model.addObject("getProjects", getProjects);
		return model;			
	}
	
	 @RequestMapping(value="/deleteProject")
	    public String  deleteProject(@RequestParam(value="id", required=true) int id) {
	        dio.deleteProject(id);
	        return "redirect:projectList";
	    }
	    
	    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
	    public ModelAndView updateProject(@ModelAttribute("project") Project project) {
	        System.out.println(project.getTitle());
	        if(null != project )
	        dio.updateProject(project);
	        ModelAndView model = new ModelAndView("index");
	        project=new Project();
	        model.addObject("project", project);
	        List<Project> getProjects = dio.getProjects();
	        model.addObject("getProjects", getProjects);
	        return model;
	    }
	    
	    @RequestMapping(value="/projectsList")
	    public ModelAndView projectsList() {	
	    	List<Project> getProjects = dio.getProjects();
	    	ModelAndView model = new ModelAndView("projectsList");
	        model.addObject("getProjects", getProjects );
	        return model;
	    }
	*/
   
    
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
	    
	    @RequestMapping(value="/SuggestionsList")
	    public ModelAndView SuggestionsList() {	
	    	List<Suggestion> getSuggestions = dio.getSuggestions();
	    	ModelAndView model = new ModelAndView("suggestionsList");
	        model.addObject("getSuggestions", getSuggestions );
	        return model;
	    }
   //gab endline
	
//end ikram
    
}