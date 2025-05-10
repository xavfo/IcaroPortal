/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl.system;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.model.Language;
import com.iportal.model.system.SysUser;
import com.yage.struts.action.BaseAction;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class SystemMenuAction extends BaseAction {

    
    public ActionForward execute(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception {
        
        HttpSession session = request.getSession();
        
        
        // Gets the StringBuffer for the currently user's menu
        StringBuffer menu = (StringBuffer) session.getAttribute(Constants.SYSTEM_MENU_KEY);
        Locale locale = (Locale) session.getAttribute(Constants.SYSTEM_MENU_LOCALE_KEY);
        SysUser user = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);
        Language language = (Language) session.getAttribute(Constants.LANGUAGE_KEY);
        
        Locale current = language.getLocale();
        if (menu == null || !current.equals(locale) ) {
            SystemMenuHelper helper = new SystemMenuHelper();
            menu = helper.createMenu(user.getRole(), current, request.getContextPath());
            
            // stores the new menu in session scope
            session.setAttribute(Constants.SYSTEM_MENU_KEY, menu);
            session.setAttribute(Constants.SYSTEM_MENU_LOCALE_KEY, current);
        }
        
        response.setContentType("text/javascript");
        PrintWriter out = response.getWriter();
        out.print( menu );
        
        return null;
    }
    
    
}
