package ch.arc.crowdcoding.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.repository.SnippetRepository;
import ch.arc.crowdcoding.service.SnippetExecutionService;
import ch.arc.crowdcoding.service.SnippetExecutionServiceImpl;

@Controller
@RequestMapping(value = "/snippets")
public class SnippetController {
	
	@Autowired
	private SnippetExecutionService snippetExecutor;
	
	@Autowired
	private SnippetRepository snippetRepository;
	

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
	
	@RequestMapping("/editor")
	public String getEditorSnippetForm()
	{
		return "snippets/editor";
	}
	
	@RequestMapping(value="{id}/execute", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody public String executeSnippet(@PathVariable(value="id") Integer id)
	{
		/*Optional<CodeSnippet> codeSnippet = snippetRepository.findById(id);
		String jsonOutput = "";
		if(codeSnippet.isPresent())
			jsonOutput = snippetExecutor.runSnippet(codeSnippet.get());
		return jsonOutput;*/
		String codeTxt = "print('asd')";
				
		
		CodeSnippet snippet = new CodeSnippet();
		snippet.setContent(codeTxt);
		
		String output = snippetExecutor.runSnippet(snippet);
		return output;
	}
}
