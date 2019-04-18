package ch.arc.crowdcoding.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.Language;
import ch.arc.crowdcoding.model.User;


@Repository
public interface LanguageRepository extends PagingAndSortingRepository<Language, Integer> {
	
	Language findByLanguage(String language);
	
	
}