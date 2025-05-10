/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.portal.bean;

import java.util.ArrayList;

import javax.persistence.Transient;

import com.iportal.biz.RowItem;

public class MenuPortalBean {
    
	  //private Long menuItemCode;
	  
	  private Long contentCode;
	  
	  //private Long displayCode;
	  
	  private Long accessLevelCode;
	  
	  private Long portalModuleCode;
	  
	  private String portalModuleForward;
	  
	  private Long layoutCode;
	  
	  private String layoutForward;
	  
	  private String contentTitle;
	  
	  private String contentText;
	  
	  private String imageName;
	  
	  private String imagePath;
	  
	  private Integer imageHeight;
	  
	  private Integer imageWidth;
	  
	  private String contentBackground;
	  
	  private transient String contentBackgroundName;
	  
	  private transient String contentParentBackgroundName;
	  
	  private Long contentParentCode;
	  
	  private ArrayList<RowItem> navigator;
	  
	  private String contentParentTitle;
	  
	  private String contentParentBackground;
	  
	  
	  private transient String imageExtension;
	    
	  private transient String imageNameNoExtension;

	  

    
	 
		public MenuPortalBean( Long contentCode, Long accessLevelCode, 
				Long layoutCode, 
				String contentTitle, String contentText, 
				String contentBackground, Long contentParentCode) {
			super();
			this.contentCode = contentCode;
			//this.displayCode = displayCode;
			this.accessLevelCode = accessLevelCode;
			this.layoutCode = layoutCode;
			this.contentTitle = contentTitle;
			this.contentText = contentText;
			this.contentBackground = contentBackground;
			this.contentParentCode = contentParentCode;
			this.contentBackgroundName = null;
			this.imageExtension = null;
			this.imageNameNoExtension = null;
			
			
		}

	/**
	 * @param menuItemCode
	 * @param contentCode
	 * @param displayCode
	 * @param accessLevelCode
	 * @param portalModuleCode
	 * @param portalModuleForward
	 * @param layoutCode
	 * @param layoutForward
	 * @param contentTitle
	 * @param contentText
	 * @param contentBackground
	 * @param contentParentCode
	 */
	public MenuPortalBean( Long contentCode, Long accessLevelCode, 
			Long portalModuleCode, String portalModuleForward, 
			Long layoutCode, String layoutForward,
			String contentTitle, String contentText, 
			String contentBackground, Long contentParentCode, String contentParentTitle, String contentParentBackground
			,String imageName, String imagePath) {
		super();
		this.clear();
		this.contentCode = contentCode;
		//this.displayCode = displayCode;
		this.accessLevelCode = accessLevelCode;
		this.portalModuleCode = portalModuleCode;
		this.portalModuleForward = portalModuleForward;
		this.layoutCode = layoutCode;
		this.layoutForward = layoutForward;
		this.contentTitle = contentTitle;
		this.contentText = contentText;
		this.contentBackground = contentBackground;
		
		this.contentParentCode =  contentParentCode;
		this.contentParentTitle = contentParentTitle;
		this.contentBackgroundName = null;
		this.contentParentBackground = contentParentBackground;
		this.contentParentBackgroundName = null;
		
		this.imageName = imageName;
		this.imagePath = imagePath;

	}

	public MenuPortalBean( Long contentCode, Long accessLevelCode, 
			Long portalModuleCode, String portalModuleForward, 
			Long layoutCode, String layoutForward,
			String contentTitle, String contentText, 
			String contentBackground, Long contentParentCode, String contentParentTitle, String contentParentBackground
			,String imageName, String imagePath, Integer height, Integer width ) {
		super();
		this.clear();
		this.contentCode = contentCode;
		//this.displayCode = displayCode;
		this.accessLevelCode = accessLevelCode;
		this.portalModuleCode = portalModuleCode;
		this.portalModuleForward = portalModuleForward;
		this.layoutCode = layoutCode;
		this.layoutForward = layoutForward;
		this.contentTitle = contentTitle;
		this.contentText = contentText;
		this.contentBackground = contentBackground;
		
		this.contentParentCode =  contentParentCode;
		this.contentParentTitle = contentParentTitle;
		this.contentBackgroundName = null;
		this.contentParentBackground = contentParentBackground;
		this.contentParentBackgroundName = null;
		
		this.imageName = imageName;
		this.imagePath = imagePath;
		this.imageHeight = height;
		this.imageWidth  = width;

	}

	public MenuPortalBean( Long contentCode, Long accessLevelCode, 
			Long portalModuleCode, String portalModuleForward, 
			Long layoutCode, String layoutForward,
			String contentTitle, String contentText, 
			String contentBackground, Long contentParentCode) {
		super();
		this.clear();
		this.contentCode = contentCode;
		this.accessLevelCode = accessLevelCode;
		this.portalModuleCode = portalModuleCode;
		this.portalModuleForward = portalModuleForward;
		this.layoutCode = layoutCode;
		this.layoutForward = layoutForward;
		this.contentTitle = contentTitle;
		this.contentText = contentText;
		this.contentBackground = contentBackground;
		this.contentParentCode =  contentParentCode;
		this.contentBackgroundName = null;
	}
	
	
	
	public MenuPortalBean(Long contentCode, String contentTitle, Long contentParentCode, String contentParentTitle) {
		super();
		this.clear();
		this.contentCode = contentCode;
		this.contentTitle = contentTitle;
		this.contentParentCode = contentParentCode;
		this.contentParentTitle = contentParentTitle;
	}



	/**
     * Constructos
     */
    public MenuPortalBean() {
    	super();
    	this.clear();
	}
    
    private void clear () {
		this.contentCode = null;
		this.accessLevelCode  = null;
		this.portalModuleCode = null;
		this.portalModuleForward = null;
		this.layoutCode = null;
		this.layoutForward = null;
		this.contentTitle = null;
		this.contentText = null;
		this.contentBackground = null;
		
		this.contentParentCode = null;
		this.contentParentTitle = null;
		this.contentBackgroundName = null;
		this.contentParentBackground = null;
		
		this.imageName = null;
		this.imagePath = null;
		this.imageExtension = null;
		this.imageNameNoExtension = null;
		
		this.imageHeight = null;
		this.imageWidth = null;


		
		
    }

    @Transient
	public String getImageExtension() {
		if  (this.imageExtension == null) {
			if ( this.imagePath != null && this.imagePath.length() > 0) {
				setImageExtension(this.imagePath.substring(this.imagePath.lastIndexOf(".")+1).trim());
			}else {
				setImageExtension(null);
			}
		}
		return imageExtension;
	}

	/**
	 * @param image1Extension The image1Extension to set.
	 */
	private void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}

	/**
     * Devuelve el path del archivo sin extensión para presentación de archivo FLASH.
     * @return
     */
    @Transient
	public String getImageNameNoExtension() {
		if  (this.imageNameNoExtension == null) {
			if ( this.imagePath != null && this.imagePath.length() > 0) {
				setImageNameNoExtension(this.imagePath.substring(0,this.imagePath.lastIndexOf(".")).trim());
			}else {
				setImageNameNoExtension(null);
			}
		}
		return imageNameNoExtension;
	}

	/**
	 * @param image1Extension The image1Extension to set.
	 */
	private void setImageNameNoExtension(String imageNameNoExtension) {
		this.imageNameNoExtension = imageNameNoExtension;
	}

    public Long getAccessLevelCode() {
		return accessLevelCode;
	}
	public void setAccessLevelCode(Long accessLevelCode) {
		this.accessLevelCode = accessLevelCode;
	}
	public Long getContentCode() {
		return contentCode;
	}
	public void setContentCode(Long contentCode) {
		this.contentCode = contentCode;
	}
	/*
	public Long getDisplayCode() {
		return displayCode;
	}
	public void setDisplayCode(Long displayCode) {
		this.displayCode = displayCode;
	}*/
	public Long getLayoutCode() {
		return layoutCode;
	}
	public void setLayoutCode(Long layoutCode) {
		this.layoutCode = layoutCode;
	}
	public String getLayoutForward() {
		return layoutForward;
	}
	public void setLayoutForward(String layoutForward) {
		this.layoutForward = layoutForward;
	}
	/*
	public Long getMenuItemCode() {
		return menuItemCode;
	}
	public void setMenuItemCode(Long menuItemCode) {
		this.menuItemCode = menuItemCode;
	}*/
	public Long getPortalModuleCode() {
		return portalModuleCode;
	}
	public void setPortalModuleCode(Long portalModuleCode) {
		this.portalModuleCode = portalModuleCode;
	}
	public String getPortalModuleForward() {
		return portalModuleForward;
	}
	public void setPortalModuleForward(String portalModuleForward) {
		this.portalModuleForward = portalModuleForward;
	}

	/**
	 * @return Returns the contentBackground.
	 */
	public String getContentBackground() {
		return contentBackground;
	}

	/**
	 * @param contentBackground The contentBackground to set.
	 */
	public void setContentBackground(String contentBackground) {
		this.setContentBackgroundName(null);
		this.contentBackground = contentBackground;
	}

	/**
	 * @return Returns the contentText.
	 */
	public String getContentText() {
		return contentText;
	}

	/**
	 * @param contentText The contentText to set.
	 */
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	/**
	 * @return Returns the contentTitle.
	 */
	public String getContentTitle() {
		return contentTitle;
	}

	/**
	 * @param contentTitle The contentTitle to set.
	 */
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}


	/**
	 * @return Returns the contentParentCode.
	 */
	public Long getContentParentCode() {
		return contentParentCode;
	}


	/**
	 * @param contentParentCode The contentParentCode to set.
	 */
	public void setContentParentCode(Long contentParentCode) {
		this.contentParentCode = contentParentCode;
	}


	/**
	 * @return Returns the navigator.
	 */
	public ArrayList<RowItem> getNavigator() {
		return navigator;
	}


	/**
	 * @param navigator The navigator to set.
	 */
	public void setNavigator(ArrayList<RowItem> navigator) {
		this.navigator = navigator;
	}


	/**
	 * @return Returns the contentBackgroundName.
	 */
	public String getContentBackgroundName() {
		if (this.contentBackground !=null && contentBackground.length()>0 && this.contentBackgroundName == null)
			setContentBackgroundName (contentBackground.substring(1));
		return contentBackgroundName;
	}


	/**
	 * @param contentBackgroundName The contentBackgroundName to set.
	 */
	private void setContentBackgroundName(String contentBackgroundName) {
		this.contentBackgroundName = contentBackgroundName;
	}
	
	/**
	 * @return Returns the contentBackgroundName.
	 */
	public String getContentParentBackgroundName() {
		if (this.contentParentBackground !=null && contentParentBackground.length()>0 && this.contentParentBackgroundName == null)
			setContentParentBackgroundName (contentParentBackground.substring(1));
		return contentParentBackgroundName;
	}


	/**
	 * @param contentBackgroundName The contentBackgroundName to set.
	 */
	private void setContentParentBackgroundName(String contentParentBackgroundName) {
		this.contentParentBackgroundName = contentParentBackgroundName;
	}
	

	public String getContentParentTitle() {
		return contentParentTitle;
	}

	public void setContentParentTitle(String contentParentTitle) {
		this.contentParentTitle = contentParentTitle;
	}

	public String getContentParentBackground() {
		return contentParentBackground;
	}

	public void setContentParentBackground(String contentParentBackground) {
		this.setContentParentBackgroundName(null);
		this.contentParentBackground = contentParentBackground;
		
	}

	public Integer getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}
	
	

	
	

	
	

}