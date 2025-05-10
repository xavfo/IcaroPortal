/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
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

/*
 * This class persist data to the <code>tb_banner</code> database table.
 * Ahora es un acceso directo a cualquier recurso
 * 
 * @author  YAGE (hleon)
 * @version 2.0
 */

@Entity
@Table(name="tb_access")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="access",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Access {
	
    private Long code;

    private String name;
    
    private String description;
    
    private String url;
    
    private String path;
    
    private Integer height;
    
    private Integer width;
    
    private Long relatedCode;
    
    private String relatedName;
    
    private AccessUrl accessUrl;
    
    private transient String pathExtension;
    
    private transient String pathNameNoExtension;
    
    /**
     * Creates a new instance of Account
     */
    public Access() {
        super();
    }
    
    
    
    /**
     * Tipo de URL que debe tener el acceso directo relacionado
	 * @return Returns the accessUrl.
	 */
	@ManyToOne
	@JoinColumn(name="access_url_code")
	public AccessUrl getAccessUrl() {
		return accessUrl;
	}

	@Id 
    @Column(name="access_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
    public Long getCode() {
        return code;
    }  

	@Column(name="access_desc")
	public String getDescription() {
		return description;
	}

    @Column(name="access_height")
	public Integer getHeight() {
		return height;
	}

    /**
	 * @return Returns the name.
	 */
	@Column(name="access_name")
	public String getName() {
		return name;
	}

	@Column(name="access_path")
	public String getPath() {
		return path;
	}
	
	@Column(name="access_related_code")
	public Long getRelatedCode() {
		return relatedCode;
	}
	
	@Column(name="access_related_title")
	public String getRelatedName() {
		return relatedName;
	}

	@Column(name="access_url")
	public String getUrl() {
		return url;
	}

	@Column(name="access_width")
	public Integer getWidth() {
		return width;
	}

	@Transient
	public String getWebPath() {
		return path;
	}

	@Transient
	public String getInfo(){
		StringBuffer str = new StringBuffer();
		str.append(" - > Nombre: " + name);
		str.append(" - > URL: " + url);
		str.append(" - > Width: " + width);
		str.append(" - > Height: " + height);
		return str.toString();
	}
	@Transient
	public boolean getIsFlash(){
		String ext = path.substring(path.lastIndexOf('.'));
		if (ext.equals(".swf"))
			return true;
		return false;
	}

	/**
     * Devuelve la extension del archivo adjunto.
     * @return
     */
    @Transient
	public String getPathExtension() {
		if  (this.pathExtension == null) {
			if ( this.path != null && this.path.length() > 0) {
				setPathExtension(this.path.substring(this.path.lastIndexOf(".")+1).trim());
			}else {
				setPathExtension(null);
			}
		}
		return pathExtension;
	}

	/**
     * Devuelve el path del archivo sin extensi�n para presentacion de archivo FLASH.
     * @return
     */
    @Transient
	public String getPathNameNoExtension() {
		if  (this.pathNameNoExtension == null) {
			if ( this.path != null && this.path.length() > 0) {
				setPathNameNoExtension(this.path.substring(0,this.path.lastIndexOf(".")).trim());
			}else {
				setPathNameNoExtension(null);
			}
		}
		return pathNameNoExtension;
	}

    /**
	 * @param accessUrl The accessUrl to set.
	 */
	public void setAccessUrl(AccessUrl accessUrl) {
		this.accessUrl = accessUrl;
	}

	/**
     * @param code The code to set.
     */
    public void setCode(Long code) {
        this.code = code;
    }

	/**
	 * @param height The height to set.
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	
	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @param width The width to set.
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}


	public void setRelatedCode(Long relatedCode) {
		this.relatedCode = relatedCode;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}
	

	/**
	 * @param image1Extension The image1Extension to set.
	 */
	private void setPathExtension(String imageExtension) {
		this.pathExtension = imageExtension;
	}
	
	/**
	 * @param image1Extension The image1Extension to set.
	 */
	private void setPathNameNoExtension(String imageNameNoExtension) {
		this.pathNameNoExtension = imageNameNoExtension;
	}


	public void setDescription(String description) {
		this.description = description;
	}
}
