package com.iportal.cart.view;

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

import com.iportal.view.system.CityTilesAction;

public class RegisterTilesAction extends CityTilesAction {
	
private static Log logger = LogFactory.getLog(RegisterTilesAction.class);
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        super.execute(context, mapping, form, request, response);
        
        
        Session sess = null;
        Query query = null;
        
        List idTypeList = null;
            	
        try {
        	//Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        	sess = getHibernateSession();

            query = sess.createQuery(" from IDType");
            query.setCacheable(true);
            idTypeList = query.list();
         	
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
      
        request.setAttribute("idTypeList", idTypeList);
		
		return null;
    }    
    
    
}
