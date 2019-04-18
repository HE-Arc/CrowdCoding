package ch.arc.crowdcoding.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.User;

public interface SnippetService {
	
	Page<CodeSnippet> findByOwner(User owner, Pageable pageable);
	Page<CodeSnippet> findByAccessibility(String accessibility, Pageable pageable);
	CodeSnippet findById(Integer id);
	
	CodeSnippet createNewSnippet(User owner, String Name, String language, String accessibility);
	CodeSnippet updateSnippet(Integer id, String name, String content, User owner, String language, String accessibility);
}
