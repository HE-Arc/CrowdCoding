package ch.arc.crowdcoding.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Temporal;

@Entity
@Table(name = "codeSnippet")
@EntityListeners(AuditingEntityListener.class)
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
	
	//@CreatedDate
	@Column(name = "createdAt")
    private Date createdAt;
	
	//@LastModifiedDate
	@Column
    private Date modifiedAt;
	
	@Column(name = "language")
	String language;
	
	@Column(name = "accessibility")
	String accessibility;
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getAccessibility() {
		return accessibility;
	}
	public void setAccessibility(String accessibility) {
		this.accessibility = accessibility;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreateAt(Date date) {
		this.createdAt = date;
	}
	
	public Date getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(Date date) {
		this.modifiedAt = date;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
}
