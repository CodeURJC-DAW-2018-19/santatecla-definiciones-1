package com.grupo5.definiciones;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long>{
	
}
