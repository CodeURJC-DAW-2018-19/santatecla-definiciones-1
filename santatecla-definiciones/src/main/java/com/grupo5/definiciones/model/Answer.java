package com.grupo5.definiciones.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length = 10000)
	private String answerText;
	private boolean marked;
	private boolean correct;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Justification> justifications;
	@OneToOne
	private User user; //User that created this answer 
	@ManyToOne
	private Concept concept;
	@OneToMany(mappedBy="answer", cascade = CascadeType.ALL)
	private List<Question> questions;
	
	protected Answer() {
	}

	public Answer(String answerText, boolean marked, User user) {
		super();
		this.justifications = new ArrayList<>();
		this.answerText = answerText;
		this.marked = marked;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}


	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	public List<Justification> getJustifications() {
		return justifications;
	}

	public void setJustifications(List<Justification> justifications) {
		this.justifications = justifications;
	}

	public void addJustification(Justification justification) {
		this.justifications.add(justification);
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(Question question) {
		this.questions.add(question);
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerText=" + answerText + ", marked=" + marked + ", correct=" + correct
				+ ", justifications=" + justifications + ", user=" + user + ", concept=" + concept + ", questions="
				+ questions + "]";
	}


}
