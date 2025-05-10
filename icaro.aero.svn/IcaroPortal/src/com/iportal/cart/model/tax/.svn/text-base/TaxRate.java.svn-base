/*
 * Created 21/03/2007
 *	Tax.java
 */
package com.iportal.cart.model.tax;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * Impuestos y valores correspondientes a los mismos 
 * de acuerdo con las fechas de vigencia desde(since) hasta (until) 
 * @author hernan
 * @version 1.0
 */
@Entity
@Table(name="tb_tax_rate")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="tax_rate",
								allocationSize=1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TaxRate {

    private static Log logger = LogFactory.getLog(TaxRate.class);

    private Long code;
	
	private Double rate;
	
	private Calendar since;
	
	private Calendar until;
	
	private Tax tax;

	/**
	 * 
	 */
	public TaxRate() {
		super();
		this.code = null;
		this.rate = null;
		this.since = null;
		this.until = null;
		this.tax = null;
	}

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="tax_rate_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the rate.
	 */
    @Column(name="tax_rate_value")
	public Double getRate() {
		return rate;
	}

	/**
	 * @return Returns the since.
	 */ 
    @Column(name="tax_rate_since")
	public Calendar getSince() {
		return since;
	}
    
    
    /**
     * @return Returns the until.
     */ 
    @Column(name="tax_rate_until")
    public Calendar getUntil() {
        return until;
    }
    
    

	/**
	 * @return Returns the taxes.
	 */
	@ManyToOne
    @JoinColumn(name="tax_code")
	public Tax getTax() {
		return tax;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param rate The rate to set.
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}

	/**
	 * @param since The since to set.
	 */
	public void setSince(Calendar since) {
		this.since = since;
	}

	/**
	 * @param until The until to set.
	 */
	public void setUntil(Calendar until) {
		this.until = until;
	}

    
    /**
     * @param taxes The taxes to set.
     */
    
    public void setTax(Tax tax) {
        this.tax = tax;
    }

	
	/**
	 * @see java.lang.Object#clone()
	 */
	public TaxRate clone() {
	    TaxRate out = new TaxRate();
        out.setCode(this.code);
        out.setRate(this.rate);
        out.setSince(this.since);
        out.setUntil(this.until);
        out.setTax(this.tax);
        return out;
    }

    @Transient
    public static Double getCurrentRate(Long code2) {
        Session sess = null;
        Double taxRate = null;
        try {
            sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
            taxRate = getCurrentRate (code2, sess);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (sess != null) {
                try {
                    sess.close();
                } catch (Exception e) { }                
            }
        }
        return taxRate;
    }
    
    @Transient
    public static Double getCurrentRate(Long code2, Session sess) {
        TaxRate taxRate = null;
        taxRate = (TaxRate) sess.createQuery("FROM TaxRate WHERE tax.code = ? AND since <= CURRENT_DATE() AND until > CURRENT_DATE()").setLong(0, code2).uniqueResult();
        return (null == taxRate) ? null : taxRate.getRate();
    }
}
