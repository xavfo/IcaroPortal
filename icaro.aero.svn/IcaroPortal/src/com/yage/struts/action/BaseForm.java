/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.yage.Globals;
import com.yage.commons.ImageString;

/**
 *
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class BaseForm extends ValidatorForm {

    private static final long serialVersionUID = 6530442992322716067L;

    private ImageString action;

    private String id;

    private Long code;

    private String orderField;

    private Boolean orderAsc;

    private Long[] codes;

    private String button;


    private Integer pageSize;

    private Integer pageNumber;

    private Integer totalPages;

    protected Boolean listItems;

    protected Boolean reset;

    protected Long itemCode;

    private String text;

    /**
     *  Cra una nueva instancia de BaseForm
     */
    public BaseForm() {
        super();
    }


    /**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping arg0, HttpServletRequest arg1) {
        super.reset(arg0, arg1);
        action = new ImageString();
        id = null;
        code = null;
        listItems = Globals.FALSE;
        reset = Globals.FALSE;
        orderField = null;
        orderAsc = Globals.TRUE;
        codes = new Long[0];
        button = null;
        pageSize = Globals.DEFAULT_PAGE_SIZE;
        pageNumber = Globals.DEFAULT_PAGE_NUMBER;
        text = null;
    }


    public Object getAction() {
        return action;
    }


    public String getButton() {
        return button;
    }


    public Long getCode() {
        return code;
    }


    public Long[] getCodes() {
        return codes;
    }


    public String getId() {
        return id;
    }

    /**
     * @return Returns the listItems property that indicates if the JSP page must return the list
     * of all the objects that belong to the current action.  Its default value is 0.
     */
    public Boolean getListItems() {
        return listItems;
    }


    public Boolean getOrderAsc() {
        return orderAsc;
    }


    public String getOrderField() {
        return orderField;
    }


    public Integer getPageNumber() {
        return pageNumber;
    }


    public Integer getPageSize() {
        return pageSize;
    }


    public Integer getTotalPages() {
        return totalPages;
    }


    public void setAction(Object action) {
        if (action instanceof ImageString) {
            this.action.setAction(((ImageString)action).getAction());
        } if (action instanceof String) {
            this.action.setAction((String)action);
        }
    }


    public void setAction(String action) {
        this.action.setAction(action);
    }


    public void setButton(String button) {
        this.button = button;
    }


    public void setCode(Long code) {
        this.code = code;
    }


    public void setCodes(Long[] codes) {
        this.codes = codes;
    }


    public void setId(String id) {
        this.id = id;
    }


    public void setListItems(Boolean listItems) {
        this.listItems = listItems;
    }


    public void setOrderAsc(Boolean orderAsc) {
        this.orderAsc = orderAsc;
    }


    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }


    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }


    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }


    /**
     * @return Returns the redirect.
     */
    public Boolean getReset() {
        return reset;
    }


    /**
     * @param redirect The redirect to set.
     */
    public void setReset(Boolean redirect) {
        this.reset = redirect;
    }


    /**
     * @return Returns the itemCode.
     */
    public Long getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode The itemCode to set.
     */
    public void setItemCode(Long itemCode) {
        this.itemCode = itemCode;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }
}
