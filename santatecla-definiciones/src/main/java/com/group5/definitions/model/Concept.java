package com.group5.definitions.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.ManyToOne;

@Entity
public class Concept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String conceptName;
	private String URL = "http://www.urldelconcepto.com";
	@ManyToOne
	@JoinColumn(name = "chapter_id")
	private Chapter chapterConcept;
	
	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE) //cannot uses fetch type eager
	@JoinColumn(name="answer_id")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Answer> answers = new ArrayList();
	
	protected Concept() {}

	public Concept(String conceptName, Chapter chapter) {
		super();
		this.conceptName = conceptName;
		this.chapterConcept = chapter;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public String toString() {
		return "Concept [id=" + id + ", conceptName=" + conceptName + ", URL=" + URL + ", answers=" + answers + "]";
	}
	
}
