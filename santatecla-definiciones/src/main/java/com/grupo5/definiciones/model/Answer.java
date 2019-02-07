package com.grupo5.definiciones.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String answerText;
	private boolean correct;
	@OneToOne(cascade = CascadeType.ALL)
	private Justification justification;
	
	protected Answer() {}
	
	public Answer(String answerText, boolean correct) {
		super();
		this.answerText = answerText;
		this.correct = correct;
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
	
	public Justification getJustification() {
		return justification;
	}
	public void setJustification(Justification justification) {
		this.justification = justification;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerText=" + answerText + ", correct=" + correct + ", justification=" + justification + "]";
	}
	
	
}
