package com.grupo5.definiciones.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length = 10000)
	private String questionText;
	@ManyToOne
	private Answer answer;
	
	protected Question() {}
	
	public Question(String questionText) {
		
	}
	
	public Question(String questionText, Answer answer) {
		super();
		this.questionText = questionText;
		this.answer = answer;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + ", answer=" + answer + "]";
	}

	
}
