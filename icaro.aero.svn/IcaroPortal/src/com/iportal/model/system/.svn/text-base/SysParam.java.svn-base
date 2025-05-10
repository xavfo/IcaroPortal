package com.iportal.model.system;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="tb_sys_params")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="sys_params",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class SysParam {

	private Long code;
	
	private String name;
	
	private String value;
	
	private String type;
	
	static private Set<SysParam> parameter;
	
	
	public SysParam() {
		super();
		this.code = null;
		this.name = null;
		this.value = null;
		this.type = null;
	}

    @Id 
    @Column(name="sys_params_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

    @Column(name="sys_params_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Column(name="sys_params_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    @Column(name="sys_params_value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@OneToMany(mappedBy="parameter")
	public static Set<SysParam> getDocuments() {
		return parameter;
	}
}
