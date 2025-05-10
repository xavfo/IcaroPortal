/*
 * Created 22/01/2007
 *	OrderForm.java
 */
package com.iportal.cart.ctrl.system.cart;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;
import com.yage.commons.NumberFormatUtils;
import com.yage.struts.action.BaseForm;


/**
 * @author hernan
 * 
 */
public class TaxForm extends BaseForm {

    private static final long serialVersionUID = 3075445008739064416L;

    public static final Calendar MAX_DATE = new GregorianCalendar(3000,0,1,0,0,0);

    private Long taxCode;
    
    private String taxName;

    private Double rate;

    private Calendar since;

    private Calendar until;

    {
        // Stupid Calendar nececita de estar initialisado
        MAX_DATE.getTime();
//        MAX_DATE.set(3000, 0, 1, 0, 0, 0);
//        MAX_DATE.get(Calendar.YEAR);
    }
    

    public void clear() {
        this.taxCode = null;
        this.taxName = null;
        this.rate = new Double(0.0);
        this.since = new GregorianCalendar();
        this.until = MAX_DATE;

        this.listItems = Globals.TRUE;
    }


    /**
     * 
     */
    public TaxForm() {
        super();
        this.clear();
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();
    }


    public String getSinceStr() {
        if (null != this.since) {
            return DateFormatUtils.format(this.since.getTime(), Constants.DATE_FORMAT);
        }
        return "";
    }

    public void setSinceStr(String _since) {
        if (null != _since && 0 < _since.length()) {
            Date since = DateFormatUtils.parseToDate(_since, Constants.DATE_FORMAT);
            this.since.setTime(since);
        } else {
            this.since = null;
        }
    }

    public String getUntilStr() {
        if (null != this.until) {
            return DateFormatUtils.format(this.until.getTime(), Constants.DATE_FORMAT);
        }
        return "";
    }


    public void setUntilStr(String _until) {
//        if (null != _until && 0 < _until.length()) {
//            Date until = DateFormatUtils.parseToDate(_until, Constants.DATE_FORMAT);
//            if(until.getTime() != this.until.getTime().getTime()) {
//                this.until.setTime(until);
//                this.dateChanged = Globals.TRUE;
//            }
//            this.until.setTime(DateFormatUtils.parseToDate(_until, Constants.DATE_FORMAT));
//        } else {
//            this.until = null;
//        }
    }
    
    
    public void setRateStr(String _rate) {
        if(null != _rate && 0 < _rate.length()) {
            _rate = _rate.trim();
            if(_rate.endsWith("%")) {
                _rate = _rate.substring(0,_rate.length()-1);
            }
            Double rate = Double.parseDouble(_rate) / 100.0;
            this.rate = rate;
        }
    }

    public String getRateStr() {
        return new NumberFormatUtils().formatPercent(this.rate);
    }
        
    /* A partir de aqui los getters y setter generados autom�ticamente. */

    public Long getTaxCode() {
        return taxCode;
    }


    public void setTaxCode(Long taxCode) {
        this.taxCode = taxCode;
    }


    public String getTaxName() {
        return this.taxName;
    }


    public void setTaxName(String taxName) {
        this.taxName = taxName;
    }


    public Double getRate() {
        return this.rate;
    }
    
    
    public void setRate(Double rate) {
        this.rate = rate;
    }


    public Calendar getSince() {
        return since;
    }
    

    public void setSince(Calendar since) {
        this.since = since;
    }

    
    public Calendar getUntil() {
        return until;
    }


    public void setUntil(Calendar until) {
        this.until = until;
    }


}
