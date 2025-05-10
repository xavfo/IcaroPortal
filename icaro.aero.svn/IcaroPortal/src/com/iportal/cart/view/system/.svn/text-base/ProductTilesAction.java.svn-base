/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.cart.view.system;

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

import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class ProductTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(ProductTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
              
        List brandList = null;
        List sellerList = null;
        List taxTypeList = null;
        
        try {
            sess = getHibernateSession();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (seller.code, seller.name) ");
            sql.append("from Seller as seller ");
            sql.append("where seller.enabled = ? ");
            sql.append("order by seller.name ");
            
            sellerList = sess.createQuery(sql.toString()).setBoolean(0, Globals.TRUE.booleanValue()).list();
            
            sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (brand.code, brand.name) ");
            sql.append("from Brand as brand ");
            sql.append("order by brand.name ");
            
            brandList = sess.createQuery(sql.toString()).list();
            
            sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (taxType.code, taxType.name) ");
            sql.append("from TaxType as taxType ");
            sql.append("order by taxType.name ");
            
            taxTypeList = sess.createQuery(sql.toString()).list();

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
		request.setAttribute("sellerList", sellerList);
		request.setAttribute("brandList", brandList);
		request.setAttribute("taxTypeList", taxTypeList);
		
		

		return null;
    }

}
