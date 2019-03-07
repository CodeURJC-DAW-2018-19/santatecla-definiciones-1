package com.group5.definitions.restcontrollers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Answer;
import com.group5.definitions.model.Concept;
import com.group5.definitions.model.Justification;
import com.group5.definitions.model.Question;
import com.group5.definitions.model.User;
import com.group5.definitions.services.ConceptService;
import com.group5.definitions.services.QuestionService;
import com.group5.definitions.usersession.UserSessionService;
import com.group5.definitions.utilities.QuestionGenerator;
import com.group5.definitions.utilities.QuestionMarker;

@RestController
@RequestMapping("/api")
public class RestConceptControllerStudent {

	@Autowired
	private QuestionGenerator questionGenerator;

	@Autowired
	private UserSessionService userSession;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionMarker questionMarker;
	
	@Autowired
	private ConceptService conceptService;

	private final int DEFAULT_SIZE = 10;

	@JsonView(Question.Basic.class)
	@GetMapping("/concepts/{id}/newquestion")
	public Question generateQuestion(@PathVariable long id) {
		return questionGenerator.generateQuestion(id);
	}

	// Both methods should work in theory, the only problem im having is with the User part. I got the authorization correct 
	// in Postman but I guess I'm misssing something out, the commented methods are just in case and to test that they work.
	interface QuestionAnswerConcept extends Question.Saved, Concept.Basic{}
	@JsonView(QuestionAnswerConcept.class)
	@GetMapping("/concepts/{id}/markedquestions")
	public Page<Question> getMarkedQuestions(@PathVariable long id,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		User user = userSession.getLoggedUser();
		//Page<Question> questions = questionService.findByMarkedAndAnswer_Concept_Id(true, id, page);
		Page<Question> questions = questionService.findByMarkedAndAnswer_Concept_IdAndUser(true, id, user, page);
		return questions;
	}
	
	@JsonView(QuestionAnswerConcept.class)
	@GetMapping("/concepts/{id}/unmarkedquestions")
	public Page<Question> getUnmarkedQuestions(@PathVariable long id,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		//Page<Question> questions = questionService.findByMarkedAndAnswer_Concept_Id(false, id, page);
		Page<Question> questions = questionService.findByMarkedAndAnswer_Concept_IdAndUser(false, id, userSession.getLoggedUser(), page);
		return questions;
	}
	
	@JsonView(QuestionAnswerConcept.class)
	@PostMapping("/concepts/{conceptId}/saveanswer")
	public ResponseEntity<Answer> saveAnswer(@PathVariable long conceptId, @RequestBody Question question,
			@RequestBody Answer answer, @RequestBody(required = false) Justification jus){
		boolean open = answer.getAnswerText() != null;
		String yesNo = null;
		if(question.isUserResponse())
			yesNo = "yes";
		else
			yesNo = "no";
		String answerFinalText = open ? answer.getAnswerText() : yesNo;
		questionMarker.saveQuestion(conceptService.findById(conceptId), answerFinalText, question.getQuestionText(), 
				question.getType(), answer.getId(), jus.getId());
		return new ResponseEntity<>(answer, HttpStatus.CREATED);
	}
}
