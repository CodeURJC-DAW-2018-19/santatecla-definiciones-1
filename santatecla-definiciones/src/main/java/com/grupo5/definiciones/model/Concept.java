package com.grupo5.definiciones.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.Criteria;
import org.hibernate.Session;

import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

@Entity
public class Concept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String conceptName;
	@ManyToOne
	private Chapter chapter;
	
	protected Concept() {}

	public Concept(String conceptName) {
		super();
		this.conceptName = conceptName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConceptName() {
		return conceptName;
	}

	public void setConceptName(String conceptName) {
		this.conceptName = conceptName;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	@Override
	public String toString() {
		return "Concept [id=" + id + ", conceptName=" + conceptName + ", chapter=" + chapter + "]";
	}
	
}
