package ch.arc.crowdcoding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "codeSnippet")
public class CodeSnippet {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="codeSnippet_id")
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
