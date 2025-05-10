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
public class OrderTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(OrderTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
              
        List statusList = null;
        List paymentStatusList = null;
        List paymentTypeList = null;
        List sellerList = null;
        List customerTypeList = null;
        
        try {
            sess = getHibernateSession();
            
            StringBuffer sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (paymentType.code, paymentType.name) ");
            sql.append("from PaymentType as paymentType ");
            sql.append("where paymentType.enabled = ? ");
            sql.append("order by paymentType.name ");
            
            paymentTypeList = sess.createQuery(sql.toString()).setBoolean(0, Globals.TRUE.booleanValue()).list();
            
            sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (status.code, status.name) ");
            sql.append("from OrderStatus as status ");
            sql.append("order by status.name ");
            
            statusList = sess.createQuery(sql.toString()).list();
            
            sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (paymentStatus.code, paymentStatus.name) ");
            sql.append("from PaymentStatus as paymentStatus ");
            sql.append("order by paymentStatus.name ");
            
            paymentStatusList = sess.createQuery(sql.toString()).list();
            
            sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (seller.code, seller.name) ");
            sql.append("from Seller as seller ");
            sql.append("where seller.enabled = ? ");
            sql.append("order by seller.name ");
            
            sellerList = sess.createQuery(sql.toString()).setBoolean(0, Globals.TRUE.booleanValue()).list();
            
            sql = new StringBuffer();
            sql.append("select new com.iportal.biz.RowItem (customerType.code, customerType.name) ");
            sql.append("from OrderCustomerType as customerType ");
            sql.append("order by customerType.name ");
            
            customerTypeList = sess.createQuery(sql.toString()).list();
            
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
		request.setAttribute("statusList", statusList);
		request.setAttribute("paymentStatusList", paymentStatusList);
		request.setAttribute("paymentTypeList", paymentTypeList);
        request.setAttribute("sellerList", sellerList);
        request.setAttribute("customerTypeList", customerTypeList);

		return null;
    }

}
