package com.group5.definitions.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	private final int DEFAULT_SIZE = 10;
	@Autowired
	private ConceptService conceptService;
	@Autowired
	private JustificationService justificationService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserSessionService userSession;
	
	
	//Concept methods
	@JsonView(Concept.Basic.class)
	@PutMapping("/chapters/{id}/concepts/")
	public ResponseEntity<Concept> updateConcept(@PathVariable long id, @RequestBody Concept concept) {
		Concept oldConcept = conceptService.findById(id);
		if (oldConcept == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		concept.setId(id);
		if (concept.getChapter() == null)
			concept.setChapter(oldConcept.getChapter());
		conceptService.save(concept);
		return new ResponseEntity<>(concept, HttpStatus.OK);
	}
	
	//Get methods
	interface AnswerMarked extends Answer.Marked, Answer.Justifications, Justification.Basic {}
	@JsonView(AnswerMarked.class)
	@GetMapping(value = {"/concepts/{conceptId}", "/concepts/{conceptId}/markedanswers"})
	public Page<Answer> getMarked(@PathVariable long conceptId, @PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		return answerService.findByMarkedAndConceptId(true, conceptId, page);
	}
	
	@JsonView(Answer.Basic.class)
	@GetMapping("/concepts/{conceptId}/unmarkedanswers")
	public Page<Answer> getUnmarked(@PathVariable long conceptId, @PageableDefault(size = DEFAULT_SIZE) Pageable page) {
		return answerService.findByMarkedAndConceptId(false, conceptId, page);
	}
	
	
	//Answer methods
	@JsonView(AnswerMarked.class)
	@PutMapping(value = "concepts/{conceptId}/answers/{answerId}")
	public ResponseEntity<Answer> updateAnswer(@PathVariable Long conceptId, @PathVariable Long answerId, 
			@RequestBody Answer answer) {
		Answer ans = answerService.getOne(answerId);
		if (ans == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		ans.setAnswerText(answer.getAnswerText().toUpperCase());
		ans.setCorrect(answer.isCorrect());
		if (ans.isCorrect()) {
			// It is needed to delete the justifications from the DB
			for (Justification j : answer.getJustifications()) {
				justificationService.deleteById(j.getId());
			}
			ans.getJustifications().clear(); // In case, we clear the answer justifications
		} else {
			for(Justification j : answer.getJustifications()){
				Justification newJ = new Justification(j.getJustificationText().toUpperCase(), true, userSession.getLoggedUser());
				newJ.setValid(j.isValid());
				if (j.isValid())
					newJ.setError(j.getError().toUpperCase());
				ans.addJustification(newJ);
				newJ.setAnswer(ans);
				justificationService.save(newJ);
			}
		}
		answerService.save(ans);
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}

	@JsonView(AnswerMarked.class)
	@DeleteMapping("/concepts/{conceptId}/answers/{answerId}")
	public ResponseEntity<Answer> deleteAnswer(@PathVariable long conceptId, @PathVariable long answerId) {
		Answer ans = answerService.getOne(answerId);
		if(ans != null){
			for(Justification j :ans.getJustifications())
				justificationService.deleteById(j.getId());	
			answerService.deleteById(answerId);
			return new ResponseEntity<>(ans, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(AnswerMarked.class)
	@PostMapping("/concepts/{conceptId}/answers/{answerId}")
	public ResponseEntity<Answer> addAnswer(@PathVariable long conceptId, @PathVariable long answerId,
			@RequestBody Answer answer){
		Concept con = conceptService.findById(conceptId);
		con.addAnswer(answer);
		answer.setConcept(con);
		conceptService.save(con);
		if(!answer.isCorrect()) {
			for(Justification j : answer.getJustifications()) {
				justificationService.save(j);
			}
		}
		for(Question q : answer.getQuestions()) {
			questionService.save(q);
		}
		answerService.save(answer);
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}
	
	
	//Justification methods
	@JsonView(Justification.Basic.class)
	@PutMapping("/answers/{ansId}/justifications/{justId}")
	public ResponseEntity<Justification> updateJustification(@PathVariable long ansId, @PathVariable long justId,
			@RequestBody Justification justification) {
		Justification oldJust = justificationService.findById(justId);
		if (oldJust == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (!oldJust.isMarked())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (!justification.isValid() && (justification.getError() == null))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		if (justification.isValid())
			justification.setError("");
		justification.setId(justId);
		justification.setMarked(true);
		justification.setAnswer(oldJust.getAnswer());
		justification.setUser(oldJust.getUser());
		justificationService.save(justification);
		return new ResponseEntity<>(justification, HttpStatus.OK);
	}

	@JsonView(Justification.Basic.class)
	@DeleteMapping("/answers/{ansId}/justifications/")
	public ResponseEntity<Justification> deleteJustification(@PathVariable long id, @RequestBody Justification justification) {
		if (justification.isMarked() && justification.getAnswer().countMarkedJustifications() > 1) {
			justificationService.deleteById(id);
			return new ResponseEntity<>(justification, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@JsonView(Justification.Basic.class)
	@PostMapping("/answers/{ansId}/justifications/")
	public ResponseEntity<Justification> addJustification(@PathVariable long ansId, @RequestBody Justification justification) {
		Answer answer = answerService.getOne(ansId);
		if (answer.isMarked() && !answer.isCorrect()) {
			if (!justification.isValid() && justification.getError()==null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			justification.setMarked(true);
			justification.setAnswer(answer);
			justificationService.save(justification);
			return new ResponseEntity<>(justification, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//Correction methods
	@JsonView(AnswerMarked.class)
	@PutMapping("/concepts/{conceptId}/correct/{answerId}")
	public ResponseEntity<Answer> correctAnswer(@PathVariable long conceptId, @PathVariable long answerId,
			@RequestParam boolean correct, @RequestParam(required = false) String justificationTextNew) {
		Answer answer = answerService.getOne(answerId);
		if(!correct) {
			if(justificationTextNew != null) {
				Justification jus = new Justification(justificationTextNew, true, answer.getUser());
				jus.setValid(true);
				jus.setAnswer(answer);
				justificationService.save(jus);
				answer.addJustification(jus);
			}else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		answer.setCorrect(correct);
		answer.setMarked(true);
		for (Question q : answer.getQuestions()) {
			if (!q.isMarked() && (q.getType() == 0)) {
				q.setMarked(true);
				q.setCorrect(answer.isCorrect());
				questionService.save(q);
			}
		}
		answerService.save(answer);
		return new ResponseEntity<>(answer, HttpStatus.OK);
	}
	
	@JsonView(Justification.Basic.class)
	@PutMapping("/answers/{ansId}/correct/{justId}")
	public ResponseEntity<Justification> correctJustification(@PathVariable long answerId,@PathVariable long justId, 
			@RequestParam boolean valid, @RequestParam(required = false) String errorText) {
		Justification jus = justificationService.findById(justId);
		Answer ans = jus.getAnswer();
		if(!valid) {
			if(errorText != null) 
				jus.setError(errorText);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		jus.setMarked(true);
		jus.setValid(valid);
		jus.setError(errorText);
		justificationService.save(jus);
		ans.addJustification(jus);
		answerService.save(ans);
		return new ResponseEntity<>(jus, HttpStatus.OK);
	}
}
