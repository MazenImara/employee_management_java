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
import em.model.User;


@Controller
public class MainController {
	@Autowired
	private Dio dio;

	@RequestMapping(value="/jon")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("index");
		User user = new User();
		user.name = "jon";
		user.age = 30;
		user.email = "jon@any.com";
		//dio.addUser(user);
		model.addObject("msg2", "");
		return model;			
	}
	
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView("index");
		System.out.println(user.name);
		dio.addUser(user);	
		model.addObject("msg", "added successfuly");
		return model;			
	}	
	
	@RequestMapping(value="/user")
	public ModelAndView user(@RequestParam(value="id", required=true) int  id) {
		System.out.println(id);
		ModelAndView model = new ModelAndView("user");
		User user = dio.getUser(id);
		model.addObject("user", user);
		return model;			
	}
	
	
//for project
//ikram
	@RequestMapping(value="/")
	public ModelAndView getProject() {
		ModelAndView model = new ModelAndView("index");
		Project project = new Project();
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