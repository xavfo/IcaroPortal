package com.iportal.biz;

import java.util.List;

import org.apache.struts.action.ActionForm;

public interface IPersistent {
	
	/**
	 * Return all records
	 */	
	public List<?> getAll();
	
	/**
	 * Return one page of records
	 */	
	public List<?> getPage(Integer rows, Integer page);
	
	/**
	 * @return Persistent Object by Id
	 */	
	public Object getByCode(Long code);
	
	/**
	 * Borra el registro dado de la base de datos.
	 * @return true
	 */	
	public boolean Delete(Long code);

	/**
	 * Realiza las operaciones de Save o Update.  Retorna true en caso de éxito.
	 * @return true
	 */
	public boolean Save(ActionForm object);
}
