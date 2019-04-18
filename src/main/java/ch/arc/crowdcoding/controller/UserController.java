package ch.arc.crowdcoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.repository.SnippetRepository;
import ch.arc.crowdcoding.repository.UserRepository;
import ch.arc.crowdcoding.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SnippetRepository snippetRepository;
    
    @RequestMapping(value = "/snippets")
    public ModelAndView getSnippets()
    {
    	//Current authenticated user
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipal = authentication.getName();
    	User currentUser = userService.findUserByName(currentPrincipal);
    	
    	CodeSnippet c = new CodeSnippet();
    	c.setContent("aajajajajaja");
    	c.setAccessibility("a");
    	c.setLanguage("C++");
    	c.setName("MySnippet");
    	c.setOwner(currentUser);
    	//snippetRepository.save(c);
    	
    	Pageable pageable = PageRequest.of(0, 1);
    	Page<CodeSnippet> snippets = snippetRepository.findByOwner(currentUser, pageable);
    	
    	ModelAndView mav = new ModelAndView("user-snippets");
        mav.addObject("snippets", snippets);
        mav.addObject("aa", "asldkmaléskd");
    	
        return mav;
    }
    
}
