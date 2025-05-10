/*
 * Created Nov 10, 2006
 *	MenuItemBean.java
 */
package com.iportal.biz.portal.bean;

import java.util.List;

import com.iportal.Constants;
import com.yage.Globals;

/**
 * Objeto especializado para consultar los 
 * datos necesarios para armar un menuItem.
 * @author hernan
 * @deprecated
 */
public class  MenuItemBean {

		
	private Long contentCode;
	
	private String name;
	
	private Long displayModeCode;
	
	private String backgroundName;
	
	private String background;
	
	private String intro;
	
	private String link;
	
	private String imagePath; 
	
	private String bannerPath;
	
	private String accessImagePath;
	
	private String accessImageExtension;
    
	private String accessImageNameNoExtension;
	
    private Integer menuX;
    
    private Integer menuY;
    
    private Integer menuWidth;
    
    private Boolean group;
    
    private List<MenuItemBean> subMenus;
	
	public void clear () {
		
		this.contentCode = null;
		this.name = null;
		this.displayModeCode = null;
		this.backgroundName = null;
		this.background = null;
		this.intro = null;
		this.imagePath = null; 
		this.bannerPath = null;
		this.accessImagePath = null;
		this.accessImageExtension = null;
	    this.accessImageNameNoExtension = null;
		this.link = null;
	    this.menuX = null;
	    this.menuY = null;
	    this.menuWidth = null;
	    this.group = null; 
	    if (this.subMenus != null) {
	    	this.subMenus.clear();
	    }
	    this.subMenus = null;

		
	}
	
	/**
	 * 
	 */
	public MenuItemBean() {
		super();
		this.clear();
	}

	/**
	 * @param contentCode
	 * @param name
	 * @param displayModeCode
	 * @param backgroundName
	 * @param background
	 * @param intro
	 * @param imagePath
	 * @param bannerPath
	 * @param accessImagePath
	 */
	public MenuItemBean(Long contentCode, String name, Long displayModeCode, String link, String backgroundName, String background, String intro, String imagePath, String bannerPath, String accessImagePath) {
		super();
		this.clear ();
		this.contentCode = contentCode;
		this.name = name;
		this.displayModeCode = displayModeCode;
		this.link = link;
		this.backgroundName = backgroundName;
		this.background = background;
		this.intro = intro;
		this.imagePath = imagePath;
		this.bannerPath = bannerPath;
		this.accessImagePath = accessImagePath;
	}
	
	
	

	public MenuItemBean(Long contentCode, String name, Integer menuX, Integer menuY, Integer menuWidth, Boolean group) {
		super();
		this.clear ();

		this.contentCode = contentCode;
		this.name = name;
		this.menuX = menuX;
		this.menuY = menuY;
		this.menuWidth = menuWidth;
		this.group = group;
	}
	
	

	public MenuItemBean(Long contentCode, String name, Boolean group) {
		super();
		this.clear ();
		this.contentCode = contentCode;
		this.name = name;
		this.group = group;
		this.menuX = Globals.ZERO;
		this.menuY = Globals.ZERO;
		this.menuWidth = Constants.MAIN_MENU_WIDTH;

	}

	public MenuItemBean(Long contentCode, String name, Boolean group, String link) {
		super();
		this.clear ();
		this.contentCode = contentCode;
		this.name = name;
		this.link = link;
		this.group = group;
		this.menuX = Globals.ZERO;
		this.menuY = Globals.ZERO;
		this.menuWidth = Constants.MAIN_MENU_WIDTH;

	}

	public MenuItemBean(Long contentCode, String name, String link) {
		super();
		this.clear ();
		this.contentCode = contentCode;
		this.name = name;
		this.link = link;

	}

	/**
	 * @return Returns the accessImagePath.
	 */
	public String getAccessImagePath() {
		return accessImagePath;
	}

	/**
	 * @return Returns the background.
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * @return Returns the backgroundName.
	 */
	public String getBackgroundName() {
		return backgroundName;
	}

	/**
	 * @return Returns the bannerPath.
	 */
	public String getBannerPath() {
		return bannerPath;
	}

	/**
	 * @return Returns the contentCode.
	 */
	public Long getContentCode() {
		return contentCode;
	}

	/**
	 * @return Returns the displayModeCode.
	 */
	public Long getDisplayModeCode() {
		return displayModeCode;
	}

	/**
	 * @return Returns the group.
	 */
	public Boolean getGroup() {
		return group;
	}

	/**
	 * @return Returns the imagePath.
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @return Returns the intro.
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * @return Returns the link.
	 */
	public String getLink() {
		return link;
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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the subMenus.
	 */
	public List<MenuItemBean> getSubMenus() {
		return subMenus;
	}

	/**
	 * @param accessImagePath The accessImagePath to set.
	 */
	public void setAccessImagePath(String accessImagePath) {
		this.accessImagePath = accessImagePath;
	}

	/**
	 * @param background The background to set.
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * @param backgroundName The backgroundName to set.
	 */
	public void setBackgroundName(String backgroundName) {
		this.backgroundName = backgroundName;
	}

	/**
	 * @param bannerPath The bannerPath to set.
	 */
	public void setBannerPath(String bannerPath) {
		this.bannerPath = bannerPath;
	}

	/**
	 * @param contentCode The contentCode to set.
	 */
	public void setContentCode(Long contentCode) {
		this.contentCode = contentCode;
	}

	/**
	 * @param displayModeCode The displayModeCode to set.
	 */
	public void setDisplayModeCode(Long displayModeCode) {
		this.displayModeCode = displayModeCode;
	}

	/**
	 * @param group The group to set.
	 */
	public void setGroup(Boolean group) {
		this.group = group;
	}

	/**
	 * @param imagePath The imagePath to set.
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @param intro The intro to set.
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * @param link The link to set.
	 */
	public void setLink(String link) {
		this.link = link;
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

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param subMenus The subMenus to set.
	 */
	public void setSubMenus(List<MenuItemBean> subMenus) {
		this.subMenus = subMenus;
	}

	/**
     * Devuelve la extensi�n del archivo adjunto.
     * @return
     */
	public String getAccessImageExtension() {
		if  (this.accessImageExtension == null) {
			if ( this.accessImagePath != null && this.accessImagePath.length() > 0) {
				setAccessImageExtension(this.accessImagePath.substring(this.accessImagePath.lastIndexOf(".")+1).trim());
			}else {
				setAccessImageExtension(null);
			}
		}
		return accessImageExtension;
	}

	/**
	 * @param image1Extension The image1Extension to set.
	 */
	private void setAccessImageExtension(String imageExtension) {
		this.accessImageExtension = imageExtension;
	}
	
	/**
     * Devuelve el path del archivo sin extensi�n para presentaci�n de archivo FLASH.
     * @return
     */
	public String getAccessImageNameNoExtension() {
		if  (this.accessImageNameNoExtension == null) {
			if ( this.accessImagePath != null && this.accessImagePath.length() > 0) {
				setAccessImageNameNoExtension(this.accessImagePath.substring(0,this.accessImagePath.lastIndexOf(".")).trim());
			}else {
				setAccessImageNameNoExtension(null);
			}
		}
		return accessImageNameNoExtension;
	}

	/**
	 * @param image1Extension The image1Extension to set.
	 */
	private void setAccessImageNameNoExtension(String imageNameNoExtension) {
		this.accessImageNameNoExtension = imageNameNoExtension;
	}
}
