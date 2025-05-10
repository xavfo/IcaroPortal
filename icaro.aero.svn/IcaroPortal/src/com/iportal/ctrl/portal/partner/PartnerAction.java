/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.partner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.yage.Globals;

public class PartnerAction extends ContentContainerAction {

	private static Log logger = LogFactory.getLog(PartnerAction.class);

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Session sess = null;
		HttpSession session = request.getSession();
		MenuPortalBean portalBean = (MenuPortalBean) session
				.getAttribute(Constants.MENU_KEY);

		try {
			/*sess = this.getHibernateSession(); 
			StringBuffer sql = new StringBuffer();
//			Listado de Sellers Fernando Tamayo 24/05/2007
			sql = new StringBuffer();
	        sql.append(" select new com.iportal.biz.RowItem ( ");
	    	sql.append(" catalogue.code, catalogue.name, catalogue.description) ");
	    	sql.append(" From Catalogue as catalogue ");
	    	sql.append(" Where catalogue.type.code = ? ");
			query = sess.createQuery(sql.toString());
				
			query.setLong(0, 18);
			query.setCacheable(true);
			results = query.list();			
			request.setAttribute("catalogueSellerList", results);
			
			//Listado de Ciudades Fernando Tamayo 24/05/2007
			sql = new StringBuffer();
	        sql.append(" select distinct new com.iportal.biz.RowItem ( ");
	    	sql.append(" seller.city.code, seller.city.name, seller.city.key ) ");
	    	sql.append(" From Seller as seller ");
	    	sql.append(" Where seller.city.isEnabled = ? ");
	    	sql.append(" and seller.promoter = ? ");
	    	sql.append(" and seller.enabled = ? ");
			query = sess.createQuery(sql.toString());
				
			query.setBoolean(0,Globals.TRUE);
			query.setBoolean(1,Globals.TRUE);
			query.setBoolean(2,Globals.TRUE);
			query.setCacheable(true);
			results = query.list();
				
			request.setAttribute("cityList", results);
			
//			 Para el seller del mes Fernando Tamayo 23/05/2007
			sql = new StringBuffer();			
			sql.append("from Seller as seller ");
			sql.append("where seller.month = ? ");
			query = sess.createQuery(sql.toString());
			
			query = sess.createQuery(sql.toString());
			// query.setLong(0, Constants.CATALOGUE_TYPE_INFO);
			query.setBoolean(0, Globals.TRUE.booleanValue());
			results = query.list();
				
			request.setAttribute("sellerMonth", results);		
*/
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
		return mapping.findForward(Globals.FORWARD_SUCCESS+"_"+portalBean.getLayoutForward());
	}

}
