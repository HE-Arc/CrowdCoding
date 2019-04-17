package ch.arc.crowdcoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/snippets")
public class SnippetController {
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String snippetsList()
	{
		return "snippets/list";
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String getNewSnippetForm()
	{
		return "snippets/new";
	}
}
