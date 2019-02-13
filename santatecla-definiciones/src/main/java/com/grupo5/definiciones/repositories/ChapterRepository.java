package com.grupo5.definiciones.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo5.definiciones.model.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long>{
	
	//@Query("SELECT DISTINCT ch FROM Chapter ch LEFT JOIN FETCH ch.concepts ORDER BY ch.chapterName")
	//Page<Chapter> findChaptersOrderedByName(Pageable page);
}
