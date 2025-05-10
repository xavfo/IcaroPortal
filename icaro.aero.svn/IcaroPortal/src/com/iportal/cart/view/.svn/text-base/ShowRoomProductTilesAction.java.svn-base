/*
 * Created 16/04/2007
 *	ShowRoomProductTilesAction.java
 */
package com.iportal.cart.view;

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

import com.iportal.cart.CartConstants;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * @author YAGE(hernan)
 *
 */
public class ShowRoomProductTilesAction extends BaseTilesAction {

	private static Log logger = LogFactory.getLog(ShowRoomProductTilesAction.class);
	
    /* 
     * (non-Javadoc)
     * @see org.apache.struts.tiles.actions.TilesAction#execute(org.apache.struts.tiles.ComponentContext, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        

    	Session sess = null;
        Query query  = null;
        List results = null;
        
        ServletContext mainContext = this.getServlet().getServletContext();
        
        try {
        	results =   (List) mainContext.getAttribute(CartConstants.CART_CATALOG_SHOW_ROOM);
        	if (results == null) {
        		sess = getHibernateSession();
	        	 StringBuffer hql = new StringBuffer();
	        	 
	        	 hql.append(" from Product p");
	             hql.append(" where p.enabled = ? ");
	             hql.append(" and p.showRoom = ? ");
                 hql.append(" order by RAND() ");
                 
                query = sess.createQuery(hql.toString());
                query.setBoolean(0, Globals.TRUE);
                query.setBoolean(1, Globals.TRUE);
	            query.setCacheable(true);
	            
	            results = query.list();
	            
	            mainContext.setAttribute(CartConstants.CART_CATALOG_SHOW_ROOM, results);
	            
        	}
     	        	
            
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
		
		request.setAttribute("showRoomList", results);
		
		
		return null;
    }

}
