/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yage.file.FileExtensionUtils;

/*
 * This class persist data to the <code>tb_image</code> database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 2.0
 */

@Entity
@Table(name="tb_image")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="image",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Image {
	
    private Long code;

    private String name;
    
    private String path;
    
    private Integer height;
    
    private Integer width;

    private ImageCategory imageCategory;
    
    private transient String pathIcon;
    
    private transient String imageExtension;
    
    private transient String imageNameNoExtension;
    
    /**
     * Creates a new instance of Account
     */
    public Image() {
        super();
    }

    public String toString(){
    	return this.path;
    }

    @Id 
    @Column(name="image_code")
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

	@Column(name="image_name")
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="image_height")
	public Integer getHeight() {
		return height;
	}
	/**
	 * @param height The height to set.
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	@Column(name="image_path")
	public String getPath() {
		return path;
	}
	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column (name="image_width")
	public Integer getWidth() {
		return width;
	}
	/**
	 * @param width The width to set.
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	@ManyToOne
	@JoinColumn(name="image_category_code")
	public ImageCategory getImageCategory() {
		return imageCategory;
	}
	/**
	 * @param imageCategory The imageCategory to set.
	 */
	public void setImageCategory(ImageCategory imageCategory) {
		this.imageCategory = imageCategory;
	}
	
	@Transient
	public String getInfo(){
		StringBuffer str = new StringBuffer();
		str.append("Categoría: " + imageCategory.getName());
		str.append(" - > Nombre: " + name);
		str.append(" - > Width: " + width);
		str.append(" - > Height: " + height);
		return str.toString();
	}

	/**
     * Devuelve la extensión del archivo adjunto para presentar 
     * el ícono en lugar del nombre del archivo.
     * @return
     */
    @Transient
    public String getPathIcon () {
    	if (this.pathIcon == null && this.path != null) {
    		setPathIcon (FileExtensionUtils.getFileIcon( this.path ));
    	}
    
    	return  this.pathIcon;
    }

	public void setPathIcon(String pathIcon) {
		this.pathIcon = pathIcon;
	}
	
	/**
     * Devuelve la extensión del archivo adjunto.
     * @return
     */
    @Transient
	public String getImageExtension() {
		if  (this.imageExtension == null) {
			if ( this.path != null && this.path.length() > 0) {
				setImageExtension(this.path.substring(this.path.lastIndexOf(".")+1).trim());
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
			if ( this.path != null && this.path.length() > 0) {
				setImageNameNoExtension(this.path.substring(0,this.path.lastIndexOf(".")).trim());
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
 }