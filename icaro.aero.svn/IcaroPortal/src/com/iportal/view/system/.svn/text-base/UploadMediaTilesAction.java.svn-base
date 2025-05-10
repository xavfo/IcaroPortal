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

import com.yage.struts.action.BaseTilesAction;

/**
 * java comment ContactTilesAction
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class UploadMediaTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(UploadMediaTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        List imageCategoryList = null;
//        List bannerCategoryList = null;
        
        try {
        	sess = getHibernateSession();
            
            StringBuffer sql = new StringBuffer();
            sql.append("Select new com.iportal.biz.RowItem (category.code, category.name) " );
            sql.append("From ImageCategory as category ");
            sql.append("Order by category.name ");
            
            imageCategoryList = sess.createQuery(sql.toString()).list();
            
            /*sql.delete(0, sql.length());
            sql.append("Select new com.iportal.biz.RowItem (type.code, type.name) " );
            sql.append("From BannerType as type ");
            sql.append("Order by type.name ");
            
            bannerCategoryList = sess.createQuery(sql.toString()).list();*/
            
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
		request.setAttribute("imageCategoryList", imageCategoryList);
//		request.setAttribute("bannerCategoryList", bannerCategoryList);
		
		return null;
    }

}
