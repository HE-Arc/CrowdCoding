package ch.arc.crowdcoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.service.UserService;


@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/add")
    public String add(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    	
    	User n = new User();
		n.setName(name);
		n.setEmail("mail");
		model.addAttribute("name", n.getName());
		userService.saveUser(n);
        return "greeting";
    }
    
    @GetMapping("/me/snippets")
    public String getUserSnippets()
    {
    	return "user-snippets";
    }
   
    
	@GetMapping("/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userService.findAll();
	}
	
	@GetMapping("/show")
	public @ResponseBody User getUser(@RequestParam(name="name", required=true) String name) {
		// This returns a JSON or XML with the users
		return userService.findUserByName(name);
	}

}
