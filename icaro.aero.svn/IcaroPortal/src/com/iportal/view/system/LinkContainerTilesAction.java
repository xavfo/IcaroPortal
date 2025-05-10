/*
 * Copyright © Yage. All Rights Reserved.
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
import org.hibernate.Session;

import com.iportal.Constants;
import com.yage.struts.action.BaseTilesAction;

/**
 * comment FaqTilesAction
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class LinkContainerTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(LinkContainerTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        
        
        List linkCategoryList = null;
        List linkTypeList = null;
        
        try {
        	sess = getHibernateSession();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (category.code, category.name)");
            sql.append("from Catalogue as category ");
            sql.append("where category.type.code = ? ");
            sql.append("order by category.name ");            
           
            linkCategoryList = sess.createQuery(sql.toString()).setLong(0, Constants.CATALOGUE_TYPE_LINK.longValue()).list();
            
            sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (type.code, type.name)");
            sql.append("from LinkType as type ");
            sql.append("order by type.name ");            
           
            linkTypeList = sess.createQuery(sql.toString()).list();
            
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
		request.setAttribute("linkCategoryList", linkCategoryList);
		request.setAttribute("linkTypeList", linkTypeList);
		

		return null;
    }

}
