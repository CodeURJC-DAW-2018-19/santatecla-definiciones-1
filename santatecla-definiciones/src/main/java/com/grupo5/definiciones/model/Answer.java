package com.grupo5.definiciones.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length = 50000)
	private String questionText;
	//Answers with Yes/No questions have "Sí" for yes and "No" for no in the String
	private String answerText;
	private boolean marked;
	private boolean correct;
	@OneToOne(cascade = CascadeType.ALL)
	private Justification justification;
	@OneToOne
	private User user; //User that created this answer 
	
	@ManyToOne
	private Concept concept;
	
	protected Answer() {
	}

	public Answer(String questionText, String answerText, boolean marked, User user) {
		super();
		this.questionText = questionText;
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

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Justification getJustification() {
		return justification;
	}

	public void setJustification(Justification justification) {
		this.justification = justification;
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

	@Override
	public String toString() {
		return "Answer [id=" + id + ", questionText=" + questionText + ", answerText=" + answerText + ", marked="
				+ marked + ", correct=" + correct + ", justification=" + justification + ", user=" + user + ", concept="
				+ concept + "]";
	}


}
