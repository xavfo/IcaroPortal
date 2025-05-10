package com.iportal.view;

import java.util.List;

import javax.servlet.ServletContext;
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

public class MenuPortalLeftTilesAction extends BaseTilesAction {
	
private static Log logger = LogFactory.getLog(MenuPortalLeftTilesAction.class);
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        List result = null;
        Query query = null;
        
        ServletContext servletContext = request.getSession().getServletContext();
        
        result = (List) servletContext.getAttribute(Constants.LEFT_MENU_KEY);
        
        StringBuffer hql = new StringBuffer();
    	
        try {
        	//Language language = (Language) request.getSession().getAttribute(Constants.LANGUAGE_KEY);
        	sess = getHibernateSession();
        	
         	if (null == result ) {
            	
                hql.append("select new com.iportal.biz.RowItem ( ");
                hql.append("  c.parent.code, c.parent.name, c.code, c.name, c.level ) ");
                hql.append("from Category c ");
                hql.append("right join c.parent cp ");
                hql.append("where c.parent.level = 1 ");
                hql.append("  and c.parent.enabled = ? ");
                hql.append("  and c.enabled = ? ");
                hql.append("order by c.parent.orderIndex, c.orderIndex ");
    			query = sess.createQuery(hql.toString());
    			
                query.setBoolean(0,Globals.TRUE);
    			query.setBoolean(1,Globals.TRUE);
    			query.setCacheable(true);
    			result = query.list();
    			
                //quitar de contexto lista  de todas las categorias
    			servletContext.removeAttribute(com.iportal.cart.CartConstants.CART_CATALOG_ALL_CATEGORIES);
    			servletContext.setAttribute(Constants.LEFT_MENU_KEY, result);
         		
         	}
         	
            request.setAttribute(Constants.LEFT_MENU_KEY, result);
		
			
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
      
		
		
		
		return null;
    }    
    
    
}
