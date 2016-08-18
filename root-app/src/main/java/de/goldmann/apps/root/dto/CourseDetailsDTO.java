package de.goldmann.apps.root.dto;

import java.io.Serializable;

public class CourseDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1806333652496335136L;

	private String				name;
	private String				description;

	public CourseDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CourseDetailsDTO(final String name, final String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "CourseDetailsDTO [" + (name != null ? "name=" + name : "") + "]";
	}

}
