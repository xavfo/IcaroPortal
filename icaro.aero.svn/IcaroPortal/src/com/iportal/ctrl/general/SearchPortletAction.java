/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
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
 * AlegroAdmin 2005
 */
public class SearchPortletAction extends BaseAction {
	
	private static Log logger = LogFactory.getLog(SearchPortletAction.class);
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Session sess = null;
		List results = null;

		SearchPortletForm searchForm = (SearchPortletForm) form;

		try {
			StringBuffer sql = new StringBuffer();
			ArrayList<Object> params = new ArrayList<Object>();
    		ArrayList<NullableType> types = new ArrayList<NullableType>();

			if ( "search".equals( searchForm.getAction() ) ){
                sql.append("from LinkContainer as linkContainer ");
                sql.append("where 1 = 1 ");
    
                if (searchForm.getName() != null && searchForm.getName().length() > 0) {
                    StringBuffer name = new StringBuffer();
                    name.append("%");
                    name.append(searchForm.getName().toUpperCase());
                    name.append("%");
                    sql.append("and upper(linkContainer.title) like ? ");
                    params.add( name.toString() );
                    types.add(Hibernate.STRING);
                } 
            
                sql.append("and linkContainer.isEnabled = ? ");
                params.add(Globals.TRUE);
                types.add(Hibernate.BOOLEAN);
                
                sql.append("and linkContainer.type.code = ? ");
                params.add(Globals.TYPE_PORTLET);
                types.add(Hibernate.LONG);
                
                sql.append("order by linkContainer.title ");
                sql.append(" asc ");
				
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
		request.setAttribute("portletList", results);
		return mapping.findForward(Globals.FORWARD_SUCCESS);
	}
}
