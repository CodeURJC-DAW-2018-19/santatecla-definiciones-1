package com.grupo5.definiciones.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grupo5.definiciones.model.Chapter;
import com.grupo5.definiciones.repositories.ChapterRepository;

@Service
public class ChapterService {
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	public Page<Chapter> findAll(Pageable page) {
		return chapterRepository.findAll(page);
	}
	
	public List<Chapter> findAll() {
		return chapterRepository.findAll();
	}

	public void save(Chapter chap) {
		chapterRepository.save(chap);
	}

	public Chapter findByChapterName(String conceptName) {
		return chapterRepository.findByChapterName(conceptName);
	}
	
	public void deleteById(Long id) {
		chapterRepository.deleteById(id);
	}
	
	public long countUnmarked(long id) {
		return chapterRepository.countByIdAndConcepts_Answers_Marked(id, false);
	}
	
	public long countCorrect(long id) {
		return chapterRepository.countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_Correct(id, true, true);
	}
	
	public long countIncorrect(long id) {
		return chapterRepository.countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_Correct(id, true, false);
	}
	
	public List<DiagramChapterInfo> generateDiagramInfo() {
		List<DiagramChapterInfo> diagramInfo = new ArrayList<>();
		for (Chapter c : this.findAll()) {
			long id = c.getId();
			diagramInfo.add(new DiagramChapterInfo(c.getChapterName(), this.countUnmarked(id), this.countCorrect(id), this.countIncorrect(id)));
		}
		return diagramInfo;
	}
}
