package em.controller;






import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import em.dio.Dio;
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
}