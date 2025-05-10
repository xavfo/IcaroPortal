/*
 * Created Jan 9, 2007
 *	Product.java
 */
package com.iportal.cart.model.catalog;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.iportal.cart.CartConstants;
import com.iportal.cart.model.tax.TaxType;
import com.yage.Globals;



/**
 * Cat�logo de productos del sitio de comercio electr�nico. Aqui se almacenan
 * todos los datos de los productos comercializados
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_product")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="product",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								

public class Product {
	
	private Long code;
	
	private String name;
	
	private Brand brand;
	
	private Seller seller;
	
	private Category category;
	
	private TaxType taxType;
	
	private Boolean enabled;
	
	private Boolean featured;
	
	private Boolean showRoom;
	
	private Boolean onSale;
	
	private Boolean onClearance;
	
	private Calendar creation;
	
	private Calendar lastUpdate;
	
	private Integer stock;
	
	private Integer minStock;
	
	private Integer maxStock;
	
	private Double regularPrice;
	
	private Double salePrice;
	
	private Double clearancePrice;
	
	private String features;
	
	private String description;
	
	private String slogan;
	
	private String image1;
	
	private String image2;
	
	private String image3;
	
	private String modelNumber;
	
	private String externalCode;
	
	private Float shippingWidth;
	
	private Float shippingHeight;
	
	private Float shippingDepth;
	
	private Float shippingWeight;
	
	private Double quotaPrice;
	
	private Integer numberOfQuotas;
	
	private Calendar clearanceSince;
	
	private Calendar clearanceUntil;
	
	private Set<Product> related;
	
	private transient Float weightTocharge;
	
	private transient Float volumetricWeight;
	
	
	/**
	 * 
	 */
	public Product() {
		super();
		this.code = null;
		this.name = null;
		this.brand = null;
		this.seller = null;
		this.category = null;
		this.enabled = null;
		this.featured = null;
		this.showRoom = null;
		this.onSale = null;
		this.creation = null;
		this.lastUpdate = null;
		this.stock = null;
		this.minStock = null;
		this.maxStock = null;
		this.regularPrice = null;
		this.salePrice = null;
		this.features = null;
		this.description = null;
		this.slogan = null;
		this.image1 = null;
		this.image2 = null;
		this.image3 = null;
		this.modelNumber = null;
		this.externalCode = null;
		this.shippingWidth = null;
		this.shippingHeight = null;
		this.shippingDepth = null;
		this.shippingWeight = null;
		this.quotaPrice = null;
		this.numberOfQuotas = null;
		if (this.related != null) {
			this.related = null;
		}
		this.weightTocharge   = null;
		this.volumetricWeight = null;
		this.clearanceSince = null;
		this.clearancePrice = null;
		this.clearanceUntil = null;
		this.onClearance    = null;
		this.taxType        = null;
	}
	
	@Transient
	public Float getVolume () {
		return this.getShippingHeight()*this.getShippingWidth()*this.getShippingDepth();
	}
	
	@Transient
	public Boolean getIsOnClearance () {
		Boolean resp = Globals.FALSE;
		if (this.getOnClearance() && this.getClearanceSince() != null && this.getClearanceUntil() != null) {
			Calendar today = Calendar.getInstance();
			if (today.after(this.getClearanceSince()) && today.before(this.getClearanceUntil())) {
				resp = Globals.TRUE;
			}
		} 
		return resp;
	}
	
	/**
	 * Calcula y devuelve el peso volumetrico del producto
	 * publicado en el cat�logo del carrito de compras.
	 * 
	 * @return peso volum�trico de producto. 
	 */
	@Transient
	public Float getVolumetricWeight () {
		if (this.volumetricWeight == null) {
			this.volumetricWeight = this.getVolume()/CartConstants.CART_VOLUMETRIC_WEIGHT_FACTOR;
			//solicita que se vuela a calcular el peso a cobrar por este paquete
			this.setWeightTocharge(null);
		}
		return this.volumetricWeight;
	}

	/**
	 * Calcula y devuelve el valor del peso que debe usarse para 
	 * calcular el peso del paquete al momento de calcular los
	 * costos  de env�o de un paquete.
	 * @return
	 */
	/**
	 * @return
	 */
	@Transient
	public Float getWeightToCharge () {
		if (this.weightTocharge == null) {
			this.weightTocharge = (this.getShippingWeight().floatValue() >= this.getVolumetricWeight().floatValue()) ?this.getShippingWeight():this.getVolumetricWeight();
		}
		return this.weightTocharge;
	}


	/**
	 * @return Returns the brand.
	 */
	@ManyToOne
	@JoinColumn(name="brand_code")
	public Brand getBrand() {
		return brand;
	}


	/**
	 * @return Returns the category.
	 */
	@ManyToOne
	@JoinColumn(name="category_code")
	public Category getCategory() {
		return category;
	}


	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="product_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}
    
	/**
	 * @return Returns the clearancePrice.
	 */
    @Column(name="product_clearance_price")
	public Double getClearancePrice() {
		return clearancePrice;
	}

	/**
	 * @return Returns the clearanceSince.
	 */
    @Column(name="product_clearance_since")
	public Calendar getClearanceSince() {
		return clearanceSince;
	}

	/**
	 * @return Returns the clearanceUntil.
	 */
    @Column(name="product_clearance_until")
	public Calendar getClearanceUntil() {
		return clearanceUntil;
	}

	/**
	 * @return Returns the creation.
	 */
    @Column(name="product_creation")
	public Calendar getCreation() {
		return creation;
	}


	/**
	 * @return Returns the description.
	 */
    @Column(name="product_description")
	public String getDescription() {
		return description;
	}


	/**
	 * @return Returns the enabled.
	 */
    @Column(name="product_enabled")
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * @return Returns the externalCode.
	 */
    @Column(name="product_external_code")
	public String getExternalCode() {
		return externalCode;
	}


	/**
	 * @return Returns the featured.
	 */
    @Column(name="product_featured")
	public Boolean getFeatured() {
		return featured;
	}


	/**
	 * @return Returns the features.
	 */
    @Column(name="product_features")
	public String getFeatures() {
		return features;
	}


	/**
	 * @return Returns the image1.
	 */
    @Column(name="product_image1")
	public String getImage1() {
		return image1;
	}


	/**
	 * @return Returns the image2.
	 */
    @Column(name="product_image2")
	public String getImage2() {
		return image2;
	}


	/**
	 * @return Returns the image3.
	 */
    @Column(name="product_image3")
	public String getImage3() {
		return image3;
	}



	/**
	 * @return Returns the lastUpdate.
	 */
    @Column(name="product_last_update")
	public Calendar getLastUpdate() {
		return lastUpdate;
	}


	/**
	 * @return Returns the maxStock.
	 */
    @Column(name="product_max")
	public Integer getMaxStock() {
		return maxStock;
	}


	/**
	 * @return Returns the minStock.
	 */
    @Column(name="product_min")
	public Integer getMinStock() {
		return minStock;
	}


	/**
	 * @return Returns the modelNumber.
	 */
    @Column(name="product_model_number")
	public String getModelNumber() {
		return modelNumber;
	}


	/**
	 * @return Returns the name.
	 */
    @Column(name="product_name")
	public String getName() {
		return name;
	}


	/**
	 * @return Returns the numberOfQuotas.
	 */
    @Column(name="product_quota_num")
	public Integer getNumberOfQuotas() {
		return numberOfQuotas;
	}

    
	/**
	 * @return Returns the onClearance.
	 */
    @Column(name="product_on_clearance")
	public Boolean getOnClearance() {
		return onClearance;
	}

	/**
	 * @return Returns the onSale.
	 */
    @Column(name="product_on_sale")
	public Boolean getOnSale() {
		return onSale;
	}


	/**
	 * @return Returns the quotaPrice.
	 */
    @Column(name="product_quota_price")
	public Double getQuotaPrice() {
		return quotaPrice;
	}


	/**
	 * @return Returns the regularPrice.
	 */
    @Column(name="product_regular_price")
	public Double getRegularPrice() {
		return regularPrice;
	}

    @Transient
    public Double getCurrentPrice() {
        return getPriceForDate(GregorianCalendar.getInstance());
    }

    @Transient
    public Double getPriceForDate(Calendar _calendar) {
        Double rval = regularPrice;
        if (this.getOnClearance() && this.getClearanceSince() != null && this.getClearanceUntil() != null) {
            if (_calendar.after(this.getClearanceSince()) && _calendar.before(this.getClearanceUntil())) {
                rval = clearancePrice;
            }
        } else if( this.getOnSale() ) {
            rval = salePrice;
        }
        return rval;
    }

	/**
	 * @return Returns the salePrice.
	 */
    @Column(name="product_sale_price")
	public Double getSalePrice() {
		return salePrice;
	}


	/**
	 * @return Returns the seller.
	 */
    
	@ManyToOne
	@JoinColumn(name="seller_code")
	public Seller getSeller() {
		return seller;
	}


	/**
	 * @return Returns the shippingDepth.
	 */
	@Column(name="product_shipping_depth")
	public Float getShippingDepth() {
		return shippingDepth;
	}


	/**
	 * @return Returns the shippingHeight.
	 */
	@Column(name="product_shipping_height")
	public Float getShippingHeight() {
		return shippingHeight;
	}

	/**
	 * @return Returns the shippingWeight.
	 */
	@Column(name="product_shipping_weight")
	public Float getShippingWeight() {
		return shippingWeight;
	}
	


	/**
	 * @return Returns the shippingWidth.
	 */
	@Column(name="product_shipping_width")
	public Float getShippingWidth() {
		return shippingWidth;
	}
	
	


	/**
	 * @return Returns the showRoom.
	 */
	@Column(name="product_show_room")
	public Boolean getShowRoom() {
		return showRoom;
	}

	/**
	 * @return Returns the slogan.
	 */
	@Column(name="product_slogan")
	public String getSlogan() {
		return slogan;
	}


	/**
	 * @return Returns the stock.
	 */
	@Column(name="product_stock")
	public Integer getStock() {
		return stock;
	}

	
	/**
	 * @return Returns the taxType.
	 */
	@ManyToOne
	@JoinColumn(name="tax_type_code")
	public TaxType getTaxType() {
		return taxType;
	}

	/**
	 * @return Returns the related.
	 */
	@ManyToMany
	@JoinTable(
				name="tb_products_related",
				joinColumns = {@JoinColumn(name="PRODUCT_PARENT")},
				inverseJoinColumns = @JoinColumn(name="PRODUCT_CODE")
			 )
    @Where(clause="product_enabled = 1 ")
    @OrderBy("name")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)			 
	public Set<Product> getRelated() {
		return related;
	}
	
	


	/**
	 * @param related The related to set.
	 */
	public void setRelated(Set<Product> related) {
		this.related = related;
	}


	/**
	 * @param brand The brand to set.
	 */
	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	/**
	 * @param category The category to set.
	 */
	public void setCategory(Category category) {
		this.category = category;
	}


	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}
	

	/**
	 * @param clearancePrice The clearancePrice to set.
	 */
	public void setClearancePrice(Double clearancePrice) {
		this.clearancePrice = clearancePrice;
	}

	/**
	 * @param clearanceSince The clearanceSince to set.
	 */
	public void setClearanceSince(Calendar clearanceSince) {
		this.clearanceSince = clearanceSince;
	}

	/**
	 * @param clearanceUntil The clearanceUntil to set.
	 */
	public void setClearanceUntil(Calendar clearanceUntil) {
		this.clearanceUntil = clearanceUntil;
	}


	/**
	 * @param creation The creation to set.
	 */
	public void setCreation(Calendar creation) {
		this.creation = creation;
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
	 * @param externalCode The externalCode to set.
	 */
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}


	/**
	 * @param featured The featured to set.
	 */
	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}


	/**
	 * @param features The features to set.
	 */
	public void setFeatures(String features) {
		this.features = features;
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
	 * @param image3 The image3 to set.
	 */
	public void setImage3(String image3) {
		this.image3 = image3;
	}



	/**
	 * @param lastUpdate The lastUpdate to set.
	 */
	public void setLastUpdate(Calendar lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	/**
	 * @param maxStock The maxStock to set.
	 */
	public void setMaxStock(Integer maxStock) {
		this.maxStock = maxStock;
	}


	/**
	 * @param minStock The minStock to set.
	 */
	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}


	/**
	 * @param modelNumber The modelNumber to set.
	 */
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}


	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @param numberOfQuotas The numberOfQuotas to set.
	 */
	public void setNumberOfQuotas(Integer numberOfQuotas) {
		this.numberOfQuotas = numberOfQuotas;
	}

	/**
	 * @param onClearance The onClearance to set.
	 */
	public void setOnClearance(Boolean onClearance) {
		this.onClearance = onClearance;
	}


	/**
	 * @param onSale The onSale to set.
	 */
	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}


	/**
	 * @param quotaPrice The quotaPrice to set.
	 */
	public void setQuotaPrice(Double quotaPrice) {
		this.quotaPrice = quotaPrice;
	}


	/**
	 * @param regularPrice The regularPrice to set.
	 */
	public void setRegularPrice(Double regularPrice) {
		this.regularPrice = regularPrice;
	}


	/**
	 * @param salePrice The salePrice to set.
	 */
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}


	/**
	 * @param seller The seller to set.
	 */
	public void setSeller(Seller seller) {
		this.seller = seller;
	}


	/**
	 * @param shippingDepth The shippingDepth to set.
	 */
	public void setShippingDepth(Float shippingDepth) {
		//Debe recalcularse el peso volumetrico Si se cambio una de las medidas
		if (this.shippingDepth == null || !this.shippingDepth.equals(shippingDepth)) {
			this.setVolumetricWeight(null);
		}
		this.shippingDepth = shippingDepth;
	}


	/**
	 * @param shippingHeight The shippingHeight to set.
	 */
	public void setShippingHeight(Float shippingHeight) {
		//Debe recalcularse el peso volumetrico Si se cambio una de las medidas
		if (this.shippingHeight == null || !this.shippingHeight.equals(shippingHeight)) {
			this.setVolumetricWeight(null);
		}

		this.shippingHeight = shippingHeight;
	}


	/**
	 * @param shippingWeight The shippingWeight to set.
	 */
	public void setShippingWeight(Float shippingWeight) {
		if (this.shippingWeight == null || !this.shippingWeight.equals(shippingWeight)) {
			/*como cambia el valor del peso debe cvalcularse de nuevo
			 el peso de envio*/
			this.setWeightTocharge(null);
		}
		this.shippingWeight = shippingWeight;
		
		
	}
	
	/**
	 * @param shippingWidth The shippingWidth to set.
	 */
	public void setShippingWidth(Float shippingWidth) {
		//Debe recalcularse el peso volumetrico Si se cambio una de las medidas
		if (this.shippingWidth == null || !this.shippingWidth.equals(shippingWidth)) {
			this.setVolumetricWeight(null);
		}

		this.shippingWidth = shippingWidth;
	}


	
	
	/**
	 * @param showRoom The showRoom to set.
	 */
	public void setShowRoom(Boolean showRoom) {
		this.showRoom = showRoom;
	}

	/**
	 * @param slogan The slogan to set.
	 */
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}


	/**
	 * @param stock The stock to set.
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	/**
	 * @param taxType The taxType to set.
	 */
	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
	}


	/**
	 * @param volumetricWeight The volumetricWeight to set.
	 */
	public void setVolumetricWeight(Float volumetricWeight) {
		this.volumetricWeight = volumetricWeight;
	}

	/**
	 * @param weightTocharge The weightTocharge to set.
	 */
	public void setWeightTocharge(Float weightTocharge) {
		this.weightTocharge = weightTocharge;
	}
	
	
	
	

}
