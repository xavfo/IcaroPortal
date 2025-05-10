/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model;

import java.util.Calendar;
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

import com.iportal.model.news.News;


/*
 * This class persist data to the <code>tb_account</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 2.0
 */

@Entity
@Table(name="tb_image_galery")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="image_gallery",
								allocationSize=20
								)
public class ImageGallery {
	
    private Long code;

    private Image thumbnailImage;
    
    private Image mediumImage;
    
    private Image largeImage;
    
    private String title;
    
    private String description;
    
    private Calendar from;
    
    private Calendar to;
    
    private Boolean enabled;
    
    private Set<News> news;
    
    
    
    public ImageGallery() {
        super();
    }

    @Id 
    @Column(name="image_galery_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}
	
    /**
	 * @return Returns the description.
	 */
    @Column(name="image_galery_description")
	public String getDescription() {
		return description;
	}

	/**
	 * @return Returns the enabled.
	 */
    @Column(name="image_galery_enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @return Returns the from.
	 */
    @Column(name="image_galery_from")
	public Calendar getFrom() {
		return from;
	}

	/**
	 * @return Returns the largeImage.
	 */
    @ManyToOne
    @JoinColumn(name="image_large_code")
	public Image getLargeImage() {
		return largeImage;
	}

	/**
	 * @return Returns the mediumImage.
	 */
    @ManyToOne
    @JoinColumn(name="image_medium_code")
	public Image getMediumImage() {
		return mediumImage;
	}

	/**
	 * @return Returns the thumbnailImage.
	 */
    @ManyToOne
    @JoinColumn(name="image_thumb_code")
	public Image getThumbnailImage() {
		return thumbnailImage;
	}

	/**
	 * @return Returns the title.
	 */
    @Column(name="image_galery_title")
	public String getTitle() {
		return title;
	}

	/**
	 * @return Returns the to.
	 */
    @Column(name="image_galery_to")
	public Calendar getTo() {
		return to;
	}
    
	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
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
	 * @param from The from to set.
	 */
	public void setFrom(Calendar from) {
		this.from = from;
	}

	/**
	 * @param largeImage The largeImage to set.
	 */
	public void setLargeImage(Image largeImage) {
		this.largeImage = largeImage;
	}

	/**
	 * @param mediumImage The mediumImage to set.
	 */
	public void setMediumImage(Image mediumImage) {
		this.mediumImage = mediumImage;
	}

	/**
	 * @param thumbnailImage The thumbnailImage to set.
	 */
	public void setThumbnailImage(Image thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param to The to to set.
	 */
	public void setTo(Calendar to) {
		this.to = to;
	}
	
	/**
	 * @return Returns the events.
	 */
    @ManyToMany(mappedBy = "imageGalleries", targetEntity=News.class)
	public Set<News> getNews() {
		return news;
	}

	public void setNews(Set<News> news) {
		this.news = news;
	}

}
