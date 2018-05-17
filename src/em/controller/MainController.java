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
	
	     if(null != project )
	        dio.updateProject(project);
	     return "redirect:admin";		     	
		}
	 

	@RequestMapping(value="/deleteproject")
    public String deleteproject(@RequestParam(value="id", required=true) int projectId) {
    
        dio.deleteEmployee(projectId);
        return "redirect:admin";	
	 
   }	

     
	
	@RequestMapping(value="/getemployee")
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
	    
	     return "redirect:admin";	
	    
		}
	 

	@RequestMapping(value="/deleteemployee")
    public String delete(@RequestParam(value="id", required=true) int employeeId) {
    
        dio.deleteEmployee(employeeId);
        return "redirect:admin";	
	 
   }
	// End 	MOHAMAD
	
	
	
	
}	
/*	
	
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
	
	
}
*/