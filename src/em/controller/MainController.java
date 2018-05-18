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
import em.model.Task;


@Controller
public class MainController {
	@Autowired
	private Dio dio;

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
//end ikram
    
    
//Gab start
    @RequestMapping(value="/")
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
    
    @RequestMapping(value="/projectsList")
    public ModelAndView tasksList() {	
    	List<Task> getTasks = dio.getTasks();
    	ModelAndView model = new ModelAndView("tasksList");
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