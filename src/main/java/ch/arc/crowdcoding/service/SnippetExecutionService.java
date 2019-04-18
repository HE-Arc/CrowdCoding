package ch.arc.crowdcoding.service;

import ch.arc.crowdcoding.model.CodeSnippet;

public interface SnippetExecutionService {

	public String runSnippet(CodeSnippet snippet);
}
