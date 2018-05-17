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

import em.model.User;


@Controller
public class MainController {
	@Autowired
	private Dio dio;

	@RequestMapping(value="/jon")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView("index");

//		User user = new User();
	    //user.name = "jon";
		//user.age = 30;
		//user.email = "jon@any.com";
		
		//dio.addUser(user);
		String msg2="hello in my program";
		model.addObject("msg2", msg2);
		return model;			
	}
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)

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
	
	
	//MOHAMAD CODE
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee) {
		ModelAndView model = new ModelAndView("index");
		dio.addEmployee(employee);
		  model.addObject("msg", "added succesufuly");		
	 		
	 		return model;	
	}
	


     
     
 	@RequestMapping(value="/employeeList")
 	public ModelAndView employees() {
 		ModelAndView model = new ModelAndView("employeeList");
 		
 		  List<Employee>employees = dio.listEmployees();
 		    
 	     model.addObject("employees", employees);		
 		
 		return model;	
 	
 	}	
     
	
	@RequestMapping(value="/getEmployee")
	public ModelAndView emp(@RequestParam(value="id", required=true) int  id) {

		ModelAndView model = new ModelAndView("employee");
		
		Employee employee = dio.getEmployee(id);
		
		
		model.addObject("employee", employee);
		return model;	
	
	}	
	@RequestMapping(value = "/updateemployee" ,method = RequestMethod.POST)
	
	 public String update(@ModelAttribute("employee") Employee employee) {
	
	     if(null != employee )
	        dio.updateEmployee(employee);
	    	// System.out.println(employee.id);
	     return "redirect:employeeList";	
	    	// employee=new Employee();
	    //	 ModelAndView model = new ModelAndView("index");
	  //  model.addObject("msg3", "uppdate Succsesfully");
	  //   return model;
	     	
		}
	 

	@RequestMapping(value="/deleteEmployee")
    public String delete(@RequestParam(value="id", required=true) int employeeId) {
    
        dio.deleteEmployee(employeeId);
       

      
        return "redirect:employeeList";	
	 
   }
	// End 	MOHAMAD
	
	
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
    

}