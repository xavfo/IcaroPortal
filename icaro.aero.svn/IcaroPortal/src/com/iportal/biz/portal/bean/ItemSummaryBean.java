/**
 * 
 */
package com.iportal.biz.portal.bean;

/**
 * Bean de resumen para cargar informacion relacionada a contenidos derivados
 * 
 * @author hernan
 * @version 1.0
 * 
 */
public class ItemSummaryBean {

	private Long code;

	private String name;

	private String description;

	private String link;

	private String imagePath;

	private Integer imageHeight;

	private Integer imageWidth;

	private String accessImageExtension;

	private String accessImageNameNoExtension;

	private String text;
	
	private String intro;

	public ItemSummaryBean() {
		super();
		this.clear();
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

	private void clear() {
		this.code = null;
		this.name = null;
		this.description = null;
		this.link = null;
		this.imagePath = null;
		this.accessImageExtension = null;
		this.accessImageNameNoExtension = null;
		this.imageHeight = null;
		this.imageWidth = null;
		this.text = null;
		this.intro = null;

	}

	public ItemSummaryBean(Long code, String name, String description,
			String link, String accessImagePath, Integer imageHeight,
			Integer imageWidth) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.link = link;
		this.imagePath = accessImagePath;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
	}

	public ItemSummaryBean(Long code, String name, String description,
			String link, String accessImagePath, Integer imageHeight,
			Integer imageWidth, String text) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.link = link;
		this.imagePath = accessImagePath;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.text = text;
	}
	
	public ItemSummaryBean(Long code, String name, String description,
			String link, String accessImagePath, Integer imageHeight,
			Integer imageWidth, String text, String intro) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.link = link;
		this.imagePath = accessImagePath;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		this.text = text;
		this.intro = intro;
	}

	/**
	 * Devuelve la extensión del archivo adjunto.
	 * 
	 * @return
	 */
	public String getAccessImageExtension() {
		if (this.accessImageExtension == null) {
			if (this.imagePath != null && this.imagePath.length() > 0) {
				setAccessImageExtension(this.imagePath.substring(
						this.imagePath.lastIndexOf(".") + 1).trim());
			} else {
				setAccessImageExtension(null);
			}
		}
		return accessImageExtension;
	}

	/**
	 * Devuelve el path del archivo sin extensi�n para presentación de
	 * archivo FLASH.
	 * 
	 * @return
	 */
	public String getAccessImageNameNoExtension() {
		if (this.accessImageNameNoExtension == null) {
			if (this.imagePath != null && this.imagePath.length() > 0) {
				setAccessImageNameNoExtension(this.imagePath.substring(0,
						this.imagePath.lastIndexOf(".")).trim());
			} else {
				setAccessImageNameNoExtension(null);
			}
		}
		return accessImageNameNoExtension;
	}

	public String getAccessImagePath() {
		return imagePath;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	public Long getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Integer getImageHeight() {
		return imageHeight;
	}

	public Integer getImageWidth() {
		return imageWidth;
	}

	public String getName() {
		return name;
	}

	public void setAccessImagePath(String accessImagePath) {
		this.imagePath = accessImagePath;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param image1Extension
	 *            The image1Extension to set.
	 */
	private void setAccessImageExtension(String imageExtension) {
		this.accessImageExtension = imageExtension;
	}

	/**
	 * @param image1Extension
	 *            The image1Extension to set.
	 */
	private void setAccessImageNameNoExtension(String imageNameNoExtension) {
		this.accessImageNameNoExtension = imageNameNoExtension;
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

}
