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
import em.model.Day;
import em.model.User;
import simple.ex.models.Item;


@Controller
public class MainController {
	@Autowired
	private Dio dio;
	 
	@RequestMapping(value="/")
	public ModelAndView listUser() {
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
	
	@RequestMapping(value = "/addday", method = RequestMethod.POST)
	public ModelAndView addDay(@ModelAttribute("day") Day day) {
		ModelAndView model = new ModelAndView("index");
		System.out.println(day.date);
		dio.addDay(day);
		model.addObject("day", "added successfuly");
		return model;	
	}
		@RequestMapping(value="/day")
		public ModelAndView getDay(@RequestParam(value="id", required=true) int  id) {
			System.out.println(id);
			ModelAndView model = new ModelAndView("day");
			Day day = dio.getDay(id);
			model.addObject("day", day);
			return model;			
		}
		
	@RequestMapping(value="/daylist")
 	public ModelAndView listDay () {
 		ModelAndView model = new ModelAndView("dayList");
 		  List<Day>day = dio.listDay();
 	     model.addObject("day", day);		
 		return model;	
	}
	
   	@RequestMapping(value="/delete")
    public ModelAndView deleteDay(@RequestParam(value="id", required=true) int id) {
        ModelAndView model = new ModelAndView("delet Day");
        dio.deleteDay(id);
        model.addObject("msg","delet successfuly");
        return model;
}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("day") Day day) {
        System.out.println(day.date);
        if(null != day )
            dio.updateDay(day);

        ModelAndView model = new ModelAndView("Day update");
        day=new Day();
        model.addObject("day", day);
        List<Day> dayList = dio.listDay();
        model.addObject("day", day);
        return model;
	}
}

	
