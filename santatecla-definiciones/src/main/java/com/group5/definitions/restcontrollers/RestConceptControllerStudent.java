package com.group5.definitions.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.group5.definitions.model.Question;
import com.group5.definitions.utilities.QuestionGenerator;

@RestController
@RequestMapping("/api")
public class RestConceptControllerStudent {
	
	@Autowired
	private QuestionGenerator questionGenerator;
	
	@JsonView(Question.Basic.class)
	@GetMapping("/concept/{id}/generateQuestion")
	public Question generateQuestion(@PathVariable long id) {
		return questionGenerator.generateQuestion(id);
	}
}
