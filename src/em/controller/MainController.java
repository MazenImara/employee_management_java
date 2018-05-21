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
	// End 	MOHAMAD
	
	
	
	
}	
