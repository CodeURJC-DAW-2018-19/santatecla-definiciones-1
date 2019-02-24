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
public class Concept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String conceptName;
	private String URL = "http://www.urldelconcepto.com";
	@OneToMany(mappedBy="concept", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Answer> answers = new ArrayList<>();
	
	protected Concept() {}

	public Concept(String conceptName) {
		super();
		this.conceptName = conceptName;
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
