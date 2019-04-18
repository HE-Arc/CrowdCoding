package ch.arc.crowdcoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.service.SecurityService;
import ch.arc.crowdcoding.service.SnippetService;
import ch.arc.crowdcoding.repository.SnippetRepository;
import ch.arc.crowdcoding.service.SnippetExecutionService;
import org.springframework.web.bind.annotation.RequestParam;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.service.UserService;

@Controller
@RequestMapping(value = "/snippets")
public class SnippetController {
	
	@Autowired
	private SnippetService snippetService;
	@Autowired
	private SnippetExecutionService snippetExecutor;
	
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;
	

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView snippetsList()
	{
		Pageable pageable = PageRequest.of(0, 10, Sort.by("created_at").descending());
    	Page<CodeSnippet> snippets = snippetService.findByAccessibility("public", pageable);
    	
    	ModelAndView mav = new ModelAndView("snippets/list");
    	mav.addObject("snippets", snippets);
    	
		return mav;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView getNewSnippetForm()
	{
		ModelAndView mav = new ModelAndView("snippets/new");
		mav.addObject("newCodeSnippet", new CodeSnippet());
		
		return mav;
	}
	
	//Add to db and redirect to modifiy
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RedirectView addNewSnippet(
			@RequestParam("snippet_name") String name,
			@RequestParam("snippet_language") String language,
			@RequestParam("snippet_accessibility") String accessibility)
	{
		//Current authenticated user 
		//Maybe check if currentUser is not defined ?
    	User currentUser = securityService.findLoggedInUser();
    	
    	CodeSnippet snippet = snippetService.createNewSnippet(currentUser, language, accessibility);
		
		return new RedirectView("/snippets/"+snippet.getId()+"/edit");
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveSnippet(@RequestParam("snippet_id") Integer id, @RequestParam("snippet_name") String name,
								@RequestParam("snippet_content") String content, @RequestParam("snippet_user") Integer user,
								@RequestParam("snippet_language") String language, @RequestParam("snippet_accessibility") String access)
	{    	
		
		CodeSnippet snippet= snippetService.updateSnippet(id, name, content, userService.findUserById(id), language, access);
		
		if(snippet==null)
			return new ModelAndView("error/404"); 
		
		return null;// "snippets/"+snippet.getId()+"/edit";
	}
	
	
	@RequestMapping("/{id}/edit")
	public ModelAndView getEditorSnippetForm(@PathVariable(value="id") Integer id)
	{
		CodeSnippet snippet = snippetService.findById(id);
		if(snippet == null)
			return new ModelAndView("error/404");
		
		ModelAndView mav = new ModelAndView("snippets/edit");
        mav.addObject("snippet", snippet);
        
		return mav;
	}
	
	@RequestMapping(value="{id}/execute", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody public String executeSnippet(@PathVariable(value="id") Integer id)
	{
		CodeSnippet codeSnippet = snippetService.findById(id);
		String jsonOutput = "";
		
		if(codeSnippet != null)
			jsonOutput = snippetExecutor.runSnippet(codeSnippet);
		return jsonOutput;
		/*String codeTxt = "print('asd')";
				
		
		CodeSnippet snippet = new CodeSnippet();
		snippet.setContent(codeTxt);
		
		String output = snippetExecutor.runSnippet(snippet);
		return output;*/
	}
}
