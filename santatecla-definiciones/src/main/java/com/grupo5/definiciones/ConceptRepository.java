package com.grupo5.definiciones;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo5.definiciones.model.Concept;

public interface ConceptRepository extends JpaRepository<Concept, Long>{

}
