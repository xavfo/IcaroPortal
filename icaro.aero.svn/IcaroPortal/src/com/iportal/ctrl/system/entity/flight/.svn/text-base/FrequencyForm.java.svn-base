/**
 * 
 */
package com.iportal.ctrl.system.entity.flight;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * Forma para datos de frecuencias
 * 
 * @author hernan
 * @version 1.0
 *
 */
public class FrequencyForm extends BaseForm {

	
	private String name;
	
    private Boolean enabled;
    
    private String  notes;
    
    private Long routeCode;
    
    private Integer enabledOption;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7457276027950469673L;

	public FrequencyForm() {
		super();
		this.clear();
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.clear();    
    }

	
	private void clear () {
		this.routeCode = null;
		this.name      = null;
		this.notes     = null;
		this.enabled   = null;
		this.enabledOption = null;
		
	}

	public Boolean getEnabled() {
		return enabled;
	}
	
	

	public Integer getEnabledOption() {
		return enabledOption;
	}

	public String getName() {
		return name;
	}

	public String getNotes() {
		return notes;
	}

	public Long getRouteCode() {
		return routeCode;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	

	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setRouteCode(Long routeCode) {
		this.routeCode = routeCode;
	}
	
	


}
