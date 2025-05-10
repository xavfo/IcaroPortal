/*
 * Copyright (c) Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;

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
import com.iportal.model.Language;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class AccessTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(AccessTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;
        
//        List typeList = null;
        List urlList = null;
        List displayModeList = null;
        
        try {
            sess = getHibernateSession();
           
//          query = sess.createQuery("select new com.iportal.biz.RowItem (type.code, type.name) from AccessType as type order by type.name");
//    		query.setCacheable(true);
//    		typeList = query.list();   

            Language language = (Language)request.getSession().getAttribute(Constants.LANGUAGE_KEY);

            StringBuffer hql = new StringBuffer ();
            hql.append("SELECT new com.iportal.biz.ItemBean(code, key, '"+ language.getLocale().toString() +"') ");
            hql.append("FROM DisplayMode ");
            hql.append("WHERE enabled = ? ");
            hql.append("ORDER BY name ");
            
            query = sess.createQuery(hql.toString()).setBoolean(0, Globals.TRUE);
            query.setCacheable(true);            
            displayModeList = query.list();
            
            query = sess.createQuery("from AccessUrl as url order by url.name");
    		query.setCacheable(true);
    		urlList = query.list();     		
            
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
//		request.setAttribute("typeList", typeList);
		request.setAttribute("urlList", urlList);
		request.setAttribute("displayModeList", displayModeList);
		

		return null;
    }

}
