/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.portal.news;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.iportal.ctrl.portal.content.ContentContainerAction;
import com.yage.Globals;

/**
 * The Bussiness Logic for News
 * 
 *
 */
@SuppressWarnings("unchecked")
public class LoadNewsAction  extends ContentContainerAction {

    
    private static Log logger = LogFactory.getLog(LoadNewsAction.class);

   
     @SuppressWarnings("unchecked")
	public ActionForward execute (
             ActionMapping mapping, 
             ActionForm form,
             HttpServletRequest request, 
             HttpServletResponse response)
     throws Exception {
    	 Session sess = null;
    	 //Content content = null;
         try{
        	 HttpSession session = request.getSession();
        	 sess = getHibernateSession();
        	 MenuPortalBean portalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
        	 this.setCountContentContainers(request, sess, null,  portalBean.getContentCode());
        	 //request.setAttribute("content", content);
         }
    	 catch (Exception e) {
             logger.error(e.getMessage(), e);
 		} finally {
 			if (sess != null)
 				try {
 					sess.clear();
 					sess.close();
 				} catch (Exception e) {
 				}
 		}
 		return mapping.findForward(Globals.FORWARD_SUCCESS);
     }
    
       
}
