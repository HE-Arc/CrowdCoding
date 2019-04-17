package ch.arc.crowdcoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/snippets")
public class SnippetController {
	
	@RequestMapping(value="/list")
	public String snippetsList()
	{
		return "snippets/list";
	}
	
	@RequestMapping("/new")
	public String getNewSnippetForm()
	{
		return "snippets/new";
	}
	
	@RequestMapping("/editor")
	public String getEditorSnippetForm()
	{
		
		return "snippets/editor";
	}
	
}
