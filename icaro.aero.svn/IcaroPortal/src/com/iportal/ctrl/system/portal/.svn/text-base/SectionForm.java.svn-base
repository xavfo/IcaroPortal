/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system.portal;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.ctrl.BasePortalForm;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;

/**
 * 
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class SectionForm extends BasePortalForm {

    /**
     * 
     */
    private static final long serialVersionUID = -6649099672760418428L;

    private Integer level;
    
    private Boolean group;
    
    private Boolean showMenu;
    
    private Boolean enabled;
    
    private Integer orderIndex;
    
    private String name;
    
    private String publishDate;
    
    private String expirationDate;
    
    private String title;
    
    private String keywords;
    
    private String description;
    
    private String forward;
    
    private String tile;
    
    private String url;
    
    private Boolean createUrl;
    
    private String icon;
    
    private String image1;
    
    private String image2;
    
    private String menuImage1;
    
    private String menuImage2;
    
    private String leadinTitle;
    
    private String leadinText;
    
    private String content1;
            
    private Integer menuWidth;
    
    private Integer menuX;
    
    private Integer menuY;
    
    private Long parentCode;
    
    private Long menuCode;
    
    private Long languageCode;

    
    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        level 		= Globals.DEFAULT_PROPERTY_INDEX;
        group 		= Globals.TRUE;
        showMenu 	= Globals.TRUE;
        enabled 	= Globals.TRUE;
        orderIndex 	= Globals.DEFAULT_PROPERTY_INDEX;
        name 		= null;
        publishDate = null;
        expirationDate = null;
        title 		= null;
        keywords 	= null;
        description = null;
        forward 	= null;
        tile 		= null;
        url 		= null;
        createUrl 	= Globals.TRUE;
        icon 		= null;
        image1 		= null;
        image2 		= null;
        menuImage1	= null;
        menuImage2 	= null;
        leadinTitle = null;
        leadinText 	= null;
        content1	= null;        
        menuWidth 	= null;
        menuX 		= null;
        menuY 		= null;
        parentCode 	= null;
        menuCode 	= null;
        languageCode = null;
    }
    
	public Calendar getPublish() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getPublishDate() != null && this.getPublishDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getPublishDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public void setPublish(Calendar calendar) {
		if ( calendar != null )
			this.publishDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}
	
	public Calendar getExpiration() {
		Calendar calendar = new GregorianCalendar();
		if ( this.getExpirationDate() != null && this.getExpirationDate().length() > 0  ) { 
			calendar.setTime( DateFormatUtils.parseToDate(this.getExpirationDate(), language.getDateFormat()) );
			return calendar;
		}
		return null;
	}
	
	public void setExpiration(Calendar calendar) {
		if ( calendar != null )
			this.expirationDate = DateFormatUtils.format(calendar.getTime(), language.getDateFormat());
	}	
    
    /**
     * @return Returns the content1.
     */
    public String getContent1() {
        return content1;
    }    
    /**
     * @return Returns the createUrl.
     */
    public Boolean getCreateUrl() {
        return createUrl;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @return Returns the enabled.
     */
    public Boolean getEnabled() {
        return enabled;
    }
    /**
     * @return Returns the expirationDate.
     */
    public String getExpirationDate() {
        return expirationDate;
    }
    /**
     * @return Returns the forward.
     */
    public String getForward() {
        return forward;
    }
    /**
     * @return Returns the group.
     */
    public Boolean getGroup() {
        return group;
    }
    /**
     * @return Returns the showMenu.
     */
    public Boolean getShowMenu() {
        return showMenu;
    }
    /**
     * @return Returns the icon.
     */
    public String getIcon() {
        return icon;
    }
    /**
     * @return Returns the image1.
     */
    public String getImage1() {
        return image1;
    }
    /**
     * @return Returns the image2.
     */
    public String getImage2() {
        return image2;
    }
    /**
     * @return Returns the keywords.
     */
    public String getKeywords() {
        return keywords;
    }
    /**
     * @return Returns the languageCode.
     */
    public Long getLanguageCode() {
        return languageCode;
    }
    /**
     * @return Returns the leadinText.
     */
    public String getLeadinText() {
        return leadinText;
    }
    /**
     * @return Returns the leadinTitle.
     */
    public String getLeadinTitle() {
        return leadinTitle;
    }
    /**
     * @return Returns the level.
     */
    public Integer getLevel() {
        return level;
    }
    /**
     * @return Returns the menuCode.
     */
    public Long getMenuCode() {
        return menuCode;
    }
    /**
     * @return Returns the menuImage1.
     */
    public String getMenuImage1() {
        return menuImage1;
    }
    /**
     * @return Returns the menuImage2.
     */
    public String getMenuImage2() {
        return menuImage2;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @return Returns the orderIndex.
     */
    public Integer getOrderIndex() {
        return orderIndex;
    }
    /**
     * @return Returns the parentCode.
     */
    public Long getParentCode() {
        return parentCode;
    }
    /**
     * @return Returns the publishDate.
     */
    public String getPublishDate() {
        return publishDate;
    }
    /**
     * @return Returns the tile.
     */
    public String getTile() {
        return tile;
    }
    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * @return Returns the url.
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param content1 The content1 to set.
     */
    public void setContent1(String content1) {
        this.content1 = content1;
    }     
    /**
     * @param createUrl The createUrl to set.
     */
    public void setCreateUrl(Boolean createUrl) {
        this.createUrl = createUrl;
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
     * @param expirationDate The expirationDate to set.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    /**
     * @param forward The forward to set.
     */
    public void setForward(String forward) {
        this.forward = forward;
    }
    /**
     * @param group The group to set.
     */
    public void setGroup(Boolean group) {
        this.group = group;
    }
    /**
     * @param showMenu The showMenu to set.
     */
    public void setShowMenu(Boolean showMenu) {
        this.showMenu = showMenu;
    }
    /**
     * @param icon The icon to set.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    /**
     * @param image1 The image1 to set.
     */
    public void setImage1(String image1) {
        this.image1 = image1;
    }
    /**
     * @param image2 The image2 to set.
     */
    public void setImage2(String image2) {
        this.image2 = image2;
    }
    /**
     * @param keywords The keywords to set.
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    /**
     * @param languageCode The languageCode to set.
     */
    public void setLanguageCode(Long languageCode) {
        this.languageCode = languageCode;
    }
    /**
     * @param leadinText The leadinText to set.
     */
    public void setLeadinText(String leadinText) {
        this.leadinText = leadinText;
    }
    /**
     * @param leadinTitle The leadinTitle to set.
     */
    public void setLeadinTitle(String leadinTitle) {
        this.leadinTitle = leadinTitle;
    }
    /**
     * @param level The level to set.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
    /**
     * @param menuCode The menuCode to set.
     */
    public void setMenuCode(Long menuCode) {
        this.menuCode = menuCode;
    }
    /**
     * @param menuImage1 The menuImage1 to set.
     */
    public void setMenuImage1(String menuImage1) {
        this.menuImage1 = menuImage1;
    }
    /**
     * @param menuImage2 The menuImage2 to set.
     */
    public void setMenuImage2(String menuImage2) {
        this.menuImage2 = menuImage2;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @param orderIndex The orderIndex to set.
     */
    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
    /**
     * @param parentCode The parentCode to set.
     */
    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }
    /**
     * @param publishDate The publishDate to set.
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    /**
     * @param tile The tile to set.
     */
    public void setTile(String tile) {
        this.tile = tile;
    }
    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @param url The url to set.
     */
    public void setUrl(String url) {
        this.url = url;
    }
	/**
	 * @return Returns the menuWidth.
	 */
	public Integer getMenuWidth() {
		return menuWidth;
	}
	/**
	 * @return Returns the menuX.
	 */
	public Integer getMenuX() {
		return menuX;
	}
	/**
	 * @return Returns the menuY.
	 */
	public Integer getMenuY() {
		return menuY;
	}
	/**
	 * @param menuWidth The menuWidth to set.
	 */
	public void setMenuWidth(Integer menuWidth) {
		this.menuWidth = menuWidth;
	}
	/**
	 * @param menuX The menuX to set.
	 */
	public void setMenuX(Integer menuX) {
		this.menuX = menuX;
	}
	/**
	 * @param menuY The menuY to set.
	 */
	public void setMenuY(Integer menuY) {
		this.menuY = menuY;
	}
}
