package com.grupo5.definiciones.services;

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

	public void save(Chapter chap) {
		chapterRepository.save(chap);
	}

	public Chapter findByChapterName(String conceptName) {
		return chapterRepository.findByChapterName(conceptName);
	}
}
