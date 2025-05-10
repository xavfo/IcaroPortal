/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.ctrl;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.biz.portal.MenuUtils;
import com.yage.struts.action.BaseAction;

/**
 * The PortalMenuAction generates the JavaScript file for the portal's main
 * menu. The generated script is stored in session scope. If the user change
 * the language the script is regenerated and stored in session scope under 
 * the same attribute name.
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class PortalMenuAction extends BaseAction {

    
   // private static Log logger = LogFactory.getLog(PortalMenuAction.class);
    
    
    
    public ActionForward execute(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception {
        
        //HttpSession session = request.getSession();
        ServletContext context = getServlet().getServletContext();
        BasePortalForm portalForm = (BasePortalForm) form;
        
        StringBuffer menuLanguageKey = new StringBuffer(15);
        menuLanguageKey.append(Constants.MAIN_MENU_KEY);
        MenuUtils utils = new MenuUtils();
        if (portalForm.getMenuCode() != null && portalForm.getMenuCode().longValue() != 0L) {
        	menuLanguageKey.append("_JS_");
        	menuLanguageKey.append(portalForm.getMenuCode());
        	
        
		    // Gets the StringBuffer for the currently user's menu
		    StringBuffer menuBuffer = (StringBuffer) context.getAttribute(menuLanguageKey.toString());
		    //Locale locale = (Locale) session.getAttribute(Constants.PORTAL_MENU_LOCALE_KEY);
		    
		    Locale current = getLocale(request);
		    if (menuBuffer == null ||menuBuffer.length()<1 ) {
		        menuBuffer = utils.createMenu(portalForm.getMenuCode(), request.getContextPath());
		        
		        // stores the new menu in session scope
		        
		        context.setAttribute(menuLanguageKey.toString(), menuBuffer);
		        context.setAttribute(Constants.PORTAL_MENU_LOCALE_KEY, current);
		    }
		    
		    response.setContentType("text/javascript");
		    PrintWriter out = response.getWriter();
		    out.print( menuBuffer );
        }
        return null;
    }

}
