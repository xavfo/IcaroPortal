/*
 * Created 21/03/2007
 *    TaxType.java
 */
package com.iportal.cart.model.tax;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Tipos de Impuestos aplicables a un producto.
 *
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_tax_type")
@javax.persistence.TableGenerator(
                                name="TABLE_GEN",
                                table="tb_sequence",
                                pkColumnName = "name",
                                valueColumnName = "next_val",
                                pkColumnValue="tax_type",
                                allocationSize=1
                                )
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TaxType {

    private Long code;

    private String name;

    private Set<Tax> taxes;


    /**
     *
     */
    public TaxType() {
        super();
        this.code  = null;
        this.name  = null;
        this.taxes = null;
    }

    /**
     * @return Returns the code.
     */
    @Id
    @Column(name="tax_type_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
    public Long getCode() {
        return code;
    }

    /**
     * @return Returns the name.
     */
    @Column(name="tax_type_name")
    public String getName() {
        return name;
    }


    /**
     * @return Returns the taxes.
     */
    @ManyToMany
    @JoinTable(
                name="tb_tax_applied",
                joinColumns = {@JoinColumn(name="TAX_TYPE_CODE")},
                inverseJoinColumns = @JoinColumn(name="TAX_CODE")
             )
    // TODO Chequear si el WHERE solo seleciona los impuestos vigentes
    //@Where(clause="tax_since <= NOW() AND tax_until > NOW()")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    public Set<Tax> getTaxes() {
        return taxes;
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
     * @param taxes The taxes to set.
     */
    public void setTaxes(Set<Tax> taxes) {
        this.taxes = taxes;
    }


}
