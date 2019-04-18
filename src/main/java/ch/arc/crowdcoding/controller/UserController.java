package ch.arc.crowdcoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.service.SecurityService;
import ch.arc.crowdcoding.service.SnippetService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SnippetService snippetService;
    
    @RequestMapping(value = "/snippets")
    public ModelAndView getSnippets()
    {
    	//Current authenticated user
    	User currentUser = securityService.findLoggedInUser();
    	
    	Pageable pageable = PageRequest.of(0, 1);
    	Page<CodeSnippet> snippets = snippetService.findByOwner(currentUser, pageable);
    	
    	ModelAndView mav = new ModelAndView("user-snippets");
        mav.addObject("snippets", snippets);
    	
        return mav;
    }
    
}
