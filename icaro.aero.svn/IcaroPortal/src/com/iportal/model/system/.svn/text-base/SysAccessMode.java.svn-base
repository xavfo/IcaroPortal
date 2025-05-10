package com.iportal.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class persist data to the <code>tb_sys_access_mode</code> 
 * database table.
 * 
 * @author  YAGE (Jorge Lomas)
 * @version 1.1
 *
 */
@Entity
@Table(name="tb_sys_access_mode")
@javax.persistence.TableGenerator(
		name="TABLE_GEN",
		table="tb_sequence",
		pkColumnName = "name",
		valueColumnName = "next_val",
		pkColumnValue="sys_access_mode",
		allocationSize=20
		)
public class SysAccessMode {

	private Long code;
	
	private Boolean enabled;
	
	private String name;

	public SysAccessMode(){}

	public void finalize() throws Throwable {}

	@Id 
    @Column(name="sys_access_mode_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
	public Long getCode(){
		return code;
	}

	@Column(name="sys_access_mode_name", length=50)
	public String getName(){
		return name;
	}

	@Column(name="sys_access_mode_enabled")
	public Boolean isEnabled(){
		return enabled;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(Long code){
		this.code = code;
	}

	/**
	 * 
	 * @param enabled
	 */
	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

}