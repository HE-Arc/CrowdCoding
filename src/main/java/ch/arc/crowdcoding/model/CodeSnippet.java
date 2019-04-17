package ch.arc.crowdcoding.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "codeSnippet")
public class CodeSnippet {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="codeSnippet_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Lob
	@Column(name = "content")
	private String content;
	
	@ManyToOne
    @JoinColumn
    private User owner;
	
	@CreatedDate
    Date created_at;
	
	@LastModifiedDate
    Date modified_at;
	
	@Column(name = "language")
	String language;
	
	@Column(name = "accessibility")
	String accessibility;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
