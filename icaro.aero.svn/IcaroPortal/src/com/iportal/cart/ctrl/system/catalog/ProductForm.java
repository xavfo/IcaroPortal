/*
 * Created Jan 15, 2007
 *    ProductForm.java
 */
package com.iportal.cart.ctrl.system.catalog;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.cart.model.catalog.Product;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * Forma para almecenar informaci�n de productos
 * @author hernan
 * @version 1.0
 *
 */
public class ProductForm extends BaseForm {

    private static final long serialVersionUID = 8546698949560311781L;

    private String name;

    private Long brandCode;

    private Long sellerCode;

    private Long categoryCode;

    private Long taxTypeCode;

    private String categoryName;

    private Boolean enabled;

    private Integer enabledOption;

    private Boolean featured;

    private Boolean showRoom;

    private Integer featuredOption;

    private Boolean onSale;

    private Boolean onClearance;

    private Integer onSaleOption;

    private Integer onClearanceOption;



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

    private Double quotaPrice;

    private Integer numberOfQuotas;

    private Double fromPrice;

    private Double toPrice;

    private Integer searchBySalePriceOption;

    private Float shippingWidth;

    private Float shippingHeight;

    private Float shippingDepth;

    private Float shippingWeight;

    private Product sessionProduct;

    private String clearanceSinceDate;

    private String clearanceUntilDate;



    /**
     *
     */
    public ProductForm() {
        super();
        this.clear();
    }

    public void clear () {
        this.brandCode    = null;
        this.sellerCode   = null;
        this.categoryCode = null;
        this.taxTypeCode  = null;
        this.categoryName = null;
        this.name    = null;
        this.enabled = Globals.FALSE;
        this.enabledOption = null;
        this.featured = Globals.FALSE;
        this.showRoom = Globals.FALSE;
        this.featuredOption = null;
        this.onSale = Globals.FALSE;
        this.onClearance = Globals.FALSE;
        this.onSaleOption = null;
        this.onClearanceOption = null;
        this.stock = null;
        this.minStock = null;
        this.maxStock = null;
        this.regularPrice   = null;
        this.salePrice      = null;
        this.clearancePrice = null;
        this.features = null;
        this.description = null;
        this.slogan = null;
        this.image1 = null;
        this.image2 = null;
        this.image3 = null;
        this.modelNumber = null;
        this.externalCode = null;

        this.shippingWeight = null;
        //dimensiones
        this.shippingWidth  = null;
        this.shippingHeight = null;
        this.shippingDepth  = null;

        this.quotaPrice = null;
        this.numberOfQuotas = null;

        this.fromPrice = null;
        this.toPrice   = null;
        this.searchBySalePriceOption = null;

        this.clearanceSinceDate = null;
        this.clearanceUntilDate = null;

        this.sessionProduct = null;

    }

    /* (non-Javadoc)
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();
    }

    public Calendar getClearanceSince() {

        Calendar calendar = new GregorianCalendar();

        if ( this.getClearanceSinceDate() != null && this.getClearanceSinceDate().length() > 0  ) {

            calendar.setTime( DateFormatUtils.parseToDate(this.getClearanceSinceDate(), Constants.DATE_FORMAT) );
            return calendar;
        }
        return null;


    }

    public Calendar getClearanceUntil() {

        Calendar calendar = new GregorianCalendar();

        if ( this.getClearanceUntilDate() != null && this.getClearanceUntilDate().length() > 0  ) {

            calendar.setTime( DateFormatUtils.parseToDate(this.getClearanceUntilDate(), Constants.DATE_FORMAT) );
            return calendar;
        }
        return null;


    }

    public void setClearanceSince(Calendar calendar) {
        if ( calendar != null )

            this.clearanceSinceDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
    }

    public void setClearanceUntil(Calendar calendar) {
        if ( calendar != null )

            this.clearanceUntilDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
    }


    /**
     * @return Returns the brandCode.
     */
    public Long getBrandCode() {
        return brandCode;
    }

    /**
     * @return Returns the categoryCode.
     */
    public Long getCategoryCode() {
        return categoryCode;
    }

    /**
     * @return Returns the categoryName.
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @return Returns the clearancePrice.
     */
    public Double getClearancePrice() {
        return clearancePrice;
    }

    /**
     * @return Returns the clearanceSinceDate.
     */
    public String getClearanceSinceDate() {
        return clearanceSinceDate;
    }

    /**
     * @return Returns the clearanceUntilDate.
     */
    public String getClearanceUntilDate() {
        return clearanceUntilDate;
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
     * @return Returns the enabledOption.
     */
    public Integer getEnabledOption() {
        return enabledOption;
    }

    /**
     * @return Returns the externalCode.
     */
    public String getExternalCode() {
        return externalCode;
    }

    /**
     * @return Returns the featured.
     */
    public Boolean getFeatured() {
        return featured;
    }

    /**
     * @return Returns the featuredOption.
     */
    public Integer getFeaturedOption() {
        return featuredOption;
    }

    /**
     * @return Returns the features.
     */
    public String getFeatures() {
        return features;
    }

    /**
     * @return Returns the fromPrice.
     */
    public Double getFromPrice() {
        return fromPrice;
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
     * @return Returns the image3.
     */
    public String getImage3() {
        return image3;
    }

    /**
     * @return Returns the maxStock.
     */
    public Integer getMaxStock() {
        return maxStock;
    }

    /**
     * @return Returns the minStock.
     */
    public Integer getMinStock() {
        return minStock;
    }

    /**
     * @return Returns the modelNumber.
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the numberOfQuotas.
     */
    public Integer getNumberOfQuotas() {
        return numberOfQuotas;
    }

    /**
     * @return Returns the onClearance.
     */
    public Boolean getOnClearance() {
        return onClearance;
    }

    /**
     * @return Returns the onClearanceOption.
     */
    public Integer getOnClearanceOption() {
        return onClearanceOption;
    }

    /**
     * @return Returns the onSale.
     */
    public Boolean getOnSale() {
        return onSale;
    }

    /**
     * @return Returns the onSaleOption.
     */
    public Integer getOnSaleOption() {
        return onSaleOption;
    }

    /**
     * @return Returns the quotaPrice.
     */
    public Double getQuotaPrice() {
        return quotaPrice;
    }

    /**
     * @return Returns the regularPrice.
     */
    public Double getRegularPrice() {
        return regularPrice;
    }

    /**
     * @return Returns the salePrice.
     */
    public Double getSalePrice() {
        return salePrice;
    }

    /**
     * @return Returns the searchBySalePriceOption.
     */
    public Integer getSearchBySalePriceOption() {
        return searchBySalePriceOption;
    }

    /**
     * @return Returns the sellerCode.
     */
    public Long getSellerCode() {
        return sellerCode;
    }

    /**
     * @return Returns the sessionProduct.
     */
    public Product getSessionProduct() {
        return sessionProduct;
    }

    /**
     * @return Returns the shippingDepth.
     */
    public Float getShippingDepth() {
        return shippingDepth;
    }

    /**
     * @return Returns the shippingHeight.
     */
    public Float getShippingHeight() {
        return shippingHeight;
    }

    /**
     * @return Returns the shippingWeight.
     */
    public Float getShippingWeight() {
        return shippingWeight;
    }

    /**
     * @return Returns the shippingWidth.
     */
    public Float getShippingWidth() {
        return shippingWidth;
    }



    /**
     * @return Returns the showRoom.
     */
    public Boolean getShowRoom() {
        return showRoom;
    }

    /**
     * @return Returns the slogan.
     */
    public String getSlogan() {
        return slogan;
    }

    /**
     * @return Returns the stock.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @return Returns the taxTypeCode.
     */
    public Long getTaxTypeCode() {
        return taxTypeCode;
    }

    /**
     * @return Returns the toPrice.
     */
    public Double getToPrice() {
        return toPrice;
    }

    /**
     * @param brandCode The brandCode to set.
     */
    public void setBrandCode(Long brandCode) {
        this.brandCode = brandCode;
    }

    /**
     * @param categoryCode The categoryCode to set.
     */
    public void setCategoryCode(Long categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * @param categoryName The categoryName to set.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @param clearancePrice The clearancePrice to set.
     */
    public void setClearancePrice(Double clearancePrice) {
        this.clearancePrice = clearancePrice;
    }

    /**
     * @param clearanceSinceDate The clearanceSinceDate to set.
     */
    public void setClearanceSinceDate(String clearanceSinceDate) {
        this.clearanceSinceDate = clearanceSinceDate;
    }

    /**
     * @param clearanceUntilDate The clearanceUntilDate to set.
     */
    public void setClearanceUntilDate(String clearanceUntilDate) {
        this.clearanceUntilDate = clearanceUntilDate;
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
     * @param enabledOption The enabledOption to set.
     */
    public void setEnabledOption(Integer enabledOption) {
        this.enabledOption = enabledOption;
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
     * @param featuredOption The featuredOption to set.
     */
    public void setFeaturedOption(Integer featuredOption) {
        this.featuredOption = featuredOption;
    }

    /**
     * @param features The features to set.
     */
    public void setFeatures(String features) {
        this.features = features;
    }

    /**
     * @param fromPrice The fromPrice to set.
     */
    public void setFromPrice(Double fromPrice) {
        this.fromPrice = fromPrice;
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
     * @param onClearanceOption The onClearanceOption to set.
     */
    public void setOnClearanceOption(Integer onClearanceOption) {
        this.onClearanceOption = onClearanceOption;
    }

    /**
     * @param onSale The onSale to set.
     */
    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    /**
     * @param onSaleOption The onSaleOption to set.
     */
    public void setOnSaleOption(Integer onSaleOption) {
        this.onSaleOption = onSaleOption;
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
     * @param searchBySalePriceOption The searchBySalePriceOption to set.
     */
    public void setSearchBySalePriceOption(Integer searchBySalePriceOption) {
        this.searchBySalePriceOption = searchBySalePriceOption;
    }

    /**
     * @param sellerCode The sellerCode to set.
     */
    public void setSellerCode(Long sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * @param sessionProduct The sessionProduct to set.
     */
    public void setSessionProduct(Product sessionProduct) {
        this.sessionProduct = sessionProduct;
    }

    /**
     * @param shippingDepth The shippingDepth to set.
     */
    public void setShippingDepth(Float shippingDepth) {
        this.shippingDepth = shippingDepth;
    }

    /**
     * @param shippingHeight The shippingHeight to set.
     */
    public void setShippingHeight(Float shippingHeight) {
        this.shippingHeight = shippingHeight;
    }

    /**
     * @param shippingWeight The shippingWeight to set.
     */
    public void setShippingWeight(Float shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    /**
     * @param shippingWidth The shippingWidth to set.
     */
    public void setShippingWidth(Float shippingWidth) {
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
     * @param taxTypeCode The taxTypeCode to set.
     */
    public void setTaxTypeCode(Long taxTypeCode) {
        this.taxTypeCode = taxTypeCode;
    }

    /**
     * @param toPrice The toPrice to set.
     */
    public void setToPrice(Double toPrice) {
        this.toPrice = toPrice;
    }







}
