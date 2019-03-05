package com.group5.definitions.restcontrollers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Answer;
import com.group5.definitions.model.Concept;
import com.group5.definitions.model.Justification;
import com.group5.definitions.model.Question;
import com.group5.definitions.services.AnswerService;
import com.group5.definitions.services.ConceptService;
import com.group5.definitions.services.JustificationService;
import com.group5.definitions.services.QuestionService;
import com.group5.definitions.usersession.UserSessionService;

@RestController
@RequestMapping("/api")
public class RestConceptControllerTeacher {

	@Autowired
	private ConceptService conceptService;
	private JustificationService justificationService;
	private AnswerService answerService;
	private QuestionService questionService;
	private UserSessionService userSession;
	
	@JsonView(Concept.Basic.class)
	@PutMapping("/concept/{id}")
	public ResponseEntity<Concept> updateConcept(@PathVariable long id, @RequestBody Concept concept) {
		Concept oldConcept = conceptService.findById(id);
		if (oldConcept == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		concept.setId(id);
		if (concept.getChapter()==null)
			concept.setChapter(oldConcept.getChapter());
		conceptService.save(concept);
		return new ResponseEntity<>(concept, HttpStatus.OK);
	}
	
	@PutMapping("/justification/{justId}")
	public ResponseEntity<Justification> updateJustification(@PathVariable long justId, @RequestBody Justification justification) {
		Justification oldJust = justificationService.findById(justId);
		if (oldJust==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (justification.isMarked() && !justification.isValid() && (justification.getError()==null))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		justificationService.save(justification);
		return new ResponseEntity<>(justification, HttpStatus.OK);
	}
	
	@JsonView(Answer.class)
	@PutMapping(value="/answer/{id}")
	public ResponseEntity<Answer> modifyAnswer (@PathVariable Long id, @RequestBody Answer updatedAnswer){
		Answer oldAnswer = answerService.getOne(id);
		if (oldAnswer != null) {
			updatedAnswer.setId(id);
			if (updatedAnswer.getConcept() == null) {
				updatedAnswer.setConcept(oldAnswer.getConcept());
			}
			answerService.save(updatedAnswer);
			return new ResponseEntity<>(updatedAnswer,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/concept/{conceptId}/mark/{answerId}")
	public ResponseEntity<Answer> markAnswer(@PathVariable long conceptId, @PathVariable long answerId, 
			@RequestParam String correct, @RequestParam(required = false) String justificationTextNew, 
			@RequestBody Answer answer) {
		if(answerId == answer.getId()) {
			answer.setMarked(true);
			answer.setCorrect(correct.equals("yes"));
			answerService.save(answer);
			if ((justificationTextNew != null) && (correct.equals("no"))) {
				Justification justification = new Justification(justificationTextNew.toUpperCase(), true,
						userSession.getLoggedUser());
				justification.setValid(true);
				justification.setAnswer(answer);
				justificationService.save(justification);
			}
			for (Question q : answer.getQuestions()) {
				if (!q.isMarked() && (q.getType() == 0)) {
					q.setMarked(true);
					q.setCorrect(correct.equals("yes"));
					questionService.save(q);
				}
			}
			return new ResponseEntity<>(answer, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
