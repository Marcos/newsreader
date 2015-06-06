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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {

	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	
	private String dateSource;
	
	private String titleSource;
	
	private String urlSource;
	
	private String textSource;
	
	private DateTime dateInsert;
	
	private Date datePublished;
	
	private String link;
	
	private String title;
	
	private String text;
	
	private String shortText;
	
	private String source;
	
	private String sourceLabel;
	
	private Integer status;
	
	private String shortLink;
	
	private Long randomFactor;
	
	private Collection<Tag> tags;	
	
	@OneToOne(cascade={CascadeType.ALL})
	private Picture picture;


	
}
