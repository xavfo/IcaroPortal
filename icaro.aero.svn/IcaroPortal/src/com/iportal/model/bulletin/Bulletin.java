/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.bulletin;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class persist data to the <code>tb_bulletin</code> database table.
 * 
 * author  YAGE (jorge Lomas)
 * version 1.0
 */

@Entity
@Table(name="tb_bulletin")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="bulletin",
								allocationSize=20
								)
public class Bulletin {
	
	private Long code;
	
	private String subject;
	
	private String title;
	
	private String image;
	
	private String content;
	
	private Calendar lastUpdateDate;
	
	private String lastUpdateUser;
	
	
	private Topic topic;
	
	
	
	private Set<Historic> historics;
	
	@Id 
    @Column(name="bulletin_code")
     @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}

	@Column(name="bulletin_subject")
	public String getSubject() {
		return subject;
	}

	@Column(name="bulletin_content")
	public String getContent() {
		return content;
	}
	
	@Column(name="bulletin_last_update_date")
	public Calendar getLastUpdateDate() {
		return lastUpdateDate;
	}

	@Column(name="bulletin_last_update_user")
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}


	@ManyToOne
	@JoinColumn(name="topic_code")
	public Topic getTopic() {
		return topic;
	}
	
	@Column(name="bulletin_image")
	public String getImage() {
		return image;
	}

	@Column(name="bulletin_title")
	public String getTitle() {
		return title;
	}
	
	@OneToMany(mappedBy="bulletin")
	public Set<Historic> getHistorics() {
		return historics;
	}
	
	/**
	 * @param historics The historics to set.
	 */
	public void setHistorics(Set<Historic> historics) {
		this.historics = historics;
	}

	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * @param text The content to set.
	 */
	public void setContent(String text) {
		this.content = text;
	}
	
	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}
	
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param image The image to set.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	
	/**
	 * @param lastName The lastName to set.
	 */
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	/**
	 * @param lastUpdateDate The lastUpdateDate to set.
	 */
	public void setLastUpdateDate(Calendar lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * @param lastUpdateUser The lastUpdateUser to set.
	 */
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

}