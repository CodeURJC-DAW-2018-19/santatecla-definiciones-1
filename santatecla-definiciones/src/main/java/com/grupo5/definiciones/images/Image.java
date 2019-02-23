package com.grupo5.definiciones.images;

public class Image {
	private int id;
	

	public Image(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + "]";
	}
}
