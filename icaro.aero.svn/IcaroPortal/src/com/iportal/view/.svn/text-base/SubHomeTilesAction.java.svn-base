package com.iportal.view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

public class SubHomeTilesAction extends BaseTilesAction {
	
private static Log logger = LogFactory.getLog(SubHomeTilesAction.class);
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        List result = null;
        
        HttpSession session = request.getSession();
        MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
        
        StringBuffer hql = new StringBuffer();
    	
        try {      	
        	sess = getHibernateSession();
        	
        	hql.append("select new com.iportal.biz.RowItem ( ");
     		hql.append(" related.code, related.title, related.link, related.banner.path ) ");
     		hql.append(" FROM RelatedContent as relatedContent ");
     		hql.append(" inner join  relatedContent.related related");
	        hql.append(" left  join  related.banner banner ");
     		hql.append(" WHERE relatedContent.content.code = ? ");
     		hql.append(" and relatedContent.related.enabled = ? ");
     		hql.append(" order by relatedContent.related.order");
     		Query query = sess.createQuery(hql.toString());
			
			query.setLong(0, portalBean.getContentCode());
			query.setBoolean(1,Globals.TRUE);
			query.setCacheable(true);
			result = query.list();
			
			request.setAttribute("relatedContentList", result);
		
			
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
