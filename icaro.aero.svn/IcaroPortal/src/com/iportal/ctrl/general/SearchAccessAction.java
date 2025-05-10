/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.general;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.NullableType;

import com.yage.Globals;
import com.yage.struts.action.BaseAction;

/**
 * @author mcnovillo
 * 
 */
public class SearchAccessAction extends BaseAction {
	
	private static Log logger = LogFactory.getLog(SearchAccessAction.class);
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Session sess = null;
		List results = null;

		SearchAccessForm searchForm = (SearchAccessForm) form;


		try {
			StringBuffer sql = new StringBuffer();
			ArrayList<Object> params = new ArrayList<Object>();
    		ArrayList<NullableType> types = new ArrayList<NullableType>();

			if ( "search".equals( searchForm.getAction().toString() ) ){
				sql.append("from Access as access ");
				sql.append("where 1 = 1 ");
				
				/*if (searchForm.getRelatedName() != null 
					&& searchForm.getRelatedCode().longValue() > 0) {
					sql.append(" and banner.state.country.code = ? ");
					params.add(searchForm.getCountryCode());
					types.add(Hibernate.LONG);
				}
				
				if (searchForm.getRelatedCode() != null 
					&& searchForm.getRelatedCode().longValue() > 0) {
					sql.append(" and banner.state.code = ? ");
					params.add(searchForm.getRelatedCode());
					types.add(Hibernate.LONG);
				}*/

				if (searchForm.getName() != null && searchForm.getName().length() > 0) {
					StringBuffer name = new StringBuffer();
					name.append(searchForm.getName().toUpperCase());
					name.append("%");
					sql.append("and upper(access.name) like ? ");
					params.add( name.toString() );
					types.add(Hibernate.STRING);
				} 

	     		if ( searchForm.getOrderField() != null ) {
	         		sql.append("order by access.");
	         		sql.append(searchForm.getOrderField());
	         		if ( searchForm.getOrderAsc().booleanValue() ) {
	         		    sql.append(" asc ");
	         		} else {
	         		    sql.append(" desc ");
	         		}
	     		}
				
				sess = getHibernateSession();
	     		Query query = sess.createQuery(sql.toString());
				
				for (int i = 0; i < types.size(); i++) {
					query.setParameter(i, params.get(i), types.get(i));
				}
				
				query.setCacheable(true);
				results = query.list ();
			}

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

		// Save the List of Categories in the request context	
		request.setAttribute("accessList", results);

		
		//this.removeForm(mapping, request);
		
		return mapping.findForward(Globals.FORWARD_SUCCESS);
	}
}
