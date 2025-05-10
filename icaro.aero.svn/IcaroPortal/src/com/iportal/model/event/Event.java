/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.event;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.iportal.model.Catalogue;
import com.iportal.model.Image;
import com.iportal.model.Language;
import com.iportal.model.container.DocumentContainer;


/**
 * Representa un objeto Event.  Eventos del Portal.
 * 
 * @author  YAGE (andresg)
 * @version 1.0
 */
@Entity
@Table(name="tb_event")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="event",
								allocationSize=20
								)
public class Event {
	
	private Long code;
	
	private Calendar creation;
	
	private Boolean enabled;
		
	private String title;

	private String summary;
	
	private String description;
	
	private String subject;
	
	private String host;
	
	private Boolean main;
	
	private String audience;
	
	private Calendar from;
	
	private Calendar to;
	
	private String keyWords;
	
	private Boolean seminary;
	

	private Image introImage;
    
    private Image mainImage;    
    
    private Catalogue category;

    private Language language;
    
    private Set<EventDate> dates;

    private Set<DocumentContainer> documents;

    private Set<Image> images;
    
    private transient Long docSize;
    
    private Long index;

    
	
    /**
     * Creates a new instance of Event
     */
    public Event() {
        super();
        docSize = null;
    }

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="event_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}


	/**
	 * @return Returns the dates.
	 */
	
	@OneToMany( mappedBy="event" )
	public Set<EventDate> getDates() {
		return dates;
	}

	/**
	 * @return Returns the description.
	 */
    @Column(name="event_description")
	public String getDescription() {
		return description;
	}


	/**
	 * @return Returns the enabled.
	 */
    @Column(name="event_enabled")
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * @return Returns the audience.
	 */
    @Column(name="event_audience")	
	public String getAudience() {
		return audience;
	}


	/**
	 * @return Returns the image.
	 */
	@ManyToOne
	@JoinColumn(name="image_parent")
	public Image getMainImage() {
		return mainImage;
	}


	/**
	 * @return Returns the language.
	 */
	@ManyToOne
	@JoinColumn(name="language_code")
	public Language getLanguage() {
		return language;
	}


	/**
	 * @return Returns the leadingImage.
	 */
	@ManyToOne
	@JoinColumn(name="image_code")
	public Image getIntroImage() {
		return introImage;
	}


	/**
	 * @return Returns the main.
	 */
    @Column(name="event_main")	
	public Boolean getMain() {
		return main;
	}


	/**
	 * @return Returns the organizer.
	 */
    @Column(name="event_host")	
	public String getHost() {
		return host;
	}


	/**
	 * @return Returns the subject.
	 */
    @Column(name="event_subject")
	public String getSubject() {
		return subject;
	}
    
    /**
	 * @return Returns the path.
	 */
	@Column(name="event_index")
	public Long getIndex() {
		return index;
	}



	/**
	 * @return Returns the summary.
	 */
    
    @Basic (fetch=FetchType.LAZY)
    @Fetch (FetchMode.SELECT)
    @Column( name="event_summary")
	public String getSummary() {
		return summary;
	}


	/**
	 * @return Returns the title.
	 */
    @Column(name="event_title")
	public String getTitle() {
		return title;
	}


	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}


	/**
	 * @param dates The dates to set.
	 */
	public void setDates(Set<EventDate> dates) {
		this.dates = dates;
	}


	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * @param hearing The hearing to set.
	 */
	public void setAudience(String audience) {
		this.audience = audience;
	}


	/**
	 * @param image The image to set.
	 */
	public void setMainImage(Image mainImage) {
		this.mainImage = mainImage;
	}


	/**
	 * @param language The language to set.
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}


	/**
	 * @param leadingImage The leadingImage to set.
	 */
	public void setIntroImage(Image introImage) {
		this.introImage = introImage;
	}


	/**
	 * @param main The main to set.
	 */
	public void setMain(Boolean main) {
		this.main = main;
	}


	/**
	 * @param organizer The organizer to set.
	 */
	public void setHost(String host) {
		this.host = host;
	}


	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}


	/**
	 * @param summary The summary to set.
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}


	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return Returns the documents.
	 */
	@ManyToMany
	@JoinTable(
			  name="tb_event_document",
			  joinColumns = {@JoinColumn(name="EVENT_CODE")},
			  inverseJoinColumns = @JoinColumn(name="DOC_CONTAINER_CODE")
			)
	public Set<DocumentContainer> getDocuments() {
		return documents;
	}


	/**
	 * @return Returns the images.
	 */
	@OneToMany
	@JoinTable(
			  name="tb_event_galery",
			  joinColumns = {@JoinColumn(name="EVENT_CODE")},
			  inverseJoinColumns = @JoinColumn(name="IMAGE_GALERY_CODE")
			)
	public Set<Image> getImages() {
		return images;
	}


	/**
	 * @param documents The documents to set.
	 */
	public void setDocuments(Set<DocumentContainer> documents) {
		this.documents = documents;
	}


	/**
	 * @param images The images to set.
	 */
	public void setImages(Set<Image> images) {
		this.images = images;
	}

	/**
	 * @return Returns the category.
	 */
	@ManyToOne
	@JoinColumn(name="catalogue_code")
	public Catalogue getCategory() {
		return category;
	}


	public void setCategory(Catalogue category) {
		this.category = category;
	}
	/**
	 * @return Returns the seminary.
	 */
    @Column(name="event_keywords")
	public String getKeyWords() {
		return keyWords;
	}
	/**
	 * @return Returns the seminary.
	 */
    @Column(name="event_seminary")
	public Boolean getSeminary() {
		return seminary;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public void setSeminary(Boolean seminary) {
		this.seminary = seminary;
	}
	/**
	 * @return Returns the enabled.
	 */
    @Column(name="event_publish_from")
	public Calendar getFrom() {
		return from;
	}
    /**
	 * @return Returns the enabled.
	 */
    @Column(name="event_publish_to")
	public Calendar getTo() {
		return to;
	}

	public void setFrom(Calendar from) {
		this.from = from;
	}

	public void setTo(Calendar to) {
		this.to = to;
	}

	/**
	 * @return Returns the hearing.
	 */
    @Column(name="event_creation")	
	public Calendar getCreation() {
		return creation;
	}

	public void setCreation(Calendar creation) {
		this.creation = creation;
	}

	/**
	 * @return Returns the docSize.
	 */
	@Transient
	public Long getDocSize() {
		return docSize;
	}

	/**
	 * @param docSize The docSize to set.
	 */
	public void setDocSize(Long docSize) {
		this.docSize = docSize;
	}
	
	/**
	 * @param path The path to set.
	 */
	public void setIndex(Long index) {
		this.index = index;
	} 
	
	
}
