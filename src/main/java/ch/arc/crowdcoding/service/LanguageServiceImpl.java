package ch.arc.crowdcoding.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.Language;
import ch.arc.crowdcoding.model.User;
import ch.arc.crowdcoding.repository.LanguageRepository;
import ch.arc.crowdcoding.repository.SnippetRepository;

@Service("languageService")
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public Language findByLanguage(String language) {
		return languageRepository.findByLanguage(language);
	}

	@Override
	public Language addLanguage(String name) {
		Language l = new Language();
		l.setLanguage(name);
		
		l = languageRepository.save(l); 
		return l;
	}

	@Override
	public Iterable<Language> findAll() {
		return languageRepository.findAll();
	}

	
}
