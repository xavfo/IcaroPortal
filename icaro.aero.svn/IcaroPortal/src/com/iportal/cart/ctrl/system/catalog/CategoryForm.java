/*
 * Created Jan 15, 2007
 *    CategoryForm.java
 */
package com.iportal.cart.ctrl.system.catalog;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * @author hernan
 *
 */
public class CategoryForm extends BaseForm {

    private static final long serialVersionUID = 6709535255588574132L;

    private String name;

    private Long parentCode;

    private Boolean enabled;

    private Boolean group;

    private Boolean featured;

    private Integer enabledOption;

    private Integer goupOption;

    private Integer level;

    private Integer orderIndex;

    private String image;

    private String description;


    /**
     *
     */
    public CategoryForm() {
        super();
        this.clear();
    }

    public void clear () {
        this.name    = null;
        this.enabled = Globals.FALSE;
        this.group   = Globals.FALSE;
        this.featured = Globals.FALSE;
        this.level   = null;
        this.image   = null;
        this.goupOption = null;
        this.parentCode = null;
        this.orderIndex = null;
        this.enabledOption = null;
        this.description   = null;

    }

    /* (non-Javadoc)
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();
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
     * @return Returns the featured.
     */
    public Boolean getFeatured() {
        return featured;
    }

    /**
     * @return Returns the goupOption.
     */
    public Integer getGoupOption() {
        return goupOption;
    }

    /**
     * @return Returns the group.
     */
    public Boolean getGroup() {
        return group;
    }

    /**
     * @return Returns the image.
     */
    public String getImage() {
        return image;
    }

    /**
     * @return Returns the level.
     */
    public Integer getLevel() {
        return level;
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
     * @param featured The featured to set.
     */
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    /**
     * @param goupOption The goupOption to set.
     */
    public void setGoupOption(Integer goupOption) {
        this.goupOption = goupOption;
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
     * @param parentCode The parentCode to set.
     */
    public void setParentCode(Long parentCode) {
        this.parentCode = parentCode;
    }










}
