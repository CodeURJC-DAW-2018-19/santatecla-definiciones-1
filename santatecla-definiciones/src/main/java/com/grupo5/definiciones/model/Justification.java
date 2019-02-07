package com.grupo5.definiciones.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Justification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String justificationText;
	private boolean valid;
	private String error;
	
	protected Justification() {}
	
	public Justification(String justificationText, boolean valid) {
		super();
		this.justificationText = justificationText;
		this.valid = valid;
	}
	
	public String getJustificationText() {
		return justificationText;
	}
	public void setJustificationText(String justificationText) {
		this.justificationText = justificationText;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
