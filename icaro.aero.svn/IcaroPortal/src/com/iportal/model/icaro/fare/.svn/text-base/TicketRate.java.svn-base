/**
 * 
 */
package com.iportal.model.icaro.fare;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Tarifas por tipo de pasajero
 * 
 * @author hernan
 * @version 1.0
 * 
 */
@Entity
@Table(name = "tb_ticket_rate")
@javax.persistence.TableGenerator(name = "TABLE_GEN", table = "tb_sequence", pkColumnName = "name", valueColumnName = "next_val", pkColumnValue = "ticket_rate", allocationSize = 20)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TicketRate {

    private Long code;

    private Route route;

    private PaxType paxType;

    private Boolean enabled;

    private Boolean specialFare;

    private Double fare;

    private Double ivaRate;

    private Double tax1;

    private Double tax2;

    private Boolean applyTax3;

    private Double tax3;

    private Double roundFare;

    private Double roundIvaRate;

    private Double roundTax1;

    private Double roundTax2;

    private Double roundTax3;

    private transient Boolean roundTrip;

    public TicketRate() {
	super();
	this.code = null;
	this.fare = null;
	this.enabled = null;
	this.route = null;
	this.paxType = null;
	this.specialFare = null;
	this.ivaRate = null;
	this.applyTax3 = null;
	this.tax1 = null;
	this.tax2 = null;
	this.tax3 = null;
	this.roundFare = null;
	this.roundIvaRate = null;
	this.roundTax1 = null;
	this.roundTax2 = null;
	this.roundTax3 = null;
	this.roundTrip = null;

    }

    /**
     * @return the roundTrip
     */
    @Transient
    public Double getTotalRoundFare() {
	return this.roundFare
		+ (this.roundIvaRate != null ? this.roundIvaRate : 0.0D)
		+ (this.roundTax1 != null ? this.roundTax1 : 0.0D)
		+ (this.roundTax2 != null ? this.roundTax2 : 0.0D)
		+ (this.roundTax3 != null ? this.roundTax3 : 0.0D);
    }

    @Transient
    public Double getTotalFare() {
	return this.fare + (this.ivaRate != null ? this.ivaRate : 0.0D)
		+ (this.tax1 != null ? this.tax1 : 0.0D)
		+ (this.tax2 != null ? this.tax2 : 0.0D)
		+ (this.tax3 != null ? this.tax3 : 0.0D);

    }

    /**
     * @return the roundTrip
     */
    @Transient
    public Boolean getRoundTrip() {
	return roundTrip;
    }

    /**
     * @param roundTrip
     *                the roundTrip to set
     */
    public void setRoundTrip(Boolean roundTrip) {
	this.roundTrip = roundTrip;
    }

    @Column(name = "ticket_rate_apply_tax3")
    public Boolean getApplyTax3() {
	return applyTax3;
    }

    @Id
    @Column(name = "ticket_rate_code")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
    public Long getCode() {
	return code;
    }

    @Column(name = "ticket_rate_enabled")
    public Boolean getEnabled() {
	return enabled;
    }

    @Column(name = "ticket_rate_fare")
    public Double getFare() {
	return fare;
    }

    @ManyToOne
    @JoinColumn(name = "route_code")
    public Route getRoute() {
	return route;
    }

    @Column(name = "ticket_rate_iva_rate")
    public Double getIvaRate() {
	return ivaRate;
    }

    @ManyToOne
    @JoinColumn(name = "pax_type_code")
    public PaxType getPaxType() {
	return paxType;
    }

    @Column(name = "ticket_rate_resident_fare")
    public Boolean getSpecialFare() {
	return specialFare;
    }

    @Column(name = "ticket_rate_tax1")
    public Double getTax1() {
	return tax1;
    }

    @Column(name = "ticket_rate_tax2")
    public Double getTax2() {
	return tax2;
    }

    @Column(name = "ticket_rate_tax3")
    public Double getTax3() {
	return tax3;
    }

    /**
     * @return the roundFare
     */
    @Column(name = "ticket_rate_round_fare")
    public Double getRoundFare() {
	return roundFare;
    }

    /**
     * @return the roundIvaRate
     */
    @Column(name = "ticket_rate_round_iva_rate")
    public Double getRoundIvaRate() {
	return roundIvaRate;
    }

    /**
     * @return the roundTax1
     */
    @Column(name = "ticket_rate_round_tax1")
    public Double getRoundTax1() {
	return roundTax1;
    }

    /**
     * @return the roundTax2
     */
    @Column(name = "ticket_rate_round_tax2")
    public Double getRoundTax2() {
	return roundTax2;
    }

    /**
     * @return the roundTax3
     */
    @Column(name = "ticket_rate_round_tax3")
    public Double getRoundTax3() {
	return roundTax3;
    }

    /**
     * @param roundFare
     *                the roundFare to set
     */
    public void setRoundFare(Double roundFare) {
	this.roundFare = roundFare;
    }

    /**
     * @param roundIvaRate
     *                the roundIvaRate to set
     */
    public void setRoundIvaRate(Double roundIvaRate) {
	this.roundIvaRate = roundIvaRate;
    }

    /**
     * @param roundTax1
     *                the roundTax1 to set
     */
    public void setRoundTax1(Double roundTax1) {
	this.roundTax1 = roundTax1;
    }

    /**
     * @param roundTax2
     *                the roundTax2 to set
     */
    public void setRoundTax2(Double roundTax2) {
	this.roundTax2 = roundTax2;
    }

    /**
     * @param roundTax3
     *                the roundTax3 to set
     */
    public void setRoundTax3(Double roundTax3) {
	this.roundTax3 = roundTax3;
    }

    public void setApplyTax3(Boolean applyTax3) {
	this.applyTax3 = applyTax3;
    }

    public void setCode(Long code) {
	this.code = code;
    }

    public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
    }

    public void setFare(Double fare) {
	this.fare = fare;
    }

    public void setRoute(Route route) {
	this.route = route;
    }

    public void setIvaRate(Double ivaRate) {
	this.ivaRate = ivaRate;
    }

    public void setPaxType(PaxType paxType) {
	this.paxType = paxType;
    }

    public void setSpecialFare(Boolean specialFare) {
	this.specialFare = specialFare;
    }

    public void setTax1(Double tax1) {
	this.tax1 = tax1;
    }

    public void setTax2(Double tax2) {
	this.tax2 = tax2;
    }

    public void setTax3(Double tax3) {
	this.tax3 = tax3;
    }

}
