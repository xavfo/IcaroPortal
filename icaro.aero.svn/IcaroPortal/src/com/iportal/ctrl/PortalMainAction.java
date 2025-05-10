/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.biz.PortalBeanHelper;
import com.iportal.biz.portal.bean.MenuPortalBean;
import com.yage.Globals;
import com.yage.struts.action.BaseAction;
import com.yage.struts.action.BaseForm;

/**
 *  
 * @author  YAGE (Jorge Lomas)
 * @version 2.0
 *
 */
public class PortalMainAction extends BaseAction {
	private static Log logger = LogFactory.getLog(PortalMainAction.class);
    
	public ActionForward execute(
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	MenuPortalBean menuPortalBean  = null;
    	try {
            HttpSession session = request.getSession();
            BaseForm baseForm = (BaseForm) form;
            
            menuPortalBean = (MenuPortalBean) session.getAttribute(Constants.MENU_KEY);
            
            if(menuPortalBean == null || !menuPortalBean.getContentCode().equals(baseForm.getCode())) {
            	menuPortalBean = PortalBeanHelper.setNewBean(baseForm.getCode(), request);
	    		if ( menuPortalBean == null ){
	    			throw new Exception(BaseHelper.getApplicationBundleMessage("error.content.notFound", new Long[] {baseForm.getCode()}));
    			}
	    		if ( menuPortalBean.getPortalModuleCode() == null) {
	    			throw new Exception(BaseHelper.getApplicationBundleMessage("error.content.module",new Long[] {menuPortalBean.getContentCode()}));
	    		}
            }
            
            return mapping.findForward(menuPortalBean.getPortalModuleForward());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return mapping.findForward(Globals.FORWARD_FAILURE);
		} finally {
		}
    }
}
