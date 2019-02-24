package com.grupo5.definiciones;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.model.Justification;
import com.grupo5.definiciones.model.Question;
import com.grupo5.definiciones.model.User;
import com.grupo5.definiciones.repositories.AnswerRepository;
import com.grupo5.definiciones.repositories.ChapterRepository;
import com.grupo5.definiciones.repositories.JustificationRepository;
import com.grupo5.definiciones.repositories.QuestionRepository;
import com.grupo5.definiciones.repositories.UserRepository;

@Component
public class DatabaseInfoLoader {

	@Autowired
	private ChapterRepository chapterRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private JustificationRepository justificationRepository;

	@PostConstruct
	private void initDatabase() {
		// Init placeholder data
		User student = new User("user", "pass", "ROLE_ESTUDIANTE");
		User admin = new User("admin", "adminpass", "ROLE_ESTUDIANTE", "ROLE_DOCENTE");
		userRepository.save(student);
		userRepository.save(admin);
		Chapter chapter1 = new Chapter("Tema 1: Desarrollo web servidor");
		Chapter chapter2 = new Chapter("Tema 2: Despliegue de webs");
		Chapter chapter3 = new Chapter("Tema 3: Desarrollo web de cliente avanzado: SPA");
		Concept c11 = new Concept("Spring");
		Concept c12 = new Concept("APIs REST");
		chapter1.getConcepts().add(c11);
		chapter1.getConcepts().add(c12);
		Concept c21 = new Concept("Docker");
		chapter2.getConcepts().add(c21);
		Concept c31 = new Concept("Angular");
		chapter3.getConcepts().add(c31);

		chapterRepository.save(chapter1);
		chapterRepository.save(chapter2);
		chapterRepository.save(chapter3);

		Answer[] answers = new Answer[4];
		answers[0] = new Answer(
				"UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB",
				true, student, c11);
		answers[0].setCorrect(true);
		answers[1] = new Answer("UN FRAMEWORK DE DESARROLLO DE APLICACIONES WEB", true, student, c11);
		answers[1].setCorrect(false);
		Justification j1 = new Justification("SÓLO SE PUEDE UTILIZAR PARA DESARROLLAR SERVICIOS REST", true, student);
		j1.setValid(false);
		j1.setError(
				"SPRING PERMITE EL DESARROLLO DE DIVERSOS TIPOS DE APLICACIONES DE SERVIDOR: APLICACIONES WEB, SERVICIOS REST, ANÁLISIS DE DATOS BIG DATA...");
		answers[1].addJustification(j1);
		answers[2] = new Answer("UN FRAMEWORK DE DESARROLLO DE APLICACIONES EMPRESARIALES BASADO EN JAVA", false,
				student, c11);
		answers[3] = new Answer("UN FRAMEWORK COMERCIAL", true, admin, c11);
		answers[3].setCorrect(false);
		Justification j2 = new Justification("ES UN FRAMEWORK DE SOFTWARE LIBRE", true, admin);
		answers[3].addJustification(j2);
		for (Answer a : answers) {
			answerRepository.save(a);
		}
		j1.setAnswer(answers[1]);
		j2.setAnswer(answers[3]);
		justificationRepository.save(j1);
		justificationRepository.save(j2);

		Question[] questions = new Question[4];
		questions[0] = new Question(
				"¿ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK QUE PERMITE A UNA APLICACIÓN EJECUTARSE TANTO EN UN SERVIDOR DE APLICACIONES COMO EN UN SERVIDOR WEB?",
				2, answers[0], true, student);
		questions[0].setMarked(true);
		questions[0].setUserResponse(true);
		questions[0].setCorrect(true);
		questions[1] = new Question(
				"¿ES CIERTO QUE SPRING NO ES UN FRAMEWORK DE DESARROLLO DE APLICACIONES WEB PORQUE SÓLO SE PUEDE UTILIZAR PARA DESARROLLAR SERVICIOS REST?",
				3, answers[1], true, j1, student);
		questions[1].setMarked(true);
		questions[1].setUserResponse(true);
		questions[1].setCorrect(false);
		questions[2] = new Question("¿QUÉ ES SPRING?", 0, answers[2], false, student);
		questions[3] = new Question("¿POR QUÉ NO ES CORRECTO AFIRMAR QUE SPRING ES UN FRAMEWORK COMERCIAL?", 1,
				answers[3], false, student);
		for (Question q : questions) {
			questionRepository.save(q);
		}

	}

}