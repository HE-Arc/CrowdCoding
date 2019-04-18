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
    
	
//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }
    
    
    @RequestMapping(value = "/snippets")
    public ModelAndView getSnippets()
    {
    	//Current authenticated user
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String currentPrincipal = authentication.getName();
    	User currentUser = userService.findUserByName(currentPrincipal);
    	
    	Pageable pageable = PageRequest.of(1, 1);
    	Page<CodeSnippet> snippets = snippetRepository.findByOwner(currentUser, pageable);
    	CodeSnippet c = new CodeSnippet();
    	c.setContent("aajajajajaja");
    	//c.setD
    	
    	ModelAndView mav = new ModelAndView("user-snippets");
        mav.addObject("snippets", snippets);
        mav.addObject("aa", "asldkmaléskd");
    	
    	//List<CodeSnippet> lSnippet = SnippetRepository.findAllByOwner(null, null);
    	//return "user-snippets";
        return mav;
    }
    
//	@RequestMapping(value="/snippets")
//	public String snippetsList()
//	{
//		return "user-snippets";
//	}
    
    
//	@GetMapping("/all")
//	public @ResponseBody Iterable<User> getAllUsers() {
//		// This returns a JSON or XML with the users
//		return userService.findAll();
//	}
//	
//	@GetMapping("/show")
//	public @ResponseBody User getUser(@RequestParam(name="name", required=true) String name) {
//		// This returns a JSON or XML with the users
//		return userService.findUserByName(name);
//	}

}
