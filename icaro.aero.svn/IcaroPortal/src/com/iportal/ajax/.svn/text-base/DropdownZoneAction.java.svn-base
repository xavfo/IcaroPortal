/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ajax;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.biz.RowItem;

/**
 * Clase que extiende de <code>BaseAjaxAction</code>. Genera contenidos combo dinamicos
 * @author andresg
 * @author  YAGE
 * @version 1.0
 * @see  com.iportal.ajax.BaseAjaxAction
 */
public class DropdownZoneAction extends BaseAjaxAction {
	
	private static Log logger = LogFactory.getLog(DropdownZoneAction.class);

	  /**
	   * Genera el esquema con los datos para el XLM dinamico
	   * @see org.ajaxtags.demo.servlet.BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest,
	   *      javax.servlet.http.HttpServletResponse)
	   */
	  public String getXmlContent(ActionMapping mapping, ActionForm form, HttpServletRequest request,
		      HttpServletResponse response)
	      throws Exception {
		  
		List list = getList((BaseAjaxForm)form);
	    return new AjaxXmlBuilder().addItems(list, "name", "code").toString();
	  }
	  
	  /**
	   * Lista las zonas segun la ciudad. 
	   * Toma el codigo de <code>code</code> del Objeto <code>BaseAjaxForm</code>
	   * @see org.ajaxtags.demo.servlet.BaseAjaxServlet#getXmlContent(javax.servlet.http.HttpServletRequest)
	   */
	@SuppressWarnings("unchecked")
	public List<RowItem> getList(BaseAjaxForm form) {

		Session sess = null;
        List<RowItem> results = null;
        List<RowItem> resultsFinal = null;

        try {
    		sess = this.getHibernateSession();

    		StringBuffer sql = new StringBuffer();

    		sql.append("select new com.iportal.biz.RowItem (zone.code, zone.name) ");
            sql.append("from Zone as zone ");
    		sql.append("where zone.city.code = ? ");
			sql.append("order by zone.name asc");
			
			Query query = sess.createQuery(sql.toString());
			query.setLong(0, form.getCode());

			results = query.list();
	        //Agrega un registro si la consulta no trae registro.
			resultsFinal = new ArrayList<RowItem>();
	        resultsFinal.add(new RowItem());
	        resultsFinal.addAll(1,results);
    		
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		return resultsFinal;
	}
}
