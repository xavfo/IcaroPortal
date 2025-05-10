/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author  YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class ContactTilesAction extends BaseTilesAction {

    
    private static Log logger = LogFactory.getLog(ContactTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;
        
        List cityList = null;
        List catalogueList=null;
        
        try {
            sess = getHibernateSession();
            
            query = sess.createQuery("select new com.iportal.biz.RowItem (country.code, country.name) from Country as country order by country.name");
     		query.setCacheable(true);
     		cityList = query.list();
     		
     		StringBuffer sql = new StringBuffer();
     		sql.append("select new com.iportal.biz.RowItem (catalogue.code, catalogue.name, ");
     		sql.append("catalogue.email )");
     		sql.append("from Catalogue as catalogue ");
     		sql.append("where catalogue.enabled = ? ");
     		sql.append("and catalogue.type.code = ? ");
     

     		query = sess.createQuery(sql.toString());
     		query.setBoolean(0, Globals.TRUE.booleanValue());
     		query.setLong(1, Constants.CATALOGUE_TYPE_REQUEST_REASON.longValue()); 
     		
     		catalogueList = query.list();
            
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		//Save the Lists in request scope
		request.setAttribute("countryList", cityList);
		request.setAttribute("catalogueList", catalogueList);
		

		return null;
    }


}
