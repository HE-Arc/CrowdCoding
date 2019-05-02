package ch.arc.crowdcoding.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.Language;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.repository.SnippetRepository;

@Service("snippetService")
public class SnippetServiceImpl implements SnippetService {

	@Autowired
	private SnippetRepository snippetRepository;

	@Override
	public Page<CodeSnippet> findByOwner(User owner, Pageable pageable) {
		return snippetRepository.findByOwner(owner, pageable);
	}

	@Override
	public Page<CodeSnippet> findByAccessibility(String accessibility, Pageable pageable) {
		return snippetRepository.findByAccessibility(accessibility, pageable);
	}

	@Override
	public CodeSnippet createNewSnippet(User owner, String name, Language language, String accessibility) {
		CodeSnippet snippet = new CodeSnippet();
		
		snippet.setOwner(owner);
		snippet.setAccessibility(accessibility);
		snippet.setLanguage(language);
		snippet.setName(name);
		snippet.setCreateAt(new Date());
		snippet.setModifiedAt(new Date());
		
		snippetRepository.save(snippet);
		
		return snippet;
	}

	@Override
	public CodeSnippet updateSnippet(Integer id, String name, String content, User owner, Language language, String accessibility) {
		CodeSnippet snippet = findById(id);

		if(snippet == null)
			return null;
		
		//snippet.setOwner(owner);
		snippet.setContent(content);
		snippet.setAccessibility(accessibility);
		snippet.setLanguage(language);
		snippet.setName(name);
		snippet.setModifiedAt(new Date());
		
		snippetRepository.save(snippet);
		return snippet;
	}

	@Override
	public CodeSnippet findById(Integer id) {
		Optional<CodeSnippet> oSnippet = snippetRepository.findById(id);
		
		if(oSnippet.isPresent())
			return oSnippet.get();
		else
			return null;
	}

	@Override
	public Page<CodeSnippet> findAll(Pageable pageable) {
		return snippetRepository.findAll(pageable);
	}

	@Override
	public boolean deleteSnippet(Integer id, User currentUser) {
		Optional<CodeSnippet> oSnippet = snippetRepository.findById(id);
		
		if(!oSnippet.isPresent())
			return false;
		CodeSnippet snippet = oSnippet.get();
		
		if(!(snippet.getOwner().getId().equals(currentUser.getId())))
			return false;
		
		snippetRepository.delete(snippet);
		return true;
	}
}
