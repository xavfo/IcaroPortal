/**
 * 
 */
package com.iportal.ctrl.system.entity.flight;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * Datos para registrar informacion de tarifas
 * @author hernan
 * @version 1.0
 *
 */
public class TicketRateForm extends BaseForm {

	private Long routeCode;
	
	private Long paxTypeCode;
	
	private Boolean enabled;
	
	private Boolean specialFare;
	
	private Double fare;
	
	private Double ivaRate;
	
	private Double tax1;
	
	private Double tax2;
	
	private Boolean applyTax3;
	
	private Integer applyTax3Int;
	
	private Double tax3;
	
	private Integer enabledOption;

	/**
	 * 
	 */
	private static final long serialVersionUID = -424458482483430303L;
	
	
	
    public TicketRateForm() {
		super();
		this.clear();
		
	}



	public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();    
    }
	
	private void clear () {
		this.routeCode   = null;
		this.paxTypeCode = null;
		this.specialFare = Globals.FALSE;
		this.enabled = Globals.TRUE;
		this.fare    = null;
		this.ivaRate = null; 
		this.tax1 = null;
		this.tax2 = null;
		this.applyTax3 = Globals.FALSE;
		this.applyTax3Int = 0;
		this.tax3 = null;
		this.enabledOption = null;
		
	}



	public Boolean getApplyTax3() {
		return applyTax3;
	}
	
	



	public Integer getApplyTax3Int() {
		return applyTax3Int;
	}



	public Boolean getEnabled() {
		return enabled;
	}
	
	public Integer getEnabledOption() {
		return enabledOption;
	}




	public Double getFare() {
		return fare;
	}



	public Double getIvaRate() {
		return ivaRate;
	}



	public Long getPaxTypeCode() {
		return paxTypeCode;
	}



	public Long getRouteCode() {
		return routeCode;
	}



	public Boolean getSpecialFare() {
		return specialFare;
	}



	public Double getTax1() {
		return tax1;
	}



	public Double getTax2() {
		return tax2;
	}



	public Double getTax3() {
		return tax3;
	}



	public void setApplyTax3(Boolean applyTax3) {
		this.applyTax3 = applyTax3;
	}
	
	

	public void setApplyTax3Int(Integer applyTax3Int) {
		this.applyTax3Int = applyTax3Int;
		this.setApplyTax3(this.applyTax3Int == 1?Globals.TRUE:Globals.FALSE);
	}

	public void setApplyTax3Int(Boolean applyTax3) {
		this.applyTax3Int = applyTax3?1:0;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}



	public void setFare(Double fare) {
		this.fare = fare;
	}



	public void setIvaRate(Double ivaRate) {
		this.ivaRate = ivaRate;
	}



	public void setPaxTypeCode(Long paxTypeCode) {
		this.paxTypeCode = paxTypeCode;
	}



	public void setRouteCode(Long routeCode) {
		this.routeCode = routeCode;
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
