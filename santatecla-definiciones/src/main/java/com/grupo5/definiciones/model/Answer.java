package com.grupo5.definiciones.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	@JoinColumn(name="justification_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Justification justification;

	protected Answer() {
	}

	public Answer(String questionText, String answerText, boolean marked) {
		super();
		this.questionText = questionText;
		this.answerText = answerText;
		this.marked = marked;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Answer [id=" + id + ", questionText=" + questionText + ", answerText=" + answerText + ", marked="
				+ marked);
		if (marked)
			sb.append(", correct=" + correct);
		if (justification!=null)
			sb.append(", justification=" + justification);
		sb.append("]");
		return sb.toString();
	}

}
