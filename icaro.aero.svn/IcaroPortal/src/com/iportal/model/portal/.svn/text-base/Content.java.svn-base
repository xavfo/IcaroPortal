/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model.portal;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.iportal.biz.Group;
import com.iportal.biz.GroupBase;
import com.iportal.model.Image;
import com.iportal.model.Language;
import com.iportal.model.container.DocumentContainer;
import com.iportal.model.container.FaqContainer;
import com.iportal.model.container.LinkContainer;

/*
 * This class persist data to the <code>tb_content</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 2.0
 */

@Entity
@Table(name="tb_content")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="content",
								allocationSize=1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Content extends GroupBase {
	
    private Long code;

    private String title;
    
    private String intro;
    
    private String text;
    
    private Integer level;
    
    private Boolean group;
    
    private Integer order;
    
    private String background;
    
    private transient String backgroundName;
    
    private Boolean enabled;
    
    private String keywords;
    
    private String description;
    
    private Boolean showOpen;
    
    private Boolean Type; //1:Additional 0:No Additional
    
    private String link;
    
    private Boolean main;
    
    private String menuAlias;
    
    private Long documentsNumber;
    
    private Long supportDocumentsNumber;
    
    private Long faqsNumber;
    
    private Long linksNumber;   
   
    private AccessLevel accessLevel;
    
    private Layout layout;
    
    private Image image; 

    private Image accessImage;
    
    private Language language;
    
    private Content parent;
    
    private transient PortalModule module;
    
    private transient Long document;
    
    private transient Long support;

    
    private Set<Content> contents;
    
    private Set<DocumentContainer> listOfRelatedDocuments;
    
    private Set<FaqContainer> listOfRelatedFaq;
    
    private Set<LinkContainer> listOfRelatedLink;
    
    
    @Id 
    @Column(name="content_code")
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
    
	@Column(name="content_background")
	public String getBackground() {
		return background;
	}
	/**
	 * @param background The background to set.
	 */
	public void setBackground(String background) {
		this.setBackgroundName(null);
		this.background = background;
	}
	
	@Column(name="content_description")
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="content_enabled")
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name="content_intro")
	public String getIntro() {
		return intro;
	}
	/**
	 * @param intro The intro to set.
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name="content_keywords")
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords The keywords to set.
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name="content_link")
	public String getLink() {
		return link;
	}
	/**
	 * @param link The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
	}

	@Column(name="content_main")
	public Boolean getMain() {
		return main;
	}
	/**
	 * @param main The main to set.
	 */
	public void setMain(Boolean main) {
		this.main = main;
	}
	
	@Column(name="content_level")
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Column(name="content_order")
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order The order to set.
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name="content_show_open")
	public Boolean getShowOpen() {
		return showOpen;
	}
	/**
	 * @param showOpen The showOpen to set.
	 */
	public void setShowOpen(Boolean showOpen) {
		this.showOpen = showOpen;
	}
	
	@Lob
	@Column(name="content_text")
	public String getText() {
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Column(name="content_title")
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="content_type")
	public Boolean getType() {
		return Type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(Boolean type) {
		Type = type;
	}
	
	@Column(name="content_menu_alias")
	public String getMenuAlias() {
		return menuAlias;
	}
	/**
	 * @param menuAlias The menuAlias to set.
	 */
	public void setMenuAlias(String menuAlias) {
		this.menuAlias = menuAlias;
	}
	
	
	@ManyToOne
	@JoinColumn(name="image_access_code")
	public Image getAccessImage() {
		return accessImage;
	}
	/**
	 * @param accessImage The accessImage to set.
	 */
	public void setAccessImage(Image accessImage) {
		this.accessImage = accessImage;
	}
	
	@ManyToOne
	@JoinColumn(name="image_code")
	public Image getImage() {
		return image;
	}
	/**
	 * @param image The image to set.
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	
	
	@ManyToOne
	@JoinColumn(name="language_code")
	public Language getLanguage() {
		return language;
	}
	/**
	 * @param language The language to set.
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	@ManyToOne
	@JoinColumn(name="content_parent")
	public Content getParent() {
		return parent;
	}
	/**
	 * @param parent The parent to set.
	 */
	public void setParent(Content parent) {
		this.parent = parent;
	}
	
	@ManyToOne
	@JoinColumn(name="access_level_code")
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	/**
	 * @param accessLevel The accessLevel to set.
	 */
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	@ManyToOne
	@JoinColumn(name="layout_code")
	public Layout getLayout() {
		return layout;
	}
	/**
	 * @param layout The layout to set.
	 */
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	
	@OneToMany(mappedBy="parent")
	@OrderBy("order")
    @Where(clause="content_enabled = 1")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<Content> getContents() {
		return contents;
	}
	/**
	 * @param contents The contents to set.
	 */
	public void setContents(Set<Content> contents) {
		this.contents = contents;
	}
	
	@Override
	@Transient
	public Group getAncestor() {
		return parent;
	}
	/**
	 * @return Returns the listOfRelatedDocuments to the current content.
	 */
	@ManyToMany
	@JoinTable(
				name="tb_content_document",
				joinColumns = {@JoinColumn(name="CONTENT_CODE")},
				inverseJoinColumns = @JoinColumn(name="DOC_CONTAINER_CODE")
			 )
	 @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Set<DocumentContainer> getListOfRelatedDocuments() {
		return listOfRelatedDocuments;
	}
	
	/**
	 * @param listOfRelatedDocuments The listOfRelatedDocuments to set.
	 */
	public void setListOfRelatedDocuments(
			Set<DocumentContainer> listOfRelatedDocuments) {
		this.listOfRelatedDocuments = listOfRelatedDocuments;
	}
	
	/**
	 * @return Returns the listOfRelatedLink.
	 */
	@ManyToMany
	@JoinTable(
				name="tb_content_link",
				joinColumns = {@JoinColumn(name="CONTENT_CODE")},
				inverseJoinColumns = @JoinColumn(name="LINK_CONTAINER_CODE")
			 )
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)			 
	public Set<LinkContainer> getListOfRelatedLink() {
		return listOfRelatedLink;
	}

	/**
	 * @param listOfRelatedLink The listOfRelatedLink to set.
	 */
	public void setListOfRelatedLink(Set<LinkContainer> listOfRelatedLink) {
		this.listOfRelatedLink = listOfRelatedLink;
	}
	
	@Column(name="content_group")
	public Boolean getGroup() {
		return group;
	}
	/**
	 * @param group The group to set.
	 */
	public void setGroup(Boolean group) {
		this.group = group;
	}
	
	/**
	 * @return Returns the listOfRelatedFaq.
	 */
	@ManyToMany
	@JoinTable(
				name="tb_content_faq",
				joinColumns = {@JoinColumn(name="CONTENT_CODE")},
				inverseJoinColumns = @JoinColumn(name="FAQ_CONTAINER_CODE")
			 )
	public Set<FaqContainer> getListOfRelatedFaq() {
		return listOfRelatedFaq;
	}
	/**
	 * @param listOfRelatedFaq The listOfRelatedFaq to set.
	 */
	public void setListOfRelatedFaq(Set<FaqContainer> listOfRelatedFaq) {
		this.listOfRelatedFaq = listOfRelatedFaq;
	}
	
	/**
	 * @return Returns the module.
	 */
	@Transient
	public PortalModule getModule() {
		if(this.module == null && this.getLayout() != null) {
			setModule(this.getLayout().getPortalModule());
		}
		return module;
	}
	
	/**
	 * @param module The module to set.
	 */
	private void setModule(PortalModule module) {
		this.module = module;
	}
	
	@Transient
	public Long getDocument() {
		return document;
	}
	public void setDocument(Long document) {
		this.document = document;
	}
	
	@Transient
	public String getBackgroundName() {
		if (background!=null && background.length()>0 && this.backgroundName == null)
			setBackgroundName (background.substring(1));
		return this.backgroundName;
	}
	/**
	 * @param backgroundName The backgroundName to set.
	 */
	private void setBackgroundName(String backgroundName) {
		this.backgroundName = backgroundName;
	}
	
	@Transient
	public Long getSupport() {
		return support;
	}
	public void setSupport(Long support) {
		this.support = support;
	}
   	
    /**
	 * @return Returns the documentsNumber.
	 */
    @Column(name="content_docs_count")
	public Long getDocumentsNumber() {
		return documentsNumber;
	}
	/**
	 * @return Returns the faqsNumber.
	 */
	@Column(name="content_faqs_count")
	public Long getFaqsNumber() {
		return faqsNumber;
	}
	/**
	 * @return Returns the linksNumber.
	 */
	@Column(name="content_links_count")
	public Long getLinksNumber() {
		return linksNumber;
	}
	
	/**
	 * @return Returns the supportDocumentsNumber.
	 */
	@Column(name="content_support_docs_count")	
	public Long getSupportDocumentsNumber() {
		return supportDocumentsNumber;
	}
	/**
	 * @param documentsNumber The documentsNumber to set.
	 */
	public void setDocumentsNumber(Long documentsNumber) {
		this.documentsNumber = documentsNumber;
	}
	/**
	 * @param faqsNumber The faqsNumber to set.
	 */
	public void setFaqsNumber(Long faqsNumber) {
		this.faqsNumber = faqsNumber;
	}
	/**
	 * @param linksNumber The linksNumber to set.
	 */
	public void setLinksNumber(Long linksNumber) {
		this.linksNumber = linksNumber;
	}
	
	/**
	 * @param supportDocumentsNumber The supportDocumentsNumber to set.
	 */
	public void setSupportDocumentsNumber(Long supportDocumentsNumber) {
		this.supportDocumentsNumber = supportDocumentsNumber;
	}
    
    
	
}
