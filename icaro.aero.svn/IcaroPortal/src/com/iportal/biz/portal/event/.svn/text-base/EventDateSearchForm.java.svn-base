/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.biz.portal.event;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

public class EventDateSearchForm extends BaseForm {

	private static final long serialVersionUID = -635403064230964844L;
	
	private Long eventCode;
	
	private Long cityCode;

	private String form;
	
	private String parent;
	
	private String[] properties;
	
	private String[] labels;

	private String property;
	
	private String label;
	
	private Integer index;
	
	@Override
	public void reset(ActionMapping arg0, ServletRequest arg1) {
		super.reset(arg0, arg1);
		eventCode = null;
		cityCode = null;
		form = null;
		parent = null;
		properties = null;
		labels = null;
		index = null;
	}

	public Long getCityCode() {
		return cityCode;
	}

	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}

	public Long getEventCode() {
		return eventCode;
	}

	public void setEventCode(Long eventCode) {
		this.eventCode = eventCode;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
		if (labels != null && labels.length>0)
			this.label = labels[0];
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String[] getProperties() {
		return properties;
	}

	public void setProperties(String[] properties) {
		this.properties = properties;
		if (properties != null && properties.length>0)
			this.property = properties[0];
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}

