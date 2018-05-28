package em.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import em.model.Login;
import em.model.User;


@Controller
public class MainController {
	@Autowired
	
	private Dio dio;
	
	@RequestMapping(value="/")
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
	
	    
	    @RequestMapping(value="/login")
		public String login (HttpSession session, @RequestParam(value="email") String email, @RequestParam(value="password") String password)
		{
			Login employee=dio.checkLogin();
				if(employee!=null) {
				session.setAttribute("logged", employee);
			}
	     return "redirect:/login";
		}
		
		@RequestMapping(value="/logout")
		public String logout (HttpSession session, @RequestParam(value="email") String email, @RequestParam(value="password") String password)
		{
			
				session.removeAttribute("logged");
			
	     return "redirect:/login";
		}
	    
		@RequestMapping(value="/getemployee")
		public ModelAndView getemployee(@RequestParam(value="id", required=true) int  id) {
			ModelAndView model = new ModelAndView("employee");
			Employee employee = dio.getEmployee(id);
			model.addObject("employee", employee);
			return model;
		}	
		
		@RequestMapping(value = "/addemployee", method = RequestMethod.POST)
		public ModelAndView addEmployee(@ModelAttribute("employee") Employee employee) {
			System.out.println(employee.name);
			dio.addEmployee(employee);
			ModelAndView model = new ModelAndView("index");
			employee=new Employee();
			model.addObject("employee", employee);
			return model;			
				 
		}
}
		
  	    
	
