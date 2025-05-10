/*
 * Created 18/04/2007
 *    BillInfo.java
 */
package com.iportal.cart.model.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * Clase embedible para los datos de la
 * factura de un cliente
 * @author YAGE(hernan)
 * @version 1.0
 *
 */
@Embeddable
public class BillInfo {

    private String name;

    private String identity;

    private String address;

    private String phone;

    /**
     *
     */
    public BillInfo() {
        super();
        this.name = null;
        this.identity = null;
        this.address = null;
        this.phone = null;
    }

    /**
     * @return Returns the address.
     */
    @Column(name="customer_bill_address")
    public String getAddress() {
        return address;
    }

    @Transient
    public String getAddressStr() {
        return address.replaceAll("\n", " ").replaceAll("\r", "");
    }

    /**
     * @return Returns the identity.
     */
    @Column(name="customer_bill_id")
    public String getIdentity() {
        return identity;
    }

    /**
     * @return Returns the name.
     */
    @Column(name="customer_bill_to")
    public String getName() {
        return name;
    }

    /**
     * @return Returns the phone.
     */
    @Column(name="customer_bill_phone")
    public String getPhone() {
        return phone;
    }

    /**
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param identity The identity to set.
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }



}
