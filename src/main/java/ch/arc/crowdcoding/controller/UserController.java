package ch.arc.crowdcoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.service.SecurityService;
import ch.arc.crowdcoding.service.SnippetService;
import ch.arc.crowdcoding.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SnippetService snippetService;
	
	@Autowired
	private UserService userService;
    
    @RequestMapping(value = "/snippets")
    public ModelAndView getSnippets()
    {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User currentUser = userService.findUserByName(currentPrincipalName);
		
    	Pageable pageable = PageRequest.of(0, 5);
    	Page<CodeSnippet> snippets = snippetService.findByOwner(currentUser, pageable);
    	
    	ModelAndView mav = new ModelAndView("user-snippets");
        mav.addObject("snippets", snippets);
    	
        return mav;
    }
    
}
