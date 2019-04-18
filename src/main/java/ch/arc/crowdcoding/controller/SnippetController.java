package ch.arc.crowdcoding.controller;

import java.util.Optional;

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
import ch.arc.crowdcoding.repository.SnippetRepository;
import ch.arc.crowdcoding.service.SnippetExecutionService;
import org.springframework.web.bind.annotation.RequestParam;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.service.UserService;

@Controller
@RequestMapping(value = "/snippets")
public class SnippetController {
	
	@Autowired
	private SnippetExecutionService snippetExecutor;
	
	@Autowired
	private SnippetRepository snippetRepository;
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView snippetsList()
	{
		Pageable pageable = PageRequest.of(0, 10, Sort.by("created_at").descending());
    	Page<CodeSnippet> snippets = snippetRepository.findByAccessibility("public", pageable);
    	
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
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipal = authentication.getName();
    	User currentUser = userService.findUserByName(currentPrincipal);
    	
    	CodeSnippet codeSnippet = new CodeSnippet();
    	codeSnippet.setName(name);
    	codeSnippet.setLanguage(language);
    	codeSnippet.setAccessibility(accessibility);
		codeSnippet.setOwner(currentUser);
		codeSnippet.setContent("");
		codeSnippet = snippetRepository.save(codeSnippet);
		
		return new RedirectView("/snippets/"+codeSnippet.getId()+"/edit");
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveSnippet(@RequestParam("snippet_id") Integer id, @RequestParam("snippet_name") String name,
								@RequestParam("snippet_content") String content, @RequestParam("snippet_user") Integer user,
								@RequestParam("snippet_language") String language, @RequestParam("snippet_accessibility") String access)
	{    	
		Optional<CodeSnippet> oSnippet = snippetRepository.findById(id);
		if(!oSnippet.isPresent())
			return new ModelAndView("error/404"); 
		
		//Optional<User> oUser = userService.findUserById(user);
		
		CodeSnippet snippet = oSnippet.get();
		snippet.setName(name);
		snippet.setLanguage(language);
		snippet.setContent(content);
		snippet.setLanguage(language);
		snippet.setAccessibility(access);
		
		
		//snippet.setOwner(currentUser);
		snippet = snippetRepository.save(snippet);
		
		return null;// "snippets/"+snippet.getId()+"/edit";
	}
	
	
	@RequestMapping("/{id}/edit")
	public ModelAndView getEditorSnippetForm(@PathVariable(value="id") Integer id)
	{
		Optional<CodeSnippet> oSnippet = snippetRepository.findById(id);
		if(!oSnippet.isPresent())
			return new ModelAndView("error/404");
		
		ModelAndView mav = new ModelAndView("snippets/edit");
        mav.addObject("snippet", oSnippet.get());
        
		return mav;
	}
	
	@RequestMapping(value="{id}/execute", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody public String executeSnippet(@PathVariable(value="id") Integer id)
	{
		Optional<CodeSnippet> codeSnippet = snippetRepository.findById(id);
		String jsonOutput = "";
		if(codeSnippet.isPresent())
			jsonOutput = snippetExecutor.runSnippet(codeSnippet.get());
		return jsonOutput;
		/*String codeTxt = "print('asd')";
				
		
		CodeSnippet snippet = new CodeSnippet();
		snippet.setContent(codeTxt);
		
		String output = snippetExecutor.runSnippet(snippet);
		return output;*/
	}
}
