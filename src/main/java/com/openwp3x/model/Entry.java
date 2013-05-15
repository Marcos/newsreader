package com.openwp3x.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Entry {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="id")
	private Long id;
	
	@Column(name="date_entry")
	private String dateSource;
	
	@Column(name="title_entry")
	private String titleSource;
	
	@Column(name="url_entry")
	private String urlSource;
	
	@Column(name="date_insert")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateInsert;
	
	@Column(name="date_published")
	private Date datePublished;
	
	@Column(name="link")
	private String link;
	
	@Column(name="title")
	private String title;
	
	@Column(name="source")
	private String source;
	
	@Column(name="source_label")
	private String sourceLabel;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="random_factor")
	private Long randomFactor;
	
	@ManyToMany
	@JoinTable(name="tag_entry", joinColumns=@JoinColumn(name="entry_id"), inverseJoinColumns=@JoinColumn(name="tag_id"))
	private Collection<Tag> tags;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateSource() {
		return dateSource;
	}

	public void setDateSource(String dateSource) {
		this.dateSource = dateSource;
	}

	public String getTitleSource() {
		return titleSource;
	}

	public void setTitleSource(String titleSource) {
		this.titleSource = titleSource;
	}

	public String getUrlSource() {
		return urlSource;
	}

	public void setUrlSource(String urlSource) {
		this.urlSource = urlSource;
	}

	public Date getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(Date dateInsert) {
		this.dateInsert = dateInsert;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceLabel() {
		return sourceLabel;
	}

	public void setSourceLabel(String sourceLabel) {
		this.sourceLabel = sourceLabel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRandomFactor() {
		return randomFactor;
	}

	public void setRandomFactor(Long randomFactor) {
		this.randomFactor = randomFactor;
	}

	public Collection<Tag> getTags() {
		return tags;
	}

	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
	
	
	
	
	
}
