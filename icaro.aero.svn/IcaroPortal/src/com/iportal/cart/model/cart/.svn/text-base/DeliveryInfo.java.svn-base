/*
 * Created 05/02/2007
 *	DeliveryInfo.java
 */
package com.iportal.cart.model.cart;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Componente con informacion de costo 
 * fijo por envio de carga, costo por Peso sobre el 
 * peso mínimo e información de tiempos de entrega.
 * 
 * @author hernan
 * @version 1.0
 */
@Embeddable
public class DeliveryInfo {

    private Integer daysToDeliver;
    
    private Float  minShipCost;
    
    private Float weightCost;

    
	/**
	 * 
	 */
	public DeliveryInfo() {
		super();
	    this.daysToDeliver = null;
	    this.minShipCost   = null;
	    this.weightCost    = null;
	}

	/**
	 * @return Returns the daysToDeliver.
	 */
    @Column(name="city_time_delay")
	public Integer getDaysToDeliver() {
		return daysToDeliver;
	}

	/**
	 * @return Returns the minShipCost.
	 */
    @Column(name="city_min_cost")
	public Float getMinShipCost() {
		return minShipCost;
	}

	/**
	 * @return Returns the weightCost.
	 */
    @Column(name="city_weight_cost")
	public Float getWeightCost() {
		return weightCost;
	}

	/**
	 * @param daysToDeliver The daysToDeliver to set.
	 */
	public void setDaysToDeliver(Integer daysToDeliver) {
		this.daysToDeliver = daysToDeliver;
	}

	/**
	 * @param minShipCost The minShipCost to set.
	 */
	public void setMinShipCost(Float minShipCost) {
		this.minShipCost = minShipCost;
	}

	/**
	 * @param weightCost The weightCost to set.
	 */
	public void setWeightCost(Float weightCost) {
		this.weightCost = weightCost;
	}
	
	

}
