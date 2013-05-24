package com.wp3x.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="Entry")
public class News {

	public News(Long id, java.util.Date date, String formattedDate, String title, String link, String text, String shortText, String shortLink, String source, String sourceLabel) {
		this.id = id;
		this.datePublished = date;
		this.title = title;
		this.link = link;
		this.text = text;
		this.shortText = shortText;
		this.source = source;
		this.shortLink = shortLink;
		this.sourceLabel = sourceLabel;
		this.formattedDate = formattedDate;
	}
	
	public News() {
		// TODO Auto-generated constructor stub
	}
	
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
	
	@Column(name="text_entry")
	private String textSource;
	
	@Column(name="date_insert")
	@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
	private DateTime dateInsert;
	
	@Column(name="date_published")
	private Date datePublished;
	
	@Column(name="link")
	private String link;
	
	@Column(name="title")
	private String title;
	
	@Column(name="text")
	private String text;
	
	@Column(name="short_text")
	private String shortText;
	
	@Column(name="source")
	private String source;
	
	@Column(name="source_label")
	private String sourceLabel;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="short_link")
	private String shortLink;
	
	@Column(name="random_factor")
	private Long randomFactor;
	
	@ManyToMany
	@JoinTable(name="tag_entry", joinColumns=@JoinColumn(name="entry_id"), inverseJoinColumns=@JoinColumn(name="tag_id"))
	private Collection<Tag> tags;	
	
	@Transient
	private String formattedDate;
	
	@OneToOne(cascade={CascadeType.ALL})
	private Picture picture;

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

	public DateTime getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(DateTime dateInsert) {
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

	public String getTextSource() {
		return textSource;
	}

	public void setTextSource(String textSource) {
		this.textSource = textSource;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getShortLink() {
		return shortLink;
	}

	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}

	public String getShortText() {
		return shortText;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	
}
