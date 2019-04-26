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
    
    @GetMapping("/error")
    public String error() {
        return "error";
    }

}
