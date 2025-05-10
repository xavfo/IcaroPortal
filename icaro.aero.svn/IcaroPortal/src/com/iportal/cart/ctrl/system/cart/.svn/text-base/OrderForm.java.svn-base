/*
 * Created 22/01/2007
 *    OrderForm.java
 */
package com.iportal.cart.ctrl.system.cart;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.commons.DateFormatUtils;
import com.yage.struts.action.BaseForm;

/**
 * @author hernan
 *
 */
public class OrderForm extends BaseForm {

    private static final long serialVersionUID = -1911314018946652901L;

    private Long statusCode;

    private Long customerCode;

    private Long customerType;

    private String customerReference;

    private String customerIdentity;

    private String customerName;

    private Long paymentTypeCode;

    private Long paymentStatusCode;

    private Long sellerCode;

    private String fromDate;

    private String toDate;

    private Integer enabledOption;

    private String recordLocator;


    public void clear () {
        this.statusCode   = null;
        this.customerCode = null;
        this.customerName = null;
        this.customerType=null;
        this.customerReference=null;
        this.sellerCode   = null;
        this.fromDate     = null;
        this.toDate       = null;

        this.enabledOption     = null;
        this.paymentTypeCode   = null;
        this.paymentStatusCode = null;
        this.customerCode=null;

        /*Calendar gc = GregorianCalendar.getInstance();
        setTo(gc);
        gc.set(GregorianCalendar.DAY_OF_YEAR, 1);
        setFrom(gc);*/
    }


    /**
     *
     */
    public OrderForm() {
        super();
        this.clear();
    }

    /* (non-Javadoc)
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();
    }


    public Calendar getFrom() {

        Calendar calendar = new GregorianCalendar();

        if ( this.getFromDate() != null && this.getFromDate().length() > 0  ) {

            calendar.setTime( DateFormatUtils.parseToDate(this.getFromDate(), Constants.DATE_FORMAT) );
            return calendar;
        }
        return null;


    }

    public void setFrom(Calendar calendar) {
        if ( calendar != null )

            this.fromDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
    }

    public Calendar getTo() {
        Calendar calendar = new GregorianCalendar();

        if ( this.getToDate() != null && this.getToDate().length() > 0  ) {
            calendar.setTime( DateFormatUtils.parseToDate(this.getToDate(), Constants.DATE_FORMAT) );
            calendar.set(GregorianCalendar.HOUR_OF_DAY, 23);
            calendar.set(GregorianCalendar.MINUTE, 59);
            calendar.set(GregorianCalendar.SECOND, 59);
            calendar.set(GregorianCalendar.MILLISECOND, 999);
            return calendar;
        }
        return null;

    }

    public void setTo(Calendar calendar) {
        if ( calendar != null )
            this.toDate = DateFormatUtils.format(calendar.getTime(), Constants.DATE_FORMAT);
    }
    /*    A partir de aqui los getters y setter generados autom�ticamente.*/


    /**
     * @return Returns the customerCode.
     */
    public Long getCustomerCode() {
        return customerCode;
    }


    /**
     * @return Returns the customerName.
     */
    public String getCustomerName() {
        return customerName;
    }


    /**
     * @return Returns the enabledOption.
     */
    public Integer getEnabledOption() {
        return enabledOption;
    }


    /**
     * @return Returns the fromDate.
     */
    public String getFromDate() {
        return fromDate;
    }


    /**
     * @return Returns the paymentStatusCode.
     */
    public Long getPaymentStatusCode() {
        return paymentStatusCode;
    }


    /**
     * @return Returns the paymentTypeCode.
     */
    public Long getPaymentTypeCode() {
        return paymentTypeCode;
    }


    /**
     * @return Returns the statusCode.
     */
    public Long getStatusCode() {
        return statusCode;
    }


    /**
     * @return Returns the toDate.
     */
    public String getToDate() {
        return toDate;
    }


    /**
     * @param customerCode The customerCode to set.
     */
    public void setCustomerCode(Long customerCode) {
        this.customerCode = customerCode;
    }


    /**
     * @param enabledOption The enabledOption to set.
     */
    public void setEnabledOption(Integer enabledOption) {
        this.enabledOption = enabledOption;
    }


    /**
     * @param fromDate The fromDate to set.
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }


    /**
     * @param paymentStatusCode The paymentStatusCode to set.
     */
    public void setPaymentStatusCode(Long paymentStatusCode) {
        this.paymentStatusCode = paymentStatusCode;
    }


    /**
     * @param paymentTypeCode The paymentTypeCode to set.
     */
    public void setPaymentTypeCode(Long paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }


    /**
     * @param statusCode The statusCode to set.
     */
    public void setStatusCode(Long statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * @param toDate The toDate to set.
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }


    /**
     * @return the sellerCode
     */
    public Long getSellerCode() {
        return sellerCode;
    }


    /**
     * @param sellerCode the sellerCode to set
     */
    public void setSellerCode(Long sellerCode) {
        this.sellerCode = sellerCode;
    }


    /**
     * @return the customerIdentity
     */
    public String getCustomerIdentity() {
        return customerIdentity;
    }


    /**
     * @param customerIdentity the customerIdentity to set
     */
    public void setCustomerIdentity(String customerIdentity) {
        this.customerIdentity = customerIdentity;
    }


    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    /**
     * @return Returns the recordLocator.
     */
    public String getRecordLocator() {
        return recordLocator;
    }


    /**
     * @param recordLocator The recordLocator to set.
     */
    public void setRecordLocator(String recordLocator) {
        this.recordLocator = recordLocator;
    }


    /**
     * @return Returns the customerType.
     */
    public Long getCustomerType() {
        return customerType;
    }


    /**
     * @param customerType The customerType to set.
     */
    public void setCustomerType(Long customerType) {
        this.customerType = customerType;
    }


    /**
     * @return Returns the customerReference.
     */
    public String getCustomerReference() {
        return customerReference;
    }


    /**
     * @param customerReference The customerReference to set.
     */
    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }


}
