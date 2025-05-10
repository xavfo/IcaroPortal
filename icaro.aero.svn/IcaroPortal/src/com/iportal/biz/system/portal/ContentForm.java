/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.system.portal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.model.Language;
import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * comment ContentForm
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.0
 *
 */
public class ContentForm extends BaseForm {

    private static final long serialVersionUID = 5284818922967311929L;

    private String title;
    
    private String intro;
    
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
    
    private String imageName;
    
    private Long accessImageCode;
    
    private String accessImageName;
    
    private String bannerName;
    
    private Long languageCode;
    
    private Long parentCode;
    
    private String parentDescription;
    
    private String menuAlias;
    
    private Long accessLevelCode;
    
    private Long moduleCode;
    
    private Long layoutCode;
    
    
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
    
    
    
    /* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		ActionErrors errors = super.validate(arg0, arg1);
		
		if ( !(this.text!= null && this.text.length()>0) 
				&& this.level >1){
			Language lang = (Language)arg1.getSession().getAttribute(Constants.LANGUAGE_KEY);
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("errors.required", BaseHelper.getApplicationBundleMessage("prompt.text", lang.getLocale())));
		}
		return errors;
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
        imageName = null;
        accessImageCode = null;
        accessImageName = null;
        bannerName = null;
        sectionCode=null;
        categoryCode=null;
        parentDescription=null;
        enabledSearch=null;
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
	 * @return Returns the imageName.
	 */
	public String getImageName() {
		return imageName;
	}
	/**
	 * @param imageName The imageName to set.
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
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

	/**
	 * @return Returns the accessLevelCode.
	 */
	public Long getAccessLevelCode() {
		return accessLevelCode;
	}
	/**
	 * @param accessLevelCode The accessLevelCode to set.
	 */
	public void setAccessLevelCode(Long accessLevelCode) {
		this.accessLevelCode = accessLevelCode;
	}

	/**
	 * @return Returns the layoutCode.
	 */
	public Long getLayoutCode() {
		return layoutCode;
	}
	/**
	 * @param layoutCode The layoutCode to set.
	 */
	public void setLayoutCode(Long layoutCode) {
		this.layoutCode = layoutCode;
	}

	/**
	 * @return Returns the menuAlias.
	 */
	public String getMenuAlias() {
		return menuAlias;
	}
	/**
	 * @param menuAlias The menuAlias to set.
	 */
	public void setMenuAlias(String menuAlias) {
		this.menuAlias = menuAlias;
	}

	/**
	 * @return Returns the moduleCode.
	 */
	public Long getModuleCode() {
		return moduleCode;
	}
	/**
	 * @param moduleCode The moduleCode to set.
	 */
	public void setModuleCode(Long moduleCode) {
		this.moduleCode = moduleCode;
	}

	/**
	 * @return Returns the accessImageName.
	 */
	public String getAccessImageName() {
		return accessImageName;
	}

	/**
	 * @param accessImageName The accessImageName to set.
	 */
	public void setAccessImageName(String accessImageName) {
		this.accessImageName = accessImageName;
	}
	
	/**
	 * @return Returns the bannerName.
	 */
	public String getBannerName() {
		return bannerName;
	}
	/**
	 * @param bannerName The bannerName to set.
	 */
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
}
