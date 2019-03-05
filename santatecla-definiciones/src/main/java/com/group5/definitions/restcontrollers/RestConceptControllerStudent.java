package com.group5.definitions.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Question;
import com.group5.definitions.services.QuestionService;
import com.group5.definitions.usersession.UserSessionService;
import com.group5.definitions.utilities.QuestionGenerator;

@RestController
@RequestMapping("/api")
public class RestConceptControllerStudent {

	@Autowired
	private QuestionGenerator questionGenerator;

	@Autowired
	private UserSessionService userSession;

	@Autowired
	private QuestionService questionService;

	private final int DEFAULT_SIZE = 10;

	@JsonView(Question.Basic.class)
	@GetMapping("/concept/{id}/generateQuestion")
	public Question generateQuestion(@PathVariable long id) {
		return questionGenerator.generateQuestion(id);
	}

	// No sense in having 2 different methods, do GET /concept/{id} and get all the questions by user (add more methods to service if needed)
	// Also not working
	@JsonView(Question.Saved.class)
	@GetMapping("/concept/{id}/loadMarkedQuestions")
	public Page<Question> getMarkedQuestions(@PathVariable long id,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		Page<Question> markedQuestions = questionService.findByMarkedAndAnswer_Concept_IdAndUser(true, id,
				userSession.getLoggedUser(), page);
		return markedQuestions;
	}

	@JsonView(Question.Saved.class)
	@GetMapping("/concept/{id}/loadUnmarkedQuestions")
	public Page<Question> getUnmarkedQuestions(@PathVariable long id,
			@PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		Page<Question> unmarkedQuestions = questionService.findByMarkedAndAnswer_Concept_IdAndUser(false, id,
				userSession.getLoggedUser(), page);
		return unmarkedQuestions;
	}
}
