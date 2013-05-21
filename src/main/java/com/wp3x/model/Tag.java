package com.wp3x.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
	
	@Id
	private String id;
	
	private String label;
	
	private Long clicks;
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}
	
	public Tag(String id, String label, Long clicks) {
		this.id = id;
		this.label = label;
		this.clicks = clicks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getClicks() {
		return clicks;
	}

	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}

}
