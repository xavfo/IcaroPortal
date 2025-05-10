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
import com.iportal.biz.BulletinBean;
import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class BulletinStep2TilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(BulletinStep2TilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;
        
        List topicList = null;
        
        HttpSession session = request.getSession();		
 		BulletinBean bulletinBean = (BulletinBean) session.getAttribute("bulletinBean");
 		
        try {
            sess = getHibernateSession(); 
    		
            query = sess.createQuery("select new com.iportal.biz.ItemBean(topic.code, topic.name) from Topic as topic where topic.enabled = ? and topic.code <> ?  and topic.code <> ? order by topic.name");
    		query.setCacheable(true);
    		topicList = query.setBoolean(0, Globals.TRUE).setLong(1, bulletinBean.getTopicCode()).setLong(2, Constants.GENERAL_BULLETIN_CODE).list();     		
            
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
		request.setAttribute("topicList", topicList);
		

		return null;
    }

}
