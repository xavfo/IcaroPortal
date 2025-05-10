/*
 * Created 21/03/2007
 *	Tax.java
 */
package com.iportal.cart.model.tax;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Impuestos y valores correspondientes a los mismos 
 * de acuerdo con las fechas de vigencia desde(since) hasta (until) 
 * @author hernan
 * @version 1.0
 */
@Entity
@Table(name="tb_tax")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="tax",
								allocationSize=1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tax {

	private Long code;
	
	private String name;

    private Set<TaxRate> taxRates;
	
	private Set<TaxType> taxTypes;

	/**
	 * 
	 */
	public Tax() {
		super();
		this.code = null;
		this.name = null;
        this.taxRates = null;
		this.taxTypes = null;
	}

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="tax_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the name.
	 */
    @Column(name="tax_name")
	public String getName() {
		return name;
	}
    

    /**
     * @return Returns the taxRates.
     */
    @OneToMany ( mappedBy="tax")
    public Set<TaxRate> getTaxRates() {
        return taxRates;
    }

	/**
	 * @return Returns the taxTypes.
	 */
	@ManyToMany ( mappedBy="taxes")
	public Set<TaxType> getTaxTypes() {
		return taxTypes;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * @param taxRates The taxRates to set.
     */
    
    public void setTaxRates(Set<TaxRate> taxRates) {
        this.taxRates = taxRates;
    }

	/**
	 * @param taxTypes The taxTypes to set.
	 */
	
	public void setTaxTypes(Set<TaxType> taxTypes) {
		this.taxTypes = taxTypes;
	}
    
    @Transient
    public Double getCurrentRate() {
        return TaxRate.getCurrentRate(this.code);
    }

    @Transient
    public Double getCurrentRate(Session sess) {
        return TaxRate.getCurrentRate(this.code, sess);
    }

    public Tax clone() {
	    Tax out = new Tax();
        out.setCode(this.code);
        out.setName(this.name);
        out.setTaxRates(this.taxRates);
        out.setTaxTypes(this.taxTypes);
        return out;
    }

}
