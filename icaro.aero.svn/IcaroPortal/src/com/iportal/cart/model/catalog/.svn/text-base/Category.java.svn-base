/*
 * Created Jan 9, 2007
 *	Category.java
 */
package com.iportal.cart.model.catalog;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yage.Globals;

/**
 * @author hernan
 *
 */
@Entity
@Table(name="tb_category")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="category",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Category {

 
	
	private Long code;
	
	private String name;
	
	private Category parent;
	
	private Boolean enabled;
	
	private Boolean group;
	
	private Boolean featured;
	
	private Integer level;
	
	private Integer orderIndex;
	
	private String image;
	
	private String description;
	
	private Set<Category> subCategories;
	
	private transient String imageExtension;
	    
	private transient String imageNameNoExtension;
	
	private transient Boolean hasChildren;
	
	private transient List subCategoryList;
	
	


	/**
	 * 
	 */
	public Category() {
		super();
		this.code  = null;
		this.name  = null;
		this.enabled  = null;
		this.parent  = null;
		this.group   = null;
		this.level   = null;
		this.featured = null;
		this.image    = null;
		this.description = null;
		this.orderIndex  = null;
		this.subCategories = null;
		this.imageExtension = null;
		this.imageNameNoExtension = null;
		
		this.hasChildren = Globals.FALSE;
		this.subCategoryList = null;
		

	}
	
	
	
    /**
     * Indicador de si categoría tiene o no descendientes para saber si
     * debe filtra las subcategorias destacadas o no.
     * @return
     */
	@Transient
    public Boolean getHasChildren() {
		return hasChildren;
	}



	/**
	 * Devuelve la lista de categorias descendientes de esta o
	 * hermanas para mostrar en la pagina de categoria o subcategoria.
	 * @return
	 */
    @Transient
	public List getSubCategoryList() {
		return subCategoryList;
	}



	@Transient
	public String getImageExtension() {
		if  (this.imageExtension == null) {
			if ( this.image != null && this.image.length() > 0) {
				setImageExtension(this.image.substring(this.image.lastIndexOf(".")+1).trim());
			}else {
				setImageExtension(null);
			}
		}
		return imageExtension;
	}
    
	/**
     * Devuelve el path del archivo sin extensión para presentación de archivo FLASH.
     * @return
     */
    @Transient
	public String getImageNameNoExtension() {
		if  (this.imageNameNoExtension == null) {
			if ( this.image != null && this.image.length() > 0) {
				setImageNameNoExtension(this.image.substring(0,this.image.lastIndexOf(".")).trim());
			}else {
				setImageNameNoExtension(null);
			}
		}
		return imageNameNoExtension;
	}



	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="category_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}
    
	/**
	 * @return Returns the description.
	 */
    @Column(name="category_description")
	public String getDescription() {
		return description;
	}


	/**
	 * @return Returns the enabled.
	 */
    @Column(name="category_enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @return Returns the featured.
	 */
    @Column(name="category_featured")
	public Boolean getFeatured() {
		return featured;
	}

    /**
	 * @return Returns the group.
	 */
    @Column(name="category_group")
	public Boolean getGroup() {
		return group;
	}
    
	/**
	 * @return Returns the image.
	 */
    @Column(name="category_image")
	public String getImage() {
		return image;
	}


	/**
	 * @return Returns the level.
	 */
    @Column(name="category_level")
	public Integer getLevel() {
		return level;
	}

	/**
	 * @return Returns the name.
	 */
    @Column(name="category_name")
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the orderIndex.
	 */
    @Column(name="category_order_index")
	public Integer getOrderIndex() {
		return orderIndex;
	}

	/**
	 * @return Returns the parent.
	 */
	@ManyToOne
	@JoinColumn(name="category_parent")
	public Category getParent() {
		return parent;
	}

	
	
	/**
	 * @return Returns the subCategories.
	 */
	@OneToMany( mappedBy="parent" )
	public Set<Category> getSubCategories() {
		return subCategories;
	}
    
	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
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
	 * @param featured The featured to set.
	 */
	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}


	/**
	 * @param group The group to set.
	 */
	public void setGroup(Boolean group) {
		this.group = group;
	}

	/**
	 * @param image The image to set.
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}

	public void setImageNameNoExtension(String imageNameNoExtension) {
		this.imageNameNoExtension = imageNameNoExtension;
	}


	/**
	 * @param level The level to set.
	 */
	public void setLevel(Integer level) {
		this.level = level;
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
	 * @param parent The parent to set.
	 */
	public void setParent(Category parent) {
		this.parent = parent;
	}
	
	/**
	 * @param subCategories The subCategories to set.
	 */
	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}



	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}



	public void setSubCategoryList(List subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	
	
	

}
