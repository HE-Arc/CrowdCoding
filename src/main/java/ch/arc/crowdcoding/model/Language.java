package ch.arc.crowdcoding.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "language")
public class Language {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="language_id")
	private int id;
	
	@Column(name="language")
	@NotEmpty(message = "*Please provide a name")
	private String language;
	
	@OneToMany(mappedBy = "language", cascade = CascadeType.ALL)
    private Set<CodeSnippet> codeSnippets;
	
	public int getId() {
		return id;
	}

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
