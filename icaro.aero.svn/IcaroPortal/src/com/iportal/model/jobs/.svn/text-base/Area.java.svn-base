package com.iportal.model.jobs;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.iportal.model.container.DocumentContainer;


/**
 * @version 2.0
 * @created 01-Jun-2006 5:32:23 PM
 */
@Entity
@Table(name="tb_area")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="area",
								allocationSize=20
								)
public class Area {

	private Long code;
	private String name;
	private String email;
	private Boolean isEnabled;
	private Set<Position> listOfPosition;
	private Set<DocumentContainer> documents;
	
	
	/**
	 * Creates a new instance of Area
	 *
	 */
	public Area(){
		super();
	}

	
	@Id 
    @Column(name="area_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")    
	public Long getCode(){
		return code;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setCode(Long newVal){
		code = newVal;
	}

	@Column(name="area_enabled")
	public Boolean getIsEnabled(){
		return isEnabled;
	}

	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}	

	@Column(name="area_name", length=200)
	public String getName(){
		return name;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		name = newVal;
	}

	@Column(name="area_email", length=200)
	public String getEmail(){
		return email;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setEmail(String newVal){
		email = newVal;
	}

	@OneToMany(mappedBy="area")
	
	public Set<Position> getListOfPosition() {
		return listOfPosition;
	}

	/**
	 * @param listOfPosition The listOfPosition to set.
	 */
	public void setListOfPosition(Set<Position> listOfPosition) {
		this.listOfPosition = listOfPosition;
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
	 * @param listOfRelatedDocuments The listOfRelatedDocuments to set.
	 */
	public void setDocuments(Set<DocumentContainer> documents) {
		this.documents = documents;
	}
}