package com.group5.definitions.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE, mappedBy="chapter")
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

	public void removeConcept(Concept concept) {
		this.concepts.remove(concept);
	}
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", chapterName=" + chapterName + ", concepts=" + concepts + "]";
	}
	
	
	
}
