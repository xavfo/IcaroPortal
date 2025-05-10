/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.news;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iportal.model.Catalogue;
import com.iportal.model.Image;
import com.iportal.model.ImageGallery;
import com.iportal.model.container.DocumentContainer;

/**
 * This class persist data to the <code>tb_news</code> 
 * database table.
 * 
 * @author  mcnovillo
 * @version 1.1
 *
 */
@Entity
@Table(name="tb_news")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="news",
								allocationSize=20
								)
public class News {
	
	private Long code;
	
	private Calendar creation;
	
	private String title;

	private String leadingText;
	
	private String text;
	
	private Calendar from;
	
	private Calendar to;
	
	private Catalogue category;
	
	private Image introImage;
	
	private Image mainImage;
	
	private Boolean isEnabled;
	
	private Set<DocumentContainer> documents;
	
	private Set<ImageGallery> imageGalleries;
	
	private Boolean isLocalInfo;
	
	private Boolean publishHome;
	
	private Long index;
	
	
	
		
    /**
     * Creates a new instance of News
     */
    public News() {
        super();
    }
    
    public String toString() {
        return title;
    }
    	   
    @Id 
    @Column(name="news_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}	
    
	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
	    this.code = code;
	}

	
    @Column(name="news_leading_text")
    public String getLeadingText() {
        return leadingText;
    }
    
	/**
	 * @param leadingText The leadingText to set.
	 */
	public void setLeadingText(String leadinText) {
	    this.leadingText = leadinText;
	}
	
    @Column(name="news_title")
    public String getTitle() {
        return title;
    }
    
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
	    this.title = title;
	}
	
    @Column(name="news_text")
    public String getText() {
        return text;
    }
    
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
	    this.text = text;
	}

    @Column(name="news_from")
    public Calendar getFrom() {
        return from;
    }
    
	/**
	 * @param from The from to set.
	 */
	public void setFrom(Calendar from) {
	    this.from = from;
	}


    @Column(name="news_to")
    public Calendar getTo() {
        return to;
    }
    
	/**
	 * @param to The to to set.
	 */
	public void setTo(Calendar to) {
	    this.to = to;
	}

	/**
	 * @return Returns the image.
	 */
	@ManyToOne
	@JoinColumn(name="image_intro_code")
    public Image getIntroImage() {
        return introImage;
    }
    
	/**
	 * @param image1 The image1 to set.
	 */
	public void setIntroImage(Image image1) {
	    this.introImage = image1;
	}

	/**
	 * @return Returns the image.
	 */
	@ManyToOne
	@JoinColumn(name="image_main_code")
    public Image getMainImage() {
        return mainImage;
    }
    
	/**
	 * @param image2 The image2 to set.
	 */
	public void setMainImage(Image image2) {
	    this.mainImage = image2;
	}

	
    @Column(name="news_enabled")
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * @return Returns the isLocalInfo.
	 */
	@Column(name="news_local_info")
	public Boolean getIsLocalInfo() {
		return isLocalInfo;
	}

	/**
	 * @param isLocalInfo The isLocalInfo to set.
	 */
	public void setIsLocalInfo(Boolean isLocalInfo) {
		this.isLocalInfo = isLocalInfo;
	}

	@ManyToOne
    @JoinColumn(name="catalogue_code")	
	public Catalogue getCategory() {
		return category;
	}
	
	/**
	 * @return Returns the path.
	 */
	@Column(name="news_index")
	public Long getIndex() {
		return index;
	}

	/**
	 * @param type The type to set.
	 */
	public void setCategory(Catalogue type) {
		this.category = type;
	}

	/**
	 * @return Returns the listOfRelatedDocuments that belong to the current News.
	 */
	@ManyToMany
	@JoinTable(
			  name="tb_news_document",
			  joinColumns = {@JoinColumn(name="NEWS_CODE")},
			  inverseJoinColumns = @JoinColumn(name="DOC_CONTAINER_CODE")
			)
	public Set<DocumentContainer> getDocuments() {
		return documents;
	}

	
	
	
	/**
	 * @param enabledDocuments The enabledDocuments to set.
	 */
	public void setEnabledDocuments(Set<DocumentContainer> enabledDocuments) {
	}

	/**
	 * @param listOfRelatedDocuments The listOfRelatedDocuments to set.
	 */
	public void setDocuments(Set<DocumentContainer> documents) {
		this.documents = documents;
	}
	
	
	
	@Column(name="news_publish_home")
	public Boolean getPublishHome() {
		return publishHome;
	}

	public void setPublishHome(Boolean publishHome) {
		this.publishHome = publishHome;
	}
	/**
	 * @return Returns the hearing.
	 */
    @Column(name="news_creation")	
	public Calendar getCreation() {
		return creation;
	}

	public void setCreation(Calendar creation) {
		this.creation = creation;
	}
	
	
	/**
	 * @param path The path to set.
	 */
	public void setIndex(Long index) {
		this.index = index;
	}

	/**
	 * @return Returns the images.
	 */
	@ManyToMany
	@JoinTable(
			  name="tb_news_galery",
			  joinColumns = {@JoinColumn(name="NEWS_CODE")},
			  inverseJoinColumns = @JoinColumn(name="IMAGE_GALERY_CODE")
			)
	public Set<ImageGallery> getImageGalleries() {
		return imageGalleries;
	}

	public void setImageGalleries(Set<ImageGallery> imageGalleries) {
		this.imageGalleries = imageGalleries;
	}

}
