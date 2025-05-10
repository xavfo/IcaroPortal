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

import org.apache.commons.beanutils.PropertyUtils;
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
public class GeneralSearchAction extends BaseAction {
	
	private static Log logger = LogFactory.getLog(GeneralSearchAction.class);
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Session sess = null;
		List results = null;

		GeneralSearchForm searchForm = (GeneralSearchForm) form;


		try {
			StringBuffer sql = new StringBuffer();
			ArrayList<Object> params = new ArrayList<Object>();
    		ArrayList<NullableType> types = new ArrayList<NullableType>();
    		
    		
    		
    		
    		/*if ( !searchForm.getOption().equals(Constants.BANNER_OPTION_PRODUCT) ) {
    			sql.append("select new com.iportal.biz.RowItem (obj.code, obj.title) ");
    		} else {
				sql.append("select new com.iportal.biz.RowItem (obj.code, obj.name) ");
			}*/
    		
			if ( "search".equals( searchForm.getAction().toString() ) ){		
		
	    		Class clazz = Class.forName(searchForm.getClassName());
//	    		PropertyUtils.getPropertyDescriptor(clazz.newInstance(), "name");
	    		//Si tiene la propiedad nombre (name) la clase en la que se va a buscar (content o product)
	    		String propertyName = (PropertyUtils.getPropertyDescriptor(clazz.newInstance(), "name") != null)?"name":"title"; 

				sql.append("select new com.iportal.biz.RowItem (obj.code, obj.");
				sql.append(propertyName);
				sql.append(") ");
				
				sql.append(" from  ");
				sql.append(clazz.getName());
				sql.append(" as obj ");
				/*switch (searchForm.getOption().intValue()){
				
					default: case 2: //Constants.BANNER_OPTION_CONTENT
						sql.append("from Content as obj ");
						break;
					case 3: //Constants.BANNER_OPTION_PRODUCT
						sql.append("from Product as obj ");						
						break;
				
				}*/
				
				sql.append("where 1 = 1 ");
				
				if (searchForm.getName() != null && searchForm.getName().length() > 0) {
					StringBuffer title = new StringBuffer();
					title.append(searchForm.getName().toUpperCase());
					title.append("%");
					sql.append("and upper(obj.");
					sql.append(propertyName);
					sql.append(") like ? ");
					/*if ( !searchForm.getOption().equals(Constants.BANNER_OPTION_PRODUCT) )
						sql.append("and upper(obj.title) like ? ");
					else
						sql.append("and upper(obj.name) like ? ");*/
					params.add( title.toString() );
					types.add(Hibernate.STRING);
				} 
				sql.append("order by obj.");
				sql.append(propertyName);
				/*if ( !searchForm.getOption().equals(Constants.BANNER_OPTION_PRODUCT) )
					sql.append("order by obj.title ");
				else
					sql.append("order by obj.name ");*/
				
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
		request.setAttribute("resultList", results);

		
		//this.removeForm(mapping, request);
		
		return mapping.findForward(Globals.FORWARD_LIST);
	}
}
