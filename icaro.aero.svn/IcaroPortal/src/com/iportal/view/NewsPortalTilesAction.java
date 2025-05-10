package com.iportal.view;

import java.util.GregorianCalendar;
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

import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

public class NewsPortalTilesAction extends BaseTilesAction {
	
private static Log logger = LogFactory.getLog(NewsPortalTilesAction.class);
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        GregorianCalendar now = new GregorianCalendar();
        List newsList = null;
        Query query = null;
     
        try {
            sess = getHibernateSession();
            
            StringBuffer sql = new StringBuffer();
     		sql.append("from News as news ");
    		sql.append("where news.publishHome = ? ");
    		sql.append("and news.isEnabled = ? ");
    		sql.append("and news.from <= ? ");
    		sql.append("and news.to >= ? ");
    		sql.append("order by news.from desc");
    

    		query = sess.createQuery(sql.toString());
    		query.setBoolean(0, Globals.TRUE.booleanValue());
    		query.setBoolean(1, Globals.TRUE.booleanValue());
    		query.setCalendar(2, now);
    		query.setCalendar(3, now);

    		

     		newsList = query.list();
    		
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
   
		request.setAttribute("newsList", newsList);

		return null;
    }

}
