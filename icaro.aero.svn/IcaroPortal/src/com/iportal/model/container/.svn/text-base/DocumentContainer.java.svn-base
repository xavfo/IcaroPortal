/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */

package com.iportal.model.container;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.iportal.model.Catalogue;
import com.iportal.model.DocumentDisplayMode;
import com.iportal.model.DocumentType;
import com.iportal.model.event.Event;
import com.iportal.model.news.News;
import com.iportal.model.portal.Content;
import com.yage.file.FileExtensionUtils;

/**
 * Represents a DocumentContainer Object.
 * 
 * @author  YAGE (hernan) 
 * 
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_doc_container")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="doc_container",
								allocationSize=20
								)
@javax.persistence.AttributeOverrides ( {
	@javax.persistence.AttributeOverride (name="isEnabled", column = @Column( name="doc_container_enabled" )),
	@javax.persistence.AttributeOverride (name="title", column = @Column( name="doc_container_title" ))
})
public class DocumentContainer extends Container {
	
	private String description;
	
	private Date from;
	
	private Date to;
	
	private String keywords;
	
	private String path;
	
	private Long size;
	
	private DocumentType type;
	
	private DocumentDisplayMode displayMode;
	
	private Catalogue category;
		
	private transient String pathIcon;
	
	private String extension;
	
	private Set<Content> contents;
	
	private Set<Event> events;
	
	private Set<News> news;
    
    private Set<FaqContainer> faqs;
	
	/**
     * Creates a new instance of CatalogueType
     */
    public DocumentContainer() {
        super();
        this.clear();
    }
    
    /**
     * Devuelve la extensión del archivo adjunto para presentar 
     * el ícono en lugar del nombre del archivo.
     * @return
     */
    @Transient
    public String getPathIcon () {
    	if (this.pathIcon == null && this.path != null) {
    		setPathIcon (FileExtensionUtils.getFileIcon( this.path ));
    	}
    
    	return  this.pathIcon;
    }

    protected void clear () {
    	super.clear();
    	this.type = null;
    	this.displayMode = null;
    	this.category = null;    	
    	this.description = null;
    	this.from = null;
    	this.to = null ;
    	this.keywords = null;
    	this.path = null;
    	this.pathIcon = null;
    	this.size = null;
    	this.contents = null;
    }
        
    @Id
    @Column(name="doc_container_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
   	public Long getCode() {
		return this.code;
	}
		

	/**
	 * @return Returns the from.
	 */
    @Temporal ( TemporalType.DATE )
    @Column(name="doc_container_from")
	public Date getFrom() {
		return from;
	}

	/**
	 * @return Returns the keyWords.
	 */
    @Column(name="doc_container_keywords")
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @return Returns the path.
	 */
    @Column(name="doc_container_path")
	public String getPath() {
		return path;
	}

	/**
	 * @return Returns the to.
	 */
    @Temporal ( TemporalType.DATE )
    @Column(name="doc_container_to")
	public Date getTo() {
		return to;
	}

	/**
	 * @return Returns the content1.
	 */
	@Column(name="doc_container_description")
	public String getDescription() {
		return description;
	}
	
	

	/**
	 * @return Returns the size.
	 */
	@Column(name="doc_container_size")
	public Long getSize() {
		return size;
	}

	/**
	 * @return Returns the category.
	 */
	@ManyToOne
	@JoinColumn(name="catalogue_code")
	public Catalogue getCategory() {
		return category;
	}
	
	
	@ManyToOne
	@JoinColumn(name="doc_display_mode_code")
	public DocumentDisplayMode getDisplayMode() {
		return displayMode;
	}

	/**
	 * @return Returns the type.
	 */
	@ManyToOne
	@JoinColumn(name="document_type_code")
	public DocumentType getType() {
		return type;
	}

	
	/**
	 * @return Returns the contents.
	 */
	@ManyToMany ( mappedBy="listOfRelatedDocuments", targetEntity=Content.class)
	public Set<Content> getContents() {
		return contents;
	}

	/**
	 * @param contents The contents to set.
	 */
	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}

	/**
	 * @param content1 The content1 to set.
	 */
	public void setDescription(String content1) {
		this.description = content1;
	}

	/**
	 * @param category The category to set.
	 */
	public void setCategory(Catalogue category) {
		this.category = category;
	}

	/**
	 * @param displayMode The displayMode to set.
	 */
	public void setDisplayMode(DocumentDisplayMode displayMode) {
		this.displayMode = displayMode;
	}

	/**
	 * @param from The from to set.
	 */
	public void setFrom(Date from) {
		this.from = from;
	}

	/**
	 * @param keyWords The keyWords to set.
	 */
	public void setKeywords(String keyWords) {
		this.keywords = keyWords;
	}

	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @param to The to to set.
	 */
	public void setTo(Date to) {
		this.to = to;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(DocumentType type) {
		this.type = type;
	}
	


	/**
	 * @param pathIcon The pathIcon to set.
	 */
	private void setPathIcon(String pathIcon) {
		this.pathIcon = pathIcon;
	}

	/**
	 * @param size The size to set.
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * @return Returns the extension.
	 */
	@Column(name="doc_container_extension")
	public String getExtension() {
		return extension;
	}

	/**
	 * @param extension The extension to set.
	 */

	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return Returns the events.
	 */
	@ManyToMany(mappedBy = "documents", targetEntity=Event.class)	
	public Set<Event> getEvents() {
		return events;
	}

	/**
	 * @param events The events to set.
	 */
	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	/**
	 * @return Returns the news.
	 */
	@ManyToMany(mappedBy = "documents", targetEntity=News.class)	
	public Set<News> getNews() {
		return news;
	}

	/**
	 * @param news The news to set.
	 */
	public void setNews(Set<News> news) {
		this.news = news;
	}


    /**
     * @return Returns the faqs.
     */
    @ManyToMany(mappedBy = "documents", targetEntity=FaqContainer.class)
    public Set<FaqContainer> getFaqs() {
        return faqs;
    }

    /**
     * @param faqs The faqs to set.
     */
    public void setFaqs(Set<FaqContainer> faqs) {
        this.faqs = faqs;
    }
	
}
