package com.group5.definitions.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.group5.definitions.model.Chapter;
import com.group5.definitions.model.User;
import com.group5.definitions.repositories.ChapterRepository;

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
	
	public long countUnmarked(long id, User user) {
		return chapterRepository.countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_User(id, false, user);
	}
	
	public long countCorrect(long id, User user) {
		return chapterRepository.countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_CorrectAndConcepts_Answers_User(id, true, true, user);
	}
	
	public long countIncorrect(long id, User user) {
		return chapterRepository.countByIdAndConcepts_Answers_MarkedAndConcepts_Answers_CorrectAndConcepts_Answers_User(id, true, false, user);
	}
	
	public List<DiagramChapterInfo> generateDiagramInfo() {
		List<DiagramChapterInfo> diagramInfo = new ArrayList<>();
		for (Chapter c : this.findAll()) {
			long id = c.getId();
			diagramInfo.add(new DiagramChapterInfo(c.getChapterName(), this.countUnmarked(id), this.countCorrect(id), this.countIncorrect(id)));
		}
		return diagramInfo;
	}
	
	public List<DiagramChapterInfo> generateDiagramInfo(User user) {
		List<DiagramChapterInfo> diagramInfo = new ArrayList<>();
		for (Chapter c : this.findAll()) {
			long id = c.getId();
			diagramInfo.add(new DiagramChapterInfo(c.getChapterName(), this.countUnmarked(id, user), this.countCorrect(id, user), this.countIncorrect(id, user)));
		}
		return diagramInfo;
	}

	public Chapter findById(long id) {
		return chapterRepository.findById(id);
	}
}
