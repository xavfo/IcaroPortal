package com.iportal.cart.view;

import java.util.ArrayList;
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

import com.iportal.cart.model.cart.PaymentType;
import com.iportal.view.system.CityTilesAction;
import com.yage.Globals;

public class PurchasesTilesAction extends CityTilesAction {
	
private static Log logger = LogFactory.getLog(PurchasesTilesAction.class);
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        
        
        
        Session sess = null;
        Query query = null;        
        List<PaymentType> paymentTypeList = null;
        List orderStatusList=null;
            	
        try {
        	sess = getHibernateSession();
        	StringBuffer hql = new StringBuffer();
            hql.append(" from PaymentType as type ");
            hql.append("where type.enabled = ? ");
            sess = getHibernateSession();
            query = sess.createQuery(hql.toString());
            query.setBoolean(0, Globals.TRUE);
            query.setCacheable(true);

            paymentTypeList = (ArrayList<PaymentType>)query.list();
            
            query = sess.createQuery("from OrderStatus as orderStatus order by orderStatus.name");
     		query.setCacheable(true);
     		orderStatusList = query.list();
            
         	
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
      
		request.setAttribute("paymentTypeList", paymentTypeList);
		request.setAttribute("orderStatusList", orderStatusList);
		
		return null;
    }    
    
    
}
