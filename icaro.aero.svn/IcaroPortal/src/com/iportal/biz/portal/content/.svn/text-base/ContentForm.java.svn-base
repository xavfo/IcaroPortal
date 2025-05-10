/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.portal.content;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

public class ContentForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6842236439798608220L;
	
private String title;
    
    private String intro;
    
    private Long codeContent;
    
    private Long contentChild;
    
    private String text;
    
    private Boolean group;
    
    private Integer level;
    
    private Integer order;
    
    private String background;
    
    private Boolean enabled;
    
    private String keywords;
    
    private String description;
    
    private Boolean showOpen;
    
    private Boolean type; //1:Additional 0:No Additional
    
    private String link;
    
    private Boolean main;
    
    private Long imageCode; 

    private Long accessImageCode;
    
    private Long bannerCode;
    
    private Long languageCode;
    
    private Long parentCode;
    
    private String parentDescription;
    
    
    /*
     * properties to search 
     */
    
    private String titleSearch;
    
    private Long sectionCode;
    
    private Long categoryCode;
    
    private Boolean enabledSearch;
    
    
    /**
     * Creates a new instance of SysRoleForm
     */
    public ContentForm() {
        super();
    }
    
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        title = null;
        intro = null;
        text = null;
        level = null;
        order = null;
        background = null;
        enabled = Globals.TRUE;
        keywords = null;
        description = null;
        showOpen = Globals.FALSE;
        type = Globals.FALSE; //1:Additional 0:No Additional
        link = null;
        main = Globals.TRUE;
        imageCode = null; 
        accessImageCode = null;
        bannerCode = null;
        sectionCode=null;
        categoryCode=null;
        parentDescription=null;
        enabledSearch=null;
       codeContent=null;
       contentChild=null;
    }
    
	/**
	 * @return Returns the accessImageCode.
	 */
	public Long getAccessImageCode() {
		return accessImageCode;
	}
	/**
	 * @param accessImageCode The accessImageCode to set.
	 */
	public void setAccessImageCode(Long accessImageCode) {
		this.accessImageCode = accessImageCode;
	}
	
	/**
	 * @return Returns the background.
	 */
	public String getBackground() {
		return background;
	}
	/**
	 * @param background The background to set.
	 */
	public void setBackground(String background) {
		this.background = background;
	}
	
	/**
	 * @return Returns the bannerCode.
	 */
	public Long getBannerCode() {
		return bannerCode;
	}
	/**
	 * @param bannerCode The bannerCode to set.
	 */
	public void setBannerCode(Long bannerCode) {
		this.bannerCode = bannerCode;
	}

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return enabled;
	}
	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return Returns the imageCode.
	 */
	public Long getImageCode() {
		return imageCode;
	}
	/**
	 * @param imageCode The imageCode to set.
	 */
	public void setImageCode(Long imageCode) {
		this.imageCode = imageCode;
	}
	
	/**
	 * @return Returns the intro.
	 */
	public String getIntro() {
		return intro;
	}
	/**
	 * @param intro The intro to set.
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * @return Returns the keywords.
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords The keywords to set.
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return Returns the level.
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level The level to set.
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return Returns the link.
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * @return Returns the main.
	 */
	public Boolean getMain() {
		return main;
	}
	/**
	 * @param main The main to set.
	 */
	public void setMain(Boolean main) {
		this.main = main;
	}

	/**
	 * @return Returns the order.
	 */
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param order The order to set.
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return Returns the showOpen.
	 */
	public Boolean getShowOpen() {
		return showOpen;
	}
	/**
	 * @param showOpen The showOpen to set.
	 */
	public void setShowOpen(Boolean showOpen) {
		this.showOpen = showOpen;
	}

	/**
	 * @return Returns the text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the type.
	 */
	public Boolean getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(Boolean type) {
		this.type = type;
	}

	/**
	 * @return Returns the languageCode.
	 */
	public Long getLanguageCode() {
		return languageCode;
	}

	/**
	 * @param languageCode The languageCode to set.
	 */
	public void setLanguageCode(Long languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return Returns the categoryCode.
	 */
	public Long getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param categoryCode The categoryCode to set.
	 */
	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @return Returns the parentDescription.
	 */
	public String getParentDescription() {
		return parentDescription;
	}

	/**
	 * @param parentDescription The parentDescription to set.
	 */
	public void setParentDescription(String parentDescription) {
		this.parentDescription = parentDescription;
	}

	/**
	 * @return Returns the sectionCode.
	 */
	public Long getSectionCode() {
		return sectionCode;
	}

	/**
	 * @param sectionCode The sectionCode to set.
	 */
	public void setSectionCode(Long sectionCode) {
		this.sectionCode = sectionCode;
	}

	/**
	 * @return Returns the enabledSearch.
	 */
	public Boolean getEnabledSearch() {
		return enabledSearch;
	}

	/**
	 * @param enabledSearch The enabledSearch to set.
	 */
	public void setEnabledSearch(Boolean enabledSearch) {
		this.enabledSearch = enabledSearch;
	}

	/**
	 * @return Returns the parentCode.
	 */
	public Long getParentCode() {
		return parentCode;
	}
	/**
	 * @param parentCode The parentCode to set.
	 */
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @return Returns the titleSearh.
	 */
	public String getTitleSearch() {
		return titleSearch;
	}
	/**
	 * @param titleSearh The titleSearh to set.
	 */
	public void setTitleSearch(String titleSearch) {
		this.titleSearch = titleSearch;
	}

	/**
	 * @return Returns the group.
	 */
	public Boolean getGroup() {
		return group;
	}

	/**
	 * @param group The group to set.
	 */
	public void setGroup(Boolean group) {
		this.group = group;
	}
	
	public Long getCodeContent() {
		return codeContent;
	}

	public void setCodeContent(Long codeContent) {
		this.codeContent = codeContent;
	}

	public Long getContentChild() {
		return contentChild;
	}

	public void setContentChild(Long contentChild) {
		this.contentChild = contentChild;
	}

	
	
}