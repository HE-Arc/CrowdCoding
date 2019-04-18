package ch.arc.crowdcoding.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.Language;
import ch.arc.crowdcoding.model.User;

public interface LanguageService {
	Language findByLanguage(String language);
	Language addLanguage(String name);
	Iterable<Language> findAll();
}
