package ch.arc.crowdcoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.service.LanguageService;
import ch.arc.crowdcoding.service.SecurityService;
import ch.arc.crowdcoding.service.SnippetService;
import ch.arc.crowdcoding.service.UserService;


@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SnippetService snippetService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LanguageService languageService;
    

    @RequestMapping(value = "/languages")
    public ModelAndView getLanguages()
    {		    	
    	ModelAndView mav = new ModelAndView("admin/languages");
    	System.out.println(languageService.findAll());
        mav.addObject("languages", languageService.findAll());
    	
        return mav;
    }
    
    @RequestMapping(value = "/languages/add", method=RequestMethod.POST)
    public ModelAndView addLanguage(@RequestParam("language_name") String name)
    {		
    	languageService.addLanguage(name);
    	return new ModelAndView("redirect:/admin/languages");
    }
  

}
