/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */

package com.iportal.model.container;

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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.iportal.model.Catalogue;
import com.iportal.model.portal.Content;

/**
 * Represents a FAQContainer Object.
 * 
 * @author  Pajaro
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_faq_container")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="faq_container",
								allocationSize=20
								)

public class FaqContainer {

	private Long code;
	
	private String question;
	
	private String answer;
	
	private Catalogue category;
	
	private Calendar from;
	
	private Calendar to;
	
	private Boolean isEnabled;
    
    private Set<DocumentContainer> documents;
    
    private Set<Content> contents;
    
    private Long index;
	
	
	/**
     * Creates a new instance of CatalogueType
     */
    public FaqContainer() {
        super();
    }
        
    @Id 
    @Column(name="faq_container_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
		
	/**
	 * @return Returns the name
	 */
	@Column(name="faq_container_question")
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String name) {
		this.question = name;
	}

	/**
	 * @return Returns the from.
	 */
	@Column(name="faq_container_from")
	public Calendar getFrom() {
		return from;
	}

	/**
	 * @param from The from to set.
	 */
	public void setFrom(Calendar from) {
		this.from = from;
	}

	/**
	 * @return Returns the isEnabled.
	 */
	@Column(name="faq_container_enabled")
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
	 * @return Returns the path.
	 */
	@Column(name="faq_container_answer")
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param path The path to set.
	 */
	public void setAnswer(String path) {
		this.answer = path;
	}

	/**
	 * @return Returns the to.
	 */
	@Column(name="faq_container_to")
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
	 * @return Returns the category.
	 */
	@ManyToOne
	@JoinColumn(name="catalogue_code")
	public Catalogue getCategory() {
		return category;
	}

	/**
	 * @param category The category to set.
	 */
	public void setCategory(Catalogue category) {
		this.category = category;
	}

    /**
     * @return Returns the documents.
     */
    @ManyToMany 
    @JoinTable(
              name="tb_faq_document",
              joinColumns = {@JoinColumn(name="faq_container_code")},
              inverseJoinColumns = @JoinColumn(name="doc_container_code")
    )
    @OrderBy("title")
    public Set<DocumentContainer> getDocuments() {
        return documents;
    }

    
    /**
     * @param documents The documents to set.
     */
    public void setDocuments(Set<DocumentContainer> documents) {
        this.documents = documents;
    }

	/**
	 * @return Returns the contents.
	 */
    @ManyToMany ( mappedBy="listOfRelatedFaq", targetEntity=Content.class)
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
	 * @return Returns the path.
	 */
	@Column(name="faq_container_index")
	public Long getIndex() {
		return index;
	}

	/**
	 * @param path The path to set.
	 */
	public void setIndex(Long index) {
		this.index = index;
	} 
	
}//end class
