package com.grupo5.definiciones.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Chapter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String chapterName;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Concept> concepts = new ArrayList<>();
	
	protected Chapter() {}

	public Chapter(String chapterName) {
		super();
		this.chapterName = chapterName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public List<Concept> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<Concept> concepts) {
		this.concepts = concepts;
	}

	@Override
	public String toString() {
		return "Chapter [id=" + id + ", chapterName=" + chapterName + ", concepts=" + concepts + "]";
	}
	
	
	
}
