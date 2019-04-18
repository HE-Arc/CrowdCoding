package ch.arc.crowdcoding.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ch.arc.crowdcoding.model.CodeSnippet;
import ch.arc.crowdcoding.model.User;


@Repository
public interface SnippetRepository extends PagingAndSortingRepository<CodeSnippet, Integer> {
	
	Page<CodeSnippet> findByOwner(User owner, Pageable pageable);
	Page<CodeSnippet> findByAccessibility(String accessibility, Pageable pageable);
	
}