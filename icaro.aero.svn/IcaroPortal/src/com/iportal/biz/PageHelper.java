/*
 * Created on May 5, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.biz;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto light que recupera solo algunas propiedades de otro objeto.
 * @author jorge
 * @version 1.1
 *
 */
public class PageHelper {

		
	/**
	 * 
	 */
	public PageHelper() {
		super();
	}
	
	public static Integer getTotalPages(Long totalRecords, Integer pageSize) 
	throws Exception{
		int totalRows = totalRecords.intValue(); 
		int totalPages = (totalRows / pageSize.intValue());
		if ((totalRows % pageSize.intValue()) > 0 || totalPages == 0)
			totalPages++;
		
		return new Integer(totalPages);
	}
	
	/**
	 * TODO hacer que filtre el numero de paginas a mostrar
	 * 
	 * @param totalPages
	 * @return
	 * @throws Exception
	 */
	public static List<Integer> getPages(Integer totalPages) 
	throws Exception{
		List<Integer> pages = new ArrayList<Integer>();
		
		if ( totalPages.intValue() > 1 ) 
			for (int i = 1; i < totalPages.intValue()+1; i++) {
				pages.add( new Integer(i) );
			}
		
		return pages;
	}
}
