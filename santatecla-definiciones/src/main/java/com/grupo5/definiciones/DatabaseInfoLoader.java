package com.grupo5.definiciones;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupo5.definiciones.model.Answer;
import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.model.Concept;
import com.grupo5.definiciones.model.Justification;
import com.grupo5.definiciones.model.User;
import com.grupo5.definiciones.repositories.AnswerRepository;
import com.grupo5.definiciones.repositories.ChapterRepository;
import com.grupo5.definiciones.repositories.UserRepository;

@Component
public class DatabaseInfoLoader {

	@Autowired
	private ChapterRepository chapterRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AnswerRepository answerRepository;
	
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
		answers[0] = new Answer("¿ES CORRECTO AFIRMAR QUE " + "SPRING ES UN FRAMEWORK QUE PERMITE A "
				+ "UNA APLICACIÓN EJECUTARSE TANTO EN UN " + "SERVIDOR DE APLICACIONES COMO EN UN " + "SERVIDOR WEB?",
				"Sí", true, student);
		answers[0].setCorrect(true);
		answers[1] = new Answer("¿ES CIERTO QUE SPRING NO ES" + " UN FRAMEWORK QUE PERMITE EL DESARROLLO DE "
				+ "APLICACIONES DE SERVIDOR PORQUE SUS " + "APLICACIONES SÓLO SE PUEDEN EJECUTAR EN UN "
				+ "SERVIDOR WEB?", "Sí", true, student);
		answers[1].setCorrect(false);
		Justification j1 = new Justification("SUS APLICACIONES " + "SÓLO SE PUEDEN EJECUTAR EN UN SERVIDOR WEB?",
				false);
		j1.setError("SPRING PERMITE EL DESARROLLO DE DIVERSOS " + "TIPOS DE APLICACIONES DE SERVIDOR: APLICACIONES"
				+ " WEB, SERVICIOS REST, ANÁLISIS DE DATOS BIG DATA...");
		answers[1].setJustification(j1);
		answers[2] = new Answer("¿QUÉ ES SPRING?",
				"Un framework de " + "desarrollo de aplicaciones empresariales basado en" + " tecnologías Java.", false,
				student);
		answers[3] = new Answer("¿POR QUÉ NO ES CORRECTO AFIRMAR QUE " + "SPRING ES UN FRAMEWORK COMERCIAL?",
				"Spring es un " + "framework de software libre.", false, admin);
		
		for (Answer a: answers) {
			a.setConcept(c11);
			answerRepository.save(a);
		}
	}

}