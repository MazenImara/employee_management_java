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
import em.model.Project;
import em.model.Suggestion;
import em.model.Task;


@Controller
public class MainController {
	@Autowired
	private Dio dio;

//for project
//ikram
	@RequestMapping(value="/")
	public ModelAndView getP() {
		ModelAndView model = new ModelAndView("index");
		Project project = dio.getProject(2);
		model.addObject("project", project);
		return model;			
	}
		
	@RequestMapping(value="/getProject")
	public ModelAndView getProject(@RequestParam(value="id", required=true) int id) {
		System.out.println(id);
		ModelAndView model = new ModelAndView("project");
		Project project = dio.getProject(id);
		model.addObject("project", project);
		return model;			
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public ModelAndView addProject(@ModelAttribute("project") Project project) {
		System.out.println(project.getTitle());
		project.status="new";
		project.timeSpend="0";
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
	public ModelAndView SuggestionsList() {	
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
		ModelAndView model = new ModelAndView("task");
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
		model.addObject("task", task);
		return model;			
	}
    
   
    
//Gab Endline
    
    
}