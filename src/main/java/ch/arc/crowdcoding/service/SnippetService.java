package ch.arc.crowdcoding.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.Language;
import ch.arc.crowdcoding.model.User;

public interface SnippetService {
	
	Page<CodeSnippet> findAll(Pageable pageable);
	Page<CodeSnippet> findByOwner(User owner, Pageable pageable);
	Page<CodeSnippet> findByAccessibility(String accessibility, Pageable pageable);
	CodeSnippet findById(Integer id);
	
	boolean deleteSnippet(Integer id, User currentUser);
	
	CodeSnippet createNewSnippet(User owner, String Name, Language language, String accessibility);
	CodeSnippet updateSnippet(Integer id, String name, String content, User owner, Language language, String accessibility);
}
