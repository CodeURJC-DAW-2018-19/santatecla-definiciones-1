package com.group5.definitions.restcontrollers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Answer;
import com.group5.definitions.model.Question;
import com.group5.definitions.services.AnswerService;
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
	
	@Autowired
	private AnswerService answerService;

	private final int DEFAULT_SIZE = 10;

	@JsonView(Question.Saved.class)
	@GetMapping("/concepts/{id}/newquestion")
	public Question generateQuestion(@PathVariable long id) {
		return questionGenerator.generateQuestion(id) ;
	}

	@JsonView(Question.Saved.class)
	@GetMapping("/concepts/{id}/markedquestions")
	public Page<Question> getMarkedQuestions(@PathVariable long id,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		Page<Question> questions = questionService.findByMarkedAndAnswer_Concept_IdAndUser(true, id, userSession.getLoggedUser(), page);
		return questions;
	}
	
	@JsonView(Question.Saved.class)
	@GetMapping("/concepts/{id}/unmarkedquestions")
	public Page<Question> getUnmarkedQuestions(@PathVariable long id,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		Page<Question> questions = questionService.findByMarkedAndAnswer_Concept_IdAndUser(false, id, userSession.getLoggedUser(), page);
		return questions;
	}
	
	@JsonView(Answer.Basic.class)
	@PostMapping("/concepts/{conceptId}/saveanswer")
	public ResponseEntity<Answer> addAnswer(@PathVariable long conceptId, 
			@RequestBody Map<String, Object> body){
		
		String answerText = body.get("answerText").toString();
		String answerOption = body.get("answerOption").toString();
		String questionText = body.get("questionText").toString();
		long answerId = Long.parseLong(body.get("answerId").toString());
		long justificationQuestionId = Long.parseLong(body.get("justificationQuestionId").toString());
		int questionType = (Integer) body.get("questionType");
		
		boolean open = answerText != null;
		String answerFinalText = open ? answerText : answerOption;
		questionMarker.saveQuestion(conceptService.findById(conceptId), answerFinalText, questionText, questionType,
				answerId, justificationQuestionId);
		Answer answer = answerService.getOne(answerId);
		return new ResponseEntity<>(answer, HttpStatus.CREATED);
	}
	
	
	
	
	
	/**, 
			@RequestParam int questionType, , 
			@RequestParam(required = false) String answerOption, 
			@RequestParam(required = false) Long justificationQuestionId
			*/
	
	
	
	
	
}
