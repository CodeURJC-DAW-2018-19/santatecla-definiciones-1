package com.grupo5.definiciones.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.model.Justification;
import com.grupo5.definiciones.model.Question;
import com.grupo5.definiciones.services.AnswerService;
import com.grupo5.definiciones.services.JustificationService;
import com.grupo5.definiciones.services.QuestionService;
import com.grupo5.definiciones.usersession.UserSessionService;

@Component
public class QuestionMarker {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private UserSessionService userSession;
	@Autowired
	private JustificationService justificationService;

	public void saveQuestion(Concept concept, String answerText, String questionText, int type,
			String answerQuestionText, String justificationQuestionText) {
		Answer answer = null;
		Question question = null;
		Justification justification = null;
		switch (type) {
		case 0:
			answer = new Answer(answerText, false, userSession.getLoggedUser(), concept);
			question = new Question(questionText, 0, answer, false, userSession.getLoggedUser());
			break;
		case 1:
			answer = answerService.findByAnswerText(answerQuestionText);
			question = new Question(questionText, 1, answer, false, userSession.getLoggedUser());
			justification = new Justification(answerText, false, userSession.getLoggedUser());
			break;
		case 2:
			answer = answerService.findByAnswerText(answerQuestionText);
			question = new Question(questionText, 2, answer, true, userSession.getLoggedUser());
			question.setMarked(true);
			question.setUserResponse(answerText.equals("yes"));
			question.setCorrect((answer.isCorrect() && answerText.equals("yes") || (!answer.isCorrect() && answerText.equals("no"))));
			break;
		case 3:
			answer = answerService.findByAnswerText(answerQuestionText);
			justification = justificationService.findByJustificationText(justificationQuestionText);
			question = new Question(questionText, 2, answer, true, justification, userSession.getLoggedUser());
			question.setMarked(true);
			question.setCorrect((answer.isCorrect() && answerText.equals("yes") || (!answer.isCorrect() && answerText.equals("no"))));
			break;
		}
		answerService.save(answer);
		questionService.save(question);
		if (justification != null) {
			justification.setAnswer(answer);
			justificationService.save(justification);
			answer.addJustification(justification);
		}
	}
}
